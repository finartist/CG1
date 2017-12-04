clear all;
close all;

img_blur = imread('D:/Lisa/Documents/Uni/3. Semester/BV/Praxisaufgabe_03/p03_Bild1.png');
img_blur = double(img_blur);

imshow(mat2gray(img_blur));

%Einheitsmatrix 17x17
h = eye(17);
%Each !=0 Element is 1/delta(s) = 1/17
h = h.*(1/17);

%center 17x17 block in matrix of image size
h = padarray(h, [165, 291]);
%+1 line of zeros in each direction because image size is 348x600 and 2*165 + 17 = 347 and 2*291+17 = 599
h = padarray(h, [1,1], 'pre');

%using formula from presentation
%would do the same as above code -> h is the psf and in the middle we have a line values 1/17
%for x= -8:8
% if(abs(x/2) < 17)
%  h(ceil(size(img_blur)(1)/2 + x*cos(-2.35619)), ceil(size(img_blur)(2)/2 + x*sin(-2.35619))) = 1/17;
% end
%end

%test psf, should be a diagonal line in moving direction
figure, imshow(h, []);

%transform to Fourier space
img_blur_fft = fft2(img_blur);
H = fft2(fftshift(h));

%test psf in Fourier space
figure, imshow(H, []);

%Filter image by elementwise division with Fourier-psf
F = img_blur_fft./H;

%back to position domain
f = ifft2(F);

%show filtered image
figure, imshow(f, []);
%Das Bild ist zwar nicht mehr durch Bewegung unscharf, 
%aber das vorher kaum erkennbare Rauschen wurde verstärkt.

%filter image with Wiener filter
f = p03_wien_filt(img_blur, h, 0.0001);
figure, imshow(f, []);
%Das Bild wurde mit dem Wiener Heuristikfilter gefiltert. Dieser unterdrückt ebenfalls 
%Rauschen und das Bild ist somit sehr gut erkennbar.
clear all;
close all;

image = imread('D:/Lisa/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_02/p02_Bild01.tif');
image = double(image);

img_myfft = p02_myfft2(image);
img_fft = fftshift(fft2(image));

%magnitude is absolute value of complex numbers
amp_myfft = abs(img_myfft);
%get a better vision using logarithm (+1 as log(0) is undefined)
amp_myfft = log(amp_myfft+1);
%get an actual picture
amp_myfft = mat2gray(amp_myfft);

figure, imshow(amp_myfft);

%do same with fft picture of Octave function
amp_fft = abs(img_fft);
amp_fft = log(amp_fft+1);
amp_fft = mat2gray(amp_fft);

figure, imshow(amp_fft);

%converting back (has to shift back quadrants too)
img_myifft = ifft2(ifftshift(img_myfft));
img_ifft = ifft2(ifftshift(img_fft));

%check condition and show image
if(max(abs(imag(img_myifft(:))))/max(abs(img_myifft(:))) < sqrt(eps))
 img_myifft = mat2gray(img_myifft);
 figure, imshow(img_myifft);
end
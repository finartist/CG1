clear all;
close all;

%read image and convert to double
image = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_02/p02_Bild01.tif');
image = double(image);

%convert to frequency domain
img_myfft = p02_myfft2(image);

%4 images
img1 = p02_modify(img_myfft, 2, 5);
img1 = log(img1+1);
img1 = mat2gray(img1);

img2 = p02_modify(img_myfft, -2, 5);
img2 = log(img2+1);
img2 = mat2gray(img2);

img3 = p02_modify(img_myfft, -2, -5);
img3 = log(img3+1);
img3 = mat2gray(img3);

img4 = p02_modify(img_myfft, 16, -4);
img4 = log(img4+1);
img4 = mat2gray(img4);

%show in 1 window
subplot(2,2,1);
imshow(img1);
subplot(2,2,2);
imshow(img2);
subplot(2,2,3);
imshow(img3);
subplot(2,2,4);
imshow(img4);

%all cooridnates with distance < 20 from origin
sz = size(img_myfft);
imgAdd = zeros(sz);
for i = -20:20
 for j = -20:20
  if(i*i + j*j < 20*20)
   imgAdd = imgAdd + p02_modify(img_myfft, i, j);
  end
 end
end

%imgAdd = log(imgAdd+1);
%imgAdd = mat2gray(imgAdd);

%[] Operator better result for position space
figure, imshow(imgAdd,[]);

%das Bild zeigt eine verschwommene Version des Originalbildes.
%Damit ist zu erkennen, das die wesentliche Information im transformierten
%Bild in der Mitte liegt. Das Aufaddieren ist im Prinzip die Anwendung des idealen
%Tiefpassfilters.
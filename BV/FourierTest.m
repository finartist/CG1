clear all;
close all;

image = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild1.png');
image = double(image);
fftimg = fft2(image);
flip = abs(fftimg);
imshow(flip,[]);
new = ifft2(flip);

figure, imshow(new, []);

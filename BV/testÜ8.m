
clear all;
close all;

A = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_04/p04_Bild1.png');

A2 = A.*2;
figure, subplot(3,3,1); imshow(A2);
%figure, plot(imhist(A2));

A3 = A.+100;
subplot(3,3,2); imshow(A3);
%figure, plot(imhist(A3));

A4 = double(A);
A4 = A4.*(-1);
A4 = A4.+255;
A4 = mat2gray(A4);
subplot(3,3,3); imshow(A4);
%figure, plot(imhist(A4));

A5 = double(A);
A5 = A5./255;
A5 = A5.^(2);
A5 = A5.*255;
A5 = mat2gray(A5);
subplot(3,3,4); imshow(A5);
subplot(3,3,5); plot(imhist(A5));

alpha = 0.05;
A6 = double(A);
for i=1:size(A)(1)
 for j = 1:size(A)(2)
  A6(i,j) = 255*(1+exp(-alpha*(A6(i,j) - 125)))^(-1);
 end
end
A6 = mat2gray(A6);
subplot(3,3,6); imshow(A6);
subplot(3,3,7), plot(imhist(A6));
x = 0:255;
y = 255*(1+exp(-0.1*(x-125)))^(-1)
figure, plot(x, y);
clear all;
close all;

imageGray = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild1.png');

%RGB image need 3d-Matrix, if every dimension has same intensity -> grayscale
red = imageGray;
green = imageGray;
blue = imageGray;

%colour a 11x11 square red at empirically detected position
% -> only red layer has value>0 on square position
%Alternative: matrix multiplication/addition
%for i = 0:10
%  for j = 0:10
%    red(107+j, 441+i) = 256;
%    green(107+j, 441+i) = 0;
%    blue(107+j, 441+i) = 0;
%  end
%end

red(107:117, 441:451) = 256;
green(107:117, 441:451) = 0;
blue(107:117, 441:451) = 0;

%concatinate the three layers to a 3d matrix
imageRGB = cat(3, red, green, blue);
imshow(imageRGB);
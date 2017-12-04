clear all; close all;

%read images
image1 = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild1.png');
image2 = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild2.png');

imshow(image1);
%figure is for creating new figure window
figure, imshow(image2);

%delete 200 black Pixel
for c = 1:200
  %always on index 5001, because other pixel move up
  image2(5001) = [];
end

%delete 442 white pixel
for c = 1:442
  image2(120001) = [];
end

%reshape image to same dimensions as original (former vector)
image2 = reshape(image2, size(image1));

%show reconstructed image
figure, imshow(image2);

%write 1, if true and 0 if false to console
display(isequal(image1, image2));

display(image2);
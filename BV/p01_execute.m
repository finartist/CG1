close all; 
clear all;

%read images
image1 = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild1.png');
image2 = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe1/p01_Bild2.png');

%save values
[gm1, np1, eg1, vg1] = p01_image_statistics(image1);
[gm2, np2, eg2, vg2] = p01_image_statistics(image2);

%display values
display(gm1);
display(np1);
display(eg1);
display(vg1);
display(gm2);
display(np2);
display(eg2);
display(vg2);
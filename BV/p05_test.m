clear all;
close all;

image = imread('D:/Lisa/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_05/p05_Bild01.bmp');
image = double(image);

%Sobel Kernel
skernelx = [-1 , 0, 1; -2, 0, 2; -1, 0, 1];
skernely = [-1, -2, -1;0,0,0;1,2,1];

%Convolution
newimgx = p05_conv2d(image, skernelx);
newimgy = p05_conv2d(image, skernely);
newimg = abs(newimgx) .+ abs(newimgy);

%Filter
nimgx = p05_filt2d(image, skernelx);
nimgy = p05_filt2d(image, skernely);
nimg = abs(nimgx) .+ abs(nimgy);

figure('name', 'Sobel'), subplot(3,3,2);
imshow(mat2gray(image));
title(sprintf('original'));
subplot(3,3,4);
imshow(mat2gray(newimgx)); title(sprintf('convolution x')); 
subplot(3,3,5);
imshow(mat2gray(newimgy)); title(sprintf('convolution y')); 
subplot(3,3,6);
imshow(mat2gray(newimg)); title(sprintf('convolution xy')); 
subplot(3,3,7);
imshow(mat2gray(nimgx)); title(sprintf('filter x'));
subplot(3,3,8);
imshow(mat2gray(nimgy)); title(sprintf('filter y')); 
subplot(3,3,9);
imshow(mat2gray(nimg)); title(sprintf('filter xy')); 

%mean Kernel
m3 = ones([3,3])./9;
m5 = ones([5,5])./25;
m7 = ones([7,7])./49;
m11 = ones([11,11])./121;

%%with3x3
tcon3 = tic(); %start timer for convolution 3x3
newimg3 = p05_conv2d(image, m3);
tcon3 = toc(tcon3); %stop timer for 3x3 convolution
tcor3 = tic();
nimg3 = p05_filt2d(image,m3);
tcor3 = toc(tcor3);

%%with 5x5
tcon5 = tic();
newimg5 = p05_conv2d(image, m5);
tcon5 = toc(tcon5);
tcor5 = tic();
nimg5 = p05_filt2d(image, m5);
tcor5 = toc(tcor5);

%%with 7x7
tcon7 = tic();
newimg7 = p05_conv2d(image, m7);
tcon7 = toc(tcon7);
tcor7 = tic();
nimg7 = p05_filt2d(image, m7);
tcor7 = toc(tcor7);

%%with 11x11
tcon11 = tic();
newimg11 = p05_conv2d(image, m11);
tcon11 = toc(tcon11);
tcor11 = tic();
nimg11 = p05_filt2d(image, m11);
tcor11 = toc(tcor11);

disp('Zeitmessung in Sekunden:');
disp('Konvolution 3x3: '), disp(tcon3);
disp('Filter 3x3: '), disp(tcor3);
disp('Konvolution 5x5: '), disp(tcon5);
disp('Filter 5x5: '),disp(tcor5);
disp('Konvolution 7x7: '), disp(tcon7);
disp('Filter 7x7: '), disp(tcor7);
disp('Konvolution 11x11: '), disp(tcon11);
disp('Filter 11x11: '), disp(tcor11);

%Zeitmessung in Sekunden:
%Konvolution 3x3:
% 7.8321
%Filter 3x3:
% 0.11454
%Konvolution 5x5:
% 7.6056
%Filter 5x5:
% 0.13815
%Konvolution 7x7:
% 7.9976
%Filter 7x7:
% 0.11071
%Konvolution 11x11:
% 7.9499
%Filter 11fx11:
% 0.11461

%Die Zeit ist relativ unabhängig von der Größe des Kernels,
%da aber bei der Filterung nicht für jedes Pixel der Wert
%mit Hilfe des Kernels berechnet werden muss, ist diese bedeutend
%schneller. Die Zeit bei der Konvolution ist bei den verschiedenen 
%Größen recht ähnlich, da die internen Funktionen wie sum, x:y und .* vermutlich
%die Berechnung parallelisieren und damit die Größe nur wenig Einfluss hat.
%Bei einer Implementierung über for/while Schleifen würden größere Kernel
%die Rechenzeit vermutlich sehr stark erhöhen (für jedes Pixel müssen mehr Multiplikationen
%durchgeführt werden).
%tic und toc haben außerdem eine gewisse Ungenauigkeit, weil andere laufende
%Programme diese Werte beeinflussen.

figure('name', 'Mean'), subplot(2,4,1);
imshow(mat2gray(newimg3)); title(sprintf('convolution 3x3'));
subplot(2,4,2);
imshow(mat2gray(nimg3)); title(sprintf('filter 3x3'));
subplot(2,4,3);
imshow(mat2gray(newimg5)); title(sprintf('convolution 5x5'));
subplot(2,4,4);
imshow(mat2gray(nimg5)); title(sprintf('Filter 5x5'));
subplot(2,4,5);
imshow(mat2gray(newimg7)); title(sprintf('convolution 7x7'));
subplot(2,4,6);
imshow(mat2gray(nimg7)); title(sprintf('Filter 7x7'));
subplot(2,4,7);
imshow(mat2gray(newimg11)); title(sprintf('convolution 11x11'));
subplot(2,4,8);
imshow(mat2gray(nimg11)); title(sprintf('Filter 11x11'));

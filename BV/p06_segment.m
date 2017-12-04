close all;
clear all;

image = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_06/p06_Bild01.png');

image = im2double(image);
figure, imshow(image, []);

meankernel = ones([5,5])./25;

%%Histogramm
figure, plot(imhist(image));
%Es gibt einen hohen Anteil an dunklen Pixeln. Ab Grauwert 50 bis ca. 200
%sind sie ungefähr gleichverteilt. Am Ende gibt es noch einen kleinen "Peak"
%bei den sehr hellen Grauwerten(>250). Das die Grauwerte annähernd gleichverteilt
%erklärt sich durch den Farbverlauf, die variierenden Grauwerte in den Kreisen und
%durch das deutlich sichtbare Rauschen. Dabei gibt es trotzdem sehr viele dunkle
%Strukturen, die die Spitze bei den dunklen Grauwerten erklären.

%%Rauschentfernung
kernel = meankernel;

%Padding
pad_size = floor(size(kernel)(1)/2);
k_sz = size(kernel)(1);

img_pad = padarray(image, [pad_size, pad_size], 'circular');

%flip
flip_kernel = flipud(fliplr(kernel));

newimg = zeros(size(image));

%Faltung
for i = 1:size(image)(1)
 for j = 1:size(image)(2)
  newimg(i,j) = sum(sum(img_pad(i:i+k_sz-1, j:j+k_sz-1).*flip_kernel));
 end
end
%gefaltetes Bild
img_con = newimg;

figure, imshow(img_con, []);


%%Hintergrundbild
rang = 51;

background = ones(size(image));

%Rangordnungsfilter Median
for i = 1:size(image,1)-rang
 for j = 1:size(image,2)-rang
  background(i+ceil(rang/2),j+ceil(rang/2)) = sort(img_con(i:i+rang, j:j+rang)(:))(ceil((rang*rang)/2));
 end
end

figure, imshow(background, []);

%%Bild-Hintergrund und Segmentierung
%Korrigiertes Bild
corr_img = zeros(size(image));

%Nur das was um 0.1 grauwerte nach oben und unten vom Hintergrundbild abweicht ist Objekt
for i = 1:size(img_con,1)
 for j = 1:size(img_con,2)
  if((img_con(i,j)<background(i,j)-0.1) || (img_con(i,j)>background(i,j)+0.1))
   corr_img(i,j) = 255;
  else
   corr_img(i,j) = 0;
  end
 end
end

figure, imshow(corr_img, []);

%Der Medianfilter kann bei dieser Größe nicht alle Strukturen im Vordergrund vollständig
%filtern, bei einer größeren Größe würden allerdings noch mehr Strukturen am Rand
%verloren gehen.
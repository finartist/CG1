close all;
clear all;

img = imread('C:/Users/x/Dropbox/Uni/3.Semester/BV/Praxisaufgabe_02/p02_Bild01.tif');

for x = 1:size(img)(1)
 for y = 1:size(img)(2)
  if(img(x,y) < 51)
   img(x,y) = 0;
  elseif(img(x,y) < 102)
   img(x,y) = 102;
  elseif(img(x,y) < 163)
   img(x,y) = 163;
  else
   img(x,y) = 255;
  end
 end
end

imshow(img);
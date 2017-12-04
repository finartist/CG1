
function [graymedian, numPix, erwGrauw, varGrauw] = p01_image_statistics (image1)
  %median for gray values in picture
  graymedian = median(image1(:));
  %number of pixels with value between 50 and 100
    numPix = length(find(50<image1));
    numPix = numPix-length(find(99<image1));
%{  count = 0;
  for i=1:size(image1)(1)
    for j=1:size(image1)(2)
      if (50<image1(i,j) && image1(i,j)<100)
        count = count+1;
      end
    end
  end
  %}
  numPix = count;
  %mean (Erwartungwert)
  erwGrauw = mean(image1(:));
  %variance (Varianz)
  varGrauw = var(image1(:));
end

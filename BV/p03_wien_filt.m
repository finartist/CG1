function img_rest = p03_wien_filt (img_blur, psf, k)
 wien = zeros(size(img_blur));
 G = fft2(img_blur);
 H = fft2(fftshift(psf));
 
 %same as below code but slower because of loops
 %for i = 1:size(img_blur)(1)
  %for j = 1:size(img_blur)(2)
   %if(H(i,j)!=0)
    %wien(i,j) = 1/H(i,j) * (abs(H(i,j))^2/(abs(H(i,j))^2+k));
   %end
  %end
 %end
 
 %restaurated image = (motion blur and noise image) divided by Fourier-psf G./H
 %multiplied by heuristic of Wiener filter abs(H).^2 ./ (abs(H).^2 +k)
 %always elementwise -> same as in for loops
 F = G./H .* abs(H).^2 ./ (abs(H).^2 +k);
 img_rest = ifft2(F);
 
end

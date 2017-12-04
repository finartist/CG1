function img_mod = p02_modify (img_fft, x, y)
  %new image is 0 everywhere except at one coordinate
  img_mod = zeros(size(img_fft));
  
  %coordinate has to be based on coordinate system centered on image center
  img_mod(size(img_fft)(1)/2+x, size(img_fft)(2)/2+y) = img_fft(size(img_fft)(1)/2+x, size(img_fft)(2)/2+y);
  
  %tranform back to position space
  img_mod = ifft2(ifftshift(img_mod));
end

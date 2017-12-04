function img_con = p05_conv2d (image, kernel)

%constants for padding
pad_size = floor(size(kernel)(1)/2);
k_sz = size(kernel)(1);

img_pad = padarray(image, [pad_size, pad_size], 'symmetric');

%flip in both dimensions
flip_kernel = flipud(fliplr(kernel));

newimg = zeros(size(image));

%convolution
for i = 1:size(image)(1)
 for j = 1:size(image)(2)
  newimg(i,j) = sum(sum(img_pad(i:i+k_sz-1, j:j+k_sz-1).*flip_kernel));
 end
end
img_con = newimg;

end

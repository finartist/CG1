function img_filt = p05_filt2d (image, kernel)

%image to frequency domain
freq_img = fft2(image);

%pad kernel, so it can be used in frequency domain
pad_side = floor((size(image,1)-size(kernel,1))/2);
pad_top = floor((size(image,2)-size(kernel,2))/2);
pad_kernel = padarray(kernel, [pad_side, pad_top]);

if(size(pad_kernel,1) < size(image,1))
 pad_kernel = padarray(pad_kernel, [1,0], 'pre');
end

if(size(pad_kernel,2) < size(image,2))
 pad_kernel = padarray(pad_kernel, [0,1], 'pre');
end

%Kernel to frequency domain
freq_kernel = fft2(fftshift(pad_kernel));
%imshow(mat2gray(freq_kernel));

%apply filter
freq_nimg = freq_img.*freq_kernel;

img_filt = round(ifft2(freq_nimg));
%display(img_filt);
end

function img_myfft = p02_myfft2 (img)
  M = size(img)(1);
  N = size(img)(2);
  
  frequMatrix = zeros(M, N);
  
  %2 matrices, where the m/n params of the base function are set
  %-> 2 base matrices
  MMatrix = exp(-2i * pi /M * [0:M-1]' * [0:M-1]);
  NMatrix = exp(-2i * pi /N * [0:N-1]' * [0:N-1]);
  
  %multiply image by the two base function matrices (similar to 1D case)
  %MxN image -> MxM * MxN * NxN
  frequMatrix = MMatrix * img * NMatrix;
  
  %shift quadrants, so that low frequencies are centered
  %ceil rounds it up
  sz = ceil(size(frequMatrix)/2);
  %cut quadrants and rearrange them
  shifted = frequMatrix([sz(1)+1:end, 1:sz(1)], [sz(2)+1:end, 1:sz(2)]);
  
  img_myfft = shifted;
  figure, imshow(frequMatrix);
  figure, imshow(shifted);
end
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Apr 13 09:44:39 2017

@author: lessig
"""

import numpy as np
import matplotlib.pyplot as plt

# single precision
xs_f = np.linspace( 1.99, 2.01, num=256, dtype='float32')
poly_coeffs_f = np.array( [-8.0, 12.0, -6.0, 1.0], dtype='float32')
ys_f = poly_coeffs_f[3] * np.ones( xs_f.shape, dtype='float32')
# naive and inefficient way 
for k in range( 2, -1, -1) :
    ys_f = (xs_f * ys_f) + poly_coeffs_f[k]
    
# double precision
xs_d = np.linspace( 1.99, 2.01, num=256, dtype='float64')
poly_coeffs_d = np.array( [-8.0, 12.0, -6.0, 1.0], dtype='float64')
ys_d = poly_coeffs_d[3] * np.ones( xs_f.shape, dtype='float64')
# naive and inefficient way 
for k in range( 2, -1, -1) :
    ys_d = xs_d * ys_d + poly_coeffs_d[k]
    

# plot both graphs    
plt.plot( xs_f, ys_f, xs_d, ys_d)
plt.legend( ['fp32', 'fp64'])
plt.show


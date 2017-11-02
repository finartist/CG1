#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Simple program for polynomial evaluation

y = -8 + 12 x - 6 x^2 + x^3

@author: lessig
"""

import numpy as np
import matplotlib.pyplot as plt

# single precision
xs_f = np.linspace( 1.99, 2.01, num=256, dtype='float32')
poly_coeffs_f = np.array( [-8.0, 12.0, -6.0, 1.0], dtype='float32')
ys_f = poly_coeffs_f[0]
# naive and inefficient way 
for k in range( 1, 3+1) :
    ys_f += xs_f**k * poly_coeffs_f[k]

# double precision
xs_d = np.linspace( 1.99, 2.01, num=256, dtype='float64')
poly_coeffs_d = np.array( [-8.0, 12.0, -6.0, 1.0], dtype='float64')
ys_d = poly_coeffs_d[0]
# naive and inefficient way 
for k in range( 1, 3+1) :
    ys_d += xs_d**k * poly_coeffs_d[k]
    
# plot both graphs    
plt.plot( xs_f, ys_f, xs_d, ys_d)
plt.legend( ['fp32', 'fp64'])


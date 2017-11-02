#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Apr 13 09:02:20 2017

@author: lessig
"""

import numpy as np
import matplotlib.pyplot as plt

poly3 = np.random.randn( 3, 3+1)
poly5 = np.random.randn( 3, 5+1)

# Evaluate polynomials using Horner's scheme
xs = np.linspace( -1.0, 1.0, 1024)
xss = np.reshape( xs, (1024,1))
xss = np.repeat( xss, 3, axis=1 )

ys_poly3 = np.ones( xss.shape) * poly3[:,-1]
# naive and inefficient way 
for k in range( poly3.shape[1]-2, -1, -1) :
    ys_poly3 = xss * ys_poly3 + poly3[:,k]

ys_poly5 = np.ones( xss.shape) * poly5[:,-1]
# naive and inefficient way 
for k in range( poly5.shape[1]-2, -1, -1) :
    ys_poly5 = xss * ys_poly5 + poly5[:,k]

# plot polynomials
plt.figure(0)
plt.title( 'poly3')
plt.plot( xs, ys_poly3)
plt.show
 
plt.figure(1)
plt.title( 'poly5')
plt.plot( xs, ys_poly5)
plt.show
 
# compute linear combination of polynomials
poly3_sum = np.sum( poly3, 0)
poly5_sum = np.sum( poly5, 0)

ys_poly3_sum = np.ones( xss.shape) * poly3_sum[-1]
# naive and inefficient way 
for k in range( poly3.shape[0]-2, -1, -1) :
    ys_poly3_sum = xss * ys_poly3_sum + poly3_sum[k]

ys_poly5_sum = np.ones( xss.shape) * poly5_sum[-1]
# naive and inefficient way 
for k in range( poly5.shape[0]-2, -1, -1) :
    ys_poly5_sum = xss * ys_poly5_sum + poly5_sum[k]

# plot polynomials
plt.figure(2)
plt.title( 'poly3 and sum')
plt.plot( xs, ys_poly3)
plt.plot( xs, ys_poly3_sum)
plt.show
 
plt.figure(3)
plt.title( 'poly5 and sum')
plt.plot( xs, ys_poly5)
plt.plot( xs, ys_poly5_sum)
plt.show
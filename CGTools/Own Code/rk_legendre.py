#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Apr 23 11:49:44 2017

@author: lessig
"""

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate 

# Plot first 5 Legendre polynomials

N = 5

# construct list of Legendre polynomials    
Pls = [np.polynomial.Legendre(list(np.zeros(l)) + [1.0]) for l in range(N)]    

# construct reproducing kernel
# we work here 
# xbar = 2.0*np.random.rand() - 1.0
print( "xbar = ", xbar)
rkCoeffs = [((2.0*l+1.0)/2.0) * Pls[l](xbar) for l in range(N)] 
rk = np.polynomial.Legendre( rkCoeffs)

# generate random polynomial
pCoeffs = np.random.rand( N)
p = np.polynomial.Legendre( pCoeffs)

# plot 
fig = plt.figure()
ax = plt.gca()    
(xs,ys) = rk.linspace( 1000)
ax.plot( xs, ys, 'r')
(xs,ys) = p.linspace( 1000)
ax.plot( xs, ys, 'b')
plt.axvline(x=xbar, color='r')
plt.legend( ["rk(x)", "p(x)"])
plt.grid( True)
plt.savefig( "rk_legendre.pdf")
plt.show()

# compute inner product of p with reproducing kernel
ip = scipy.integrate.quad( p * rk, -1.0, 1.0)[0]

# errror analyssi
err = np.abs( ip - p(xbar))
print( "error = ", err)


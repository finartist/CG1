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

fig = plt.figure()
ax = plt.gca()    
for l in range(N) :
    (xs,ys) = Pls[l].linspace(100)
    plt.plot( xs, ys)
plt.legend( list(range(N)), bbox_to_anchor=(1.05, 1), loc=2)
plt.grid( True)
plt.show()
    
# Check orthogonality
C = np.zeros( (N,N))
for i in range(N) :
    for j in range(N) :
        C[i,j] = scipy.integrate.quad( Pls[i] * Pls[j], -1.0, 1.0)[0]

plt.spy( C, precision=10.0*np.finfo("float64").eps)
plt.show()
print( "\n diag(C) = ", np.diag(C))

# Construct basis matrix 

def ipms( m, k) :
    """
    inner product of monomials
    """
    exp = k+m+1
    if 0 == (exp % 2) : 
        return 0
    return 1.0/exp * 2.0

# basis matrix
B = np.zeros( (N,N))

# for all Legendre polynomials 
for l in range(N) :
    
    # convert Legendre polynomial to monomial basis
    pm = Pls[l].convert( kind=np.polynomial.Polynomial)
    # get coefficients and normalize
    pm_coeffs = np.sqrt((2.0*l+1.0)/2.0) * pm.coef
    
    # for all monomials
    for n in range(N):
        
        # accumulate integral by linearity
        intval = 0.0 
        for k in range(len(pm.coef)) :
            intval += pm_coeffs[k] * ipms(n,k)
            
        B[l,n] = intval
         
print( "\n det(B) = ", np.linalg.det(B))
Btilde = np.linalg.inv(B)
         
for l in range(N) : 
    Btilde[:,l] *= np.sqrt((2.0*l+1.0)/2.0)

# the rows of Btilde are the columns of the Legendre polynomials
mtilde = [np.polynomial.Legendre(Btilde[j,:]) for j in range(N)] 

# plot dual functions
fig = plt.figure()
ax = plt.gca()    
for l in range(N) :
    (xs,ys) = mtilde[l].linspace(100)
    plt.plot( xs, ys)
plt.legend( list(range(N)), bbox_to_anchor=(1.05, 1), loc=2)
plt.grid( True)
plt.show()

# Check biorthogonality condition
biorthoCheck = np.zeros( (N,N))
for i in range(N) :
    for j in range(N) :
        biorthoCheck[i,j] = scipy.integrate.quad(lambda x: np.power(x,i) * mtilde[j](x), -1.0, 1.0)[0]

print( "\n Biorthogonality condition: \n", biorthoCheck)
plt.spy( biorthoCheck, precision=100.0*np.finfo("float64").eps)
plt.show()

# verify for example 

# Construct random polynomial
poly = np.polynomial.Polynomial( np.random.randn( N))
coeffs = [scipy.integrate.quad(lambda x: mtilde[j](x) * poly(x), -1.0, 1.0)[0] for j in range(N)]

err_coeffs = np.linalg.norm( poly.coef - np.array(coeffs))
print( '\n err_coeffs = ', err_coeffs)

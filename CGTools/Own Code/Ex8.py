#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Apr 23 11:49:44 2017

@author: lessig
"""

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate 

# Legendre Polynome von Grad 4
N = 5

# construct list of Legendre polynomials    
Pls = [np.polynomial.Legendre(list(np.zeros(l)) + [1.0]) for l in range(N)]    

#lambdas
lambdais = np.linspace(0, 1, 5)

# construct reproducing kernel
rkCoeffs = [((2.0*l+1.0)/2.0) * Pls[l](lambdais) for l in range(N)] 

#k als Koeffizientenmatrix bzgl Legendrepolynomen
K = np.array( rkCoeffs)
#duale basis zu k als koeffizenten
Ktilde = np.linalg.inv(K)

#Integral der LegendrePolynome = 0 außer für die konstante Fkt,
#ws Gewichte sind nur ein Skalar
ws = Ktilde[:,0] * np.sqrt(2.0)

def quadPN(func):
    fvals = func(lambdais)
    return np.dot(fvals, ws)

#test
p = np.poly1d(np.random.randn(N))
#unsere integration
ival = quadPN(p)
#python integration
ivalref = scipy.integrate.quad(p, -1, 1)

#fehler
diff = np.abs(ival-ivalref[0])
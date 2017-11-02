# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 11:07:33 2017

@author: User
"""

import numpy as np
import matplotlib.pyplot as plt
import numpy.linalg as la
import scipy.special
import spherical_harmonics

#1
xs = np.linspace(-1.0, 1.0, 1024)

for l in range(0,5):
    fig = plt.figure(l)
    #m ist eigentlich von -l bis l
    #m negativ nur gespiegelt und skaliert
    for m in range(0, l+1):
        ys = scipy.special.lpmv(m, l, xs) #(m,l, x) in unserer Notation
        plt.plot(xs,ys)

#3
L =16
p=16

coeffs = np.zeros([L+1])

#intergal für m != 0 wird 0 (da abhängigkeit von phi e^-m*phi 1 für m = 0 sonst verschwindet integral -> maximal 1 nicht 0 coeff
for l in range(0, L+1):
    coeffs[l] = spherical_harmonics.coeffsPhong(l,0,p)





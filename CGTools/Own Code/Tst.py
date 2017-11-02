# -*- coding: utf-8 -*-
"""
Created on Thu May  4 21:34:01 2017

@author: Lisa
"""

import numpy as np
import scipy
import matplotlib.pyplot as plt

N = 5
a = 30
Pls=[]
R = [[np.cos(np.radians(a)), np.sin(np.radians(a)),0,0,0],[-np.sin(np.radians(a)), np.cos(np.radians(a)), 0,0,0],[0,0,1,0,0],[0,0,0,1,0],[0,0,0,0,1]]
np.transpose(R)
for l in range(N):
    #legendre koeffizienten
    coeffs = R[l]
    Pls.append(np.polynomial.Legendre(coeffs))#*np.sqrt((2.0*l +1.0)/2.0))
    xs = np.linspace(-1.0, 1.0, 1024)
    ys = Pls[l](xs)
    plt.plot(xs,ys)

plt.legend(list(range(N)))
plt.show()
    
C=np.zeros((N,N)) #Kovarianzmatrix
for i in range(N):
    for j in range(N):
        C[i,j] = scipy.integrate.quad(Pls[i]*Pls[j], -1.0, 1.0)[0]
        
print(scipy.integrate.quad(lambda x : np.power(x,2), -1.0, 1.0))
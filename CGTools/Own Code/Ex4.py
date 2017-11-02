# -*- coding: utf-8 -*-
"""
Created on Thu May  4 11:17:50 2017

@author: User
"""

import numpy as np
import matplotlib.pyplot as plt
import scipy
#DirakDeltaxquer(f) ist nach Riez Theorem repräsentiert durch Funktion kxquer
#inneres Produkt <kxquer,f> = f(xquer)
#kxquer ist reproduzierender Kern
#kxquer kann so sehr einfach in Basisrepräsentation gebracht werden, da man nicht integrieren muss

#2
#_______
#| | | |
#H bildet orthogonale basis, aber nicht orthonormal
#Dirak Delta ist kontinuierlich, da Spruenge an festen Punkten als nicht verschiebbar
#alle endlichen Raume und raum indem sowohl funktion als auch ableitung drin ist, ist dd kontinuierlich

N = 5
Pls = []
kcoeffs = []
xquer = (np.random.rand()*2.0) -1.0
#4
#fancy variante Pls = [np.polynomial.Legendre(list(np.zeros(l))+[1.0]) for l in range(N)]
#kcoeffs = [Pls[l](xquer) for l in range(N)]
for l in range(N):
    #legendre koeffizienten
    coeffs = np.zeros(N)
    coeffs[l] = 1
    Pls.append(np.polynomial.Legendre(coeffs)) #Basis Legendre Polynome
    kcoeffs.append(Pls[l](xquer)*((2.0*l+1)/2.0))
    
rk = np.polynomial.Legendre(kcoeffs)

(xs,ys) = rk.linspace(1000)
plt.plot(xs,ys)
plt.axvline(xquer, color='r')

#zufaellige Koeffizienten fuer ein Polynom in Legendre Darstellung
p = np.polynomial.Legendre(np.random.randn(N))*np.sqrt((2.0*l +1.0)/2.0)

ip = scipy.integrate.quad(rk *p, -1.0, 1.0)
err = np.abs(p(xquer)-ip[0]) #Fehlerabschätzung
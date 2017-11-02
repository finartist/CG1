# -*- coding: utf-8 -*-
"""
Created on Thu Apr 27 11:27:03 2017

@author: Lisa
"""

import numpy as np
import scipy.integrate
import matplotlib.pyplot as plt

N = 5
Pls = []
#1
for l in range(N):
    #legendre koeffizienten
    coeffs = np.zeros(N)
    coeffs[l] = 1
    Pls.append(np.polynomial.Legendre(coeffs)*np.sqrt((2.0*l +1.0)/2.0)) #Normierte Legendre Polynome

    #evaluate
    xs = np.linspace(-1.0, 1.0, 1024)
    ys = Pls[l](xs)
    plt.plot(xs,ys)

plt.legend(list(range(N)))
plt.show()
print(Pls[0](0))
#2
#integriere legendre polynome numerisch um herauszufinden, 
#ob diese orthogonal sind(über inneres Produkt)
#num Integration vorsicht bei Polynomen, die stark oszillieren oder nicht konvergieren
C=np.zeros((N,N)) #Kovarianzmatrix
for i in range(N):
    for j in range(N):
        C[i,j] = scipy.integrate.quad(Pls[i]*Pls[j], -1.0, 1.0)[0] #gibt an stelle 0 den integrationswert, 1 den fehler
#Matrizen besser ansehen (weiß = 0, schwarz != 0)
#plt.spy(C, precision = np.finfo(np.float64).eps) #ohne Angabe von Maschinengenauigkeit Schachbrettmuster
#-> sind außer auf der Diagonale überall 0
print(C[0,0])
#ABER Diagonale ist nicht 1!!! wenn nicht normiert(np.sqrt((2.0*l +1.0)/2.0)) -> nicht orthonormal (Länge müsste == 1)

#1
B=np.zeros((N,N)) #Kovarianzmatrix ("Wie korrelieren die Funktionen?")
for i in range(N):
    for j in range(N):
        #B Koeffizientenmatrix zur Darstellung von f als Summe von Legendrepolynomen
        B[j,i] = scipy.integrate.quad(lambda x : np.power(x,i) * (Pls[j](x)), -1.0, 1.0)[0]# * (2*j+1)/2

#2 Given B verify that the Legendre polynomials indeed span the same space as the monomials
print(np.linalg.det(B)) #!=0

#3 Construct and plot the dual basis functions ~xn for the monomials
#Btilde Koeffizientenmatrix
Btilde = np.linalg.inv(B)

for l in range(N) : 
    Btilde[:,l] *= np.sqrt((2.0*l+1.0)/2.0)
    
#Primal Base and Dual base polynomials
Id = B*Btilde
D = []
PrimalBase = []
for i in range(N):
    #Zeilen von Btilde
    D.append(np.polynomial.Legendre(Btilde[i,:]))
    #Spalten von Primär Basis
    PrimalBase.append(np.polynomial.Legendre(B[:,i]))
    #print(PrimalBase)
    ys = D[i](xs)
    plt.plot(xs, ys)
plt.show()

#4 Verify that the constructed functions ~xn satisfy the biorthogonality condition using numerical integration.
F=np.zeros((N,N))
for i in range(N):
    for j in range(N):
        F[j,i] = scipy.integrate.quad(lambda x: np.power(x,i) * D[j](x), -1.0, 1.0)[0]

#5
#30 + 3x +2x^2 + 5x^3 + x^4 -> [30, 3, 2, 5,1]
MonomCoeffs = [30,3,2,5,1]
ys = MonomCoeffs[4]
for k in range(3, -1, -1):
   ys = ys*xs + MonomCoeffs[k]
plt.plot(xs,ys)
plt.show()

b=B.transpose()
MonomCoeffsDualBase = np.dot(b, MonomCoeffs)
ys = 0
for i in range(N):
    ys += MonomCoeffsDualBase[i]*D[i](xs)
plt.plot(xs,ys)
plt.show()
        
        
        
        
        
        
        
        
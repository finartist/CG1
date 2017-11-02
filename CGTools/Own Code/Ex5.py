# -*- coding: utf-8 -*-
"""
Created on Thu May 11 11:17:13 2017

@author: User
"""

import numpy as np
import matplotlib.pyplot as plt
import numpy.linalg as la

def plotVectors2D( vs, col='black') :
    """
    Plot 2D vectors into fig. vs is expected contain the vectors in its columns
    """
    ax = plt.gca()
    for i in range(vs.shape[0]) :
        ax.quiver( 0.0, 0.0, vs[i,0], vs[i,1], angles='xy', scale_units='xy', scale=1, color=col)
    maxaxis = 1.2 * np.max( np.abs(vs))
    ax.set_xlim( [-maxaxis, maxaxis])
    ax.set_ylim( [-maxaxis, maxaxis])
    plt.axes().set_aspect('equal')
    
#1 equiangular Frame (Vektoren haben gleichen Winkel)
A = np.array([[0.0, 1.0], [np.sqrt(3.0)/2, -1/2], [-np.sqrt(3.0)/2, -1/2]]).transpose()

plotVectors2D(A.transpose())
plt.show()
#2
#Frame Operator S=A*Atranspose
#A hat u1 bis um in den Spalten
#Atilde = Atranspose*Sinvers
S = np.dot(A, A.transpose())
#utilde i = Sinvers * ui
Sinvers = np.linalg.inv(S)
#Atilde = Atranspose*Sinvert
#Atildetranspose = Sinvers * A
Atilde = np.dot(Sinvers, A).transpose()

plotVectors2D(Atilde)
plt.show()

#Atilde ist rechte inverse von A also id = A*Atilde
print(np.dot(A, Atilde))
# tight frame -> utildei = c*ui
# v = 1/r *sum(<v,ui>ui) r = 1/c (c=n/m) verallgemeinerung orthogonalbasis r=redundancy

#4
#Parseval Frame
# v = sum(<v,ui>ui) verallgemeinerung der orthonormal basis
#Faktor fuer parseval wenn man schon tight frame hat sqrt(1/r)

Aparseval = np.sqrt(2.0/3.0) * A
Sparseval = np.dot(Aparseval, Aparseval.transpose())
Spinvers = np.linalg.inv(Sparseval)
Aparsevaltilde = np.dot(Spinvers, Aparseval).transpose()

err = np.linalg.norm(Aparseval - Aparsevaltilde.transpose())
isParseval = (err < 10.0*np.finfo('float64').eps)
print(isParseval)

#frame      basis
#allgemein  biorthogonal
#tight      orthogonal
#parseval   orthonormal
#orthogonale und orthonormale matrizen gut zu invertieren 
#5
'''
N = 100
errs = []
num_r_trials = 100
for r in np.linspace(1.0, 3.0, 20):
    #r = redundancy
    err = 0.0
    #Schleifen um den average zu bekommen -> funktion wird glatter
    for j in range(num_r_trials):
        A = np.random.randn(N, np.ceil(r*N))

        S = np.dot(A, A.transpose())
        Sinvers = np.linalg.inv(S)
        Atilde = np.dot(Sinvers, A).transpose()

        for i in range(num_r_trials):
            v = np.random.randn(N)
            vrec = np.dot(A, np.dot(Atilde,v))
            err += np.linalg.norm(v-vrec)
    errs.append(err/(num_r_trials*num_r_trials))

#plotted im logarithmus, damit Kurve besser zu sehen
plt.semilogy(np.linspace(1.0,3.0,20), errs)
'''




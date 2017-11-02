# -*- coding: utf-8 -*-
"""
Created on Thu Apr 20 11:04:15 2017

@author: Lisa
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
    

#1
#u1 = np.random.randn(2) #2 zufällige Vektoren
#u2 = np.random.randn(2)
#
##2
#B = np.array([u1, u2]).transpose() #Basis mit u1,u2 als Spaltenvektoren(transpose)
#Btilde = la.inv(B)
#
##3
#v1 = np.random.randn(2) #zufälliger vektor in Orthogonalbasis
#vcoeffs_u = np.dot(Btilde, v1) #vektor in basis u
#vrec = np.dot(B, vcoeffs_u) #theoretisch rekonstruierter vektor
##zum Vergleichen: entweder Skalarprodukt oder Differenz
#err = la.norm(v1-vrec)
#print("err= ", err) #Fehler sollte ungefähr Maschinengenauigkeit betragen
#
##4
##die Basen können vertauscht werden, müssen dann nur transponiert werden
#
#
##5
#plotVectors2D(B.transpose()) #nochmal transpose, da plotvectors die zeilenvektoren darstellt
#plotVectors2D(Btilde, 'blue')

#1
detsB = []
errs = []
for alpha in (np.linspace(90.0, 180.0, 10) *np.pi) / 180 :
    u1 = np.array([1.0 ,0.0])
    rot = np.array([[np.cos(alpha), -np.sin(alpha)], [np.sin(alpha), np.cos(alpha)]])
    u2 = np.dot(rot,u1)
    
    B = np.array([u1, u2]).transpose()
    Btilde = la.inv(B)
    
    #det wird nicht 0, wegen Maschinengenauigkeit
    #dualvektoren werden seeeehr groß bei det nahe 0
    detsB.append(la.det(B))
    print("detsB = ",detsB)
    
    #Fehler wird sehr gr0ß bei det nahe 0
    #warum ist orthogonale basis besser -> in großen Räumen numerischer Fehler kleiner
    v1 = np.random.randn(2) #zufälliger vektor in Orthogonalbasis
    vcoeffs_u = np.dot(Btilde, v1) #vektor in basis u
    vrec = np.dot(B, vcoeffs_u)
    errs.append(la.norm(v1-vrec))
    print("errs = ", errs)
    
    plotVectors2D(B.transpose()) 
    plotVectors2D(Btilde, 'blue')
    plt.show()
    
    input("Press key")









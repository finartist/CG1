#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Oct 15 17:49:01 2017

@author: lessig
"""

import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt

###############################################################################
# function definitions

###############################################################################
def s2tor3( omega) :
    r3 = np.zeros( 3)
    r3[0] = np.sin( omega[0]) * np.cos( omega[1])
    r3[1] = np.sin( omega[0]) * np.sin( omega[1])
    r3[2] = np.cos( omega[0])
    return r3

###############################################################################
def s2tor3array( omega) :
    r3 = np.zeros( [omega.shape[0], 3])
    r3[:,0] = np.sin( omega[:,0]) * np.cos( omega[:,1])
    r3[:,1] = np.sin( omega[:,0]) * np.sin( omega[:,1])
    r3[:,2] = np.cos( omega[:,0])
    return r3

###############################################################################
def ell( omega) :
    return np.max( [0.0, np.cos( omega[0])])

###############################################################################
def getUniformPointS2() :

    # generate random point in [0,1]^2
    omega = np.random.rand(2)
    
    #omega = np.arccos(omega)
    
    omega[0] = np.arccos(omega[0])
    omega[1] *= np.pi*2

    return omega

###############################################################################
# integration

N = 128
K = 20
Ns = 10
integrals = np.zeros([Ns,K])
for j in range(Ns):
    N*=2
    var = 0
    lastintegral = 0
    integral = 0
    for k in range(K):    
        omegas = np.array([getUniformPointS2() for i in range(N-1)])
        currintegral = 2*np.pi/N * np.sum([ell(omegas[i]) for i in range(N-1)])
        var += np.abs(currintegral-lastintegral)
        lastintegral = currintegral
        integral += lastintegral
        integrals[j,k] = integral
    var /= 20
    integral /= 20
    print( 'integral = ', integral, ' varianz = ', var, ' for N = ', N, " samples")

###############################################################################
# plot points

plt.plot(np.var(integrals, 1))

fig = plt.figure(figsize=plt.figaspect(0.5)*1.5)
ax = fig.add_subplot(111, projection='3d')
ax.set_aspect('equal',)
ax.set_xlim( -1.1, 1.1)
ax.set_ylim( -1.1, 1.1)
ax.set_zlim( -1.1, 1.1)
ax.grid(False)
for a in (ax.w_xaxis, ax.w_yaxis, ax.w_zaxis):
    for t in a.get_ticklines()+a.get_ticklabels():
        t.set_visible(False)
    a.line.set_visible(False)
    a.pane.set_visible(False)

omegasr3 = s2tor3array( omegas)
ax.scatter( omegasr3[:,0], omegasr3[:,1], omegasr3[:,2])

# Make data
u = np.linspace(0, 2 * np.pi, 100)
v = np.linspace(0, np.pi, 100)
x = 0.99 * np.outer(np.cos(u), np.sin(v))
y = 0.99 * np.outer(np.sin(u), np.sin(v))
z = 0.99 * np.outer(np.ones(np.size(u)), np.cos(v))
# Plot the surface
ax.plot_surface(x, y, z, color='w')

plt.show()

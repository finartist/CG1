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

# point on sphere to r3 room
def s2tor3( omega) :
    r3 = np.zeros( 3)
    r3[0] = np.sin( omega[0]) * np.cos( omega[1])
    r3[1] = np.sin( omega[0]) * np.sin( omega[1])
    r3[2] = np.cos( omega[0])
    return r3

# points on sphere to r3
def s2tor3array( omega) :
    r3 = np.zeros( [omega.shape[0], 3])
    r3[:,0] = np.sin( omega[:,0]) * np.cos( omega[:,1])
    r3[:,1] = np.sin( omega[:,0]) * np.sin( omega[:,1])
    r3[:,2] = np.cos( omega[:,0])
    return r3

def ell( omega) :
    return np.max( [0.0, np.cos( omega[1])])

def getUniformPointS2() :

    # generate random point in [0,1]^2
    omega = np.random.rand(2)
    
    omega[0] = np.arccos(omega[0])
    omega[1] *= np.pi * 2
    
    return omega

###############################################################################
# integration

N = 1000
#omegas = np.zeros( [N, 2])
omegas = np.array([getUniformPointS2() for i in range(N)])

#integral = 0   
       
#for i in range(N):
#    omegas[i,:] = getUniformPointS2()
#    integral += np.cos(omegas[i, 0])
    
#Formula is 1/N * Volume * sum(cos(theta) on N sample points)
# right answer should be pi
#integral *=  1/N * 2* np.pi
      
K = 10
Ns = np.array(2**np.linspace(6,13,8), dtype = np.int32)
integrals = np.zeros([Ns.size, K])
varis = np.zeros([Ns.size])              
for idx in range(Ns.size):
    N = Ns[idx]
    #variance definition = mean(abs(x - x.mean())**2)
    variance = 0
    for j in range(K):
        omega = np.array([getUniformPointS2() for i in range(N)])
        integrals[idx, j] = np.sum(np.cos(omega[i, 0]) for i in range(N))
    integrals[idx, :] *= np.pi * 2 * 1/N
    variance = np.var(integrals[idx,:])
    varis[idx] = variance
    print("var = ", variance)

#print( 'integral = ', integral)

###############################################################################
# plot points

plt.plot(varis)

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

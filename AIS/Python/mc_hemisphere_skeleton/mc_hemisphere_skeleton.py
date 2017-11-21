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
    return np.max( [0.0, np.cos( omega[0])])

def getUniformPointS2() :

    # generate random point in [0,1]^2
    omega = np.random.rand(2)
    
    #theta
    omega[0] = np.arccos(omega[0])
    #phi
    omega[1] *= np.pi * 2
    
    return omega

def getpxdistributedPointS2() :

    # generate random point in [0,1]^2
    omega = np.random.rand(2)
    omega *= 2
    omega -= 1
    r = np.sqrt(omega[0]*omega[0] + omega[1]*omega[1])
    #omega has to be in disk
    while r > 1:
        omega = np.random.rand(2)
        omega *= 2
        omega -= 1
        r = np.sqrt(omega[0]*omega[0] + omega[1]*omega[1])
    
    xaxisvector = np.array([1.0, 0.0])
    omega = omega/np.linalg.norm(omega)
    #get phi by computing the angle between x axis and random point vector
    phi = np.arccos(np.dot(omega, xaxisvector))
    #print(phi)
    
    #if the angle is > 180° (= pi) correct value (arccos just gives pos values 0 to pi) 
    if omega[1] < 0:
        phi = 2*np.pi - phi
    
    #return the actual value
    omega = np.array([np.arcsin(r), phi])
    
    return omega
    
###############################################################################
# integration monte carlo
print("original monte carlo")

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
#want to compute integral for different numbers N of sample points
Ns = np.array(2**np.linspace(6,13,8), dtype = np.int32)
integrals = np.zeros([Ns.size, K])
errors = np.zeros(Ns.size)
varis = np.zeros([Ns.size])              
for idx in range(Ns.size):
    N = Ns[idx]
    #variance definition = mean(abs(x - x.mean())**2)
    variance = 0
    #compute K time the integral with N random points via monte carlo
    for j in range(K):
        omega = np.array([getUniformPointS2() for i in range(N)])
        integrals[idx, j] = np.sum(np.cos(omega[i, 0]) for i in range(N))
    integrals[idx, :] *= np.pi * 2 * 1/N
    errors[idx] = np.sum(integrals[idx, :] - np.pi) / K
    #compute variance for the K integrals
    variance = np.var(integrals[idx,:])
    varis[idx] = variance
    print("var = ", variance)

#plot variance
plt.plot(varis)
plt.show()
#plot error
plt.plot(errors)
plt.show()
# -> we see that for higher N, the variance gets lower -> closer to real integral value for higher N
#print( 'integral = ', integral)

###############################################################################
###############################################################################
# integration other distribution

print("other distributed point")

print("To Do: change integral computation to 1/N * Volume (2*pi) * sum(cos(thetai)/p(xi)) on N sample points")
#Where p(x) = c*g(x) = 1/integral(g(x)) * g(x)  -> need to choose a function g(x) which is similar to f(x)
#if g(x) == f(x) one would need only one sample point, but then i would already now the integral

N = 1000
#omegas = np.zeros( [N, 2])
omegas = np.array([getpxdistributedPointS2() for i in range(N)])
#integral = 0   
       
#for i in range(N):
#    omegas[i,:] = getUniformPointS2()
#    integral += np.cos(omegas[i, 0])
    
#Formula is 1/N * Volume (2*pi) * sum(cos(theta) on N sample points)
# right answer should be pi
#integral *=  1/N * 2* np.pi
      
K = 10
#want to compute integral for different numbers N of sample points
Ns = np.array(2**np.linspace(6,13,8), dtype = np.int32)
integrals = np.zeros([Ns.size, K])
errors = np.zeros(Ns.size)
varis = np.zeros([Ns.size])              
for idx in range(Ns.size):
    N = Ns[idx]
    #variance definition = mean(abs(x - x.mean())**2)
    variance = 0
    #compute K time the integral with N random points via monte carlo
    for j in range(K):
        omega = np.array([getpxdistributedPointS2() for i in range(N)])
        integrals[idx, j] = np.sum(np.cos(omega[i, 0])/(np.cos(omega[i, 0])/np.pi) for i in range(N))
    integrals[idx, :] *= 1/N
    #get mean error for all integrals in K
    errors[idx] = np.sum(integrals[idx, :] - np.pi)/K
    print(np.sum(integrals[idx, :] - np.pi))
    #compute variance for the K integrals
    variance = np.var(integrals[idx,:])
    varis[idx] = variance
    print("var = ", variance)

#plot variance
plt.plot(varis)
plt.show()
#plot error
plt.plot(errors)
plt.show()

# -> we see that for higher N, the variance gets lower -> closer to real integral value for higher N
#print( 'integral = ', integral)

###############################################################################

# plot points on sphere

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

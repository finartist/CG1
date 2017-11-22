# -*- coding: utf-8 -*-
"""
Created on Tue Oct 17 14:07:20 2017

@author: User
"""

#week6 task

import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
import lightprobe
import sobol_seq


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

def getUniformPointH2() :

    # generate random point in [0,1]^2
    omega = np.random.rand(2)
    
    #theta
    omega[0] = np.arccos(omega[0])
    #phi
    omega[1] *= np.pi * 2
    
    return omega

def getCosineDistributedPointH2() :

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

def getStratifiedPointsH2(n, num_stratas):
    num_stratas = np.int32(num_stratas)
    samplesPerStrata = np.int32(n/num_stratas)
    
    omega = np.zeros([n, 2])
    
    for i in range(num_stratas):
        for j in range(samplesPerStrata):
            rand = np.random.rand(2)
            rand[1] = rand[1]/num_stratas + i/num_stratas
            omega[i*samplesPerStrata+j, 0] = np.arccos(rand[0])
            omega[i*samplesPerStrata+j, 1] = rand[1] * 2 * np.pi
                 
    return omega

def getSobolSequencePointsH2(n):
    
    sobolpoints = sobol_seq.i4_sobol_generate(2, n)
    
    sobolpoints[:, 0] = np.sqrt(sobolpoints[:, 0])
    sobolpoints[:, 1] = sobolpoints[:, 1] * 2 * np.pi
               
    #map to hemisphere
    sobolpoints[:, 0] = np.arccos(sobolpoints[:,0])
    
    return sobolpoints
    """
    sobolpoints = np.zeros([n, 2])
    seed = 1
    for i in range(n):
        sobolpoints[i,:] = sobol_seq.i4_sobol(2, seed)[0]
        seed += 1
    sobolpoints[:, 0] = np.sqrt(sobolpoints[:, 0])
    sobolpoints[:, 1] = sobolpoints[:, 1] * 2 * np.pi
               
    #map to hemisphere
    sobolpoints[:, 0] = np.arccos(sobolpoints[:,0])
               
    return sobolpoints"""

def uniformSamplingMonteCarlo(Ns,K):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])              
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N random points via monte carlo
        for j in range(K):
            omega = np.array([getUniformPointH2() for i in range(N)])
            integrals[idx, j] = np.sum(lightprobe.value(probe, omega[i,:])*np.max([0.0, np.cos(omega[i, 0])]) for i in range(N))
        integrals[idx, :] *= np.pi * 2 * 1/N
        errors[idx] = np.sum(integrals[idx, :] - 492.3) / K
        #compute variance for the K integrals
        variance = np.var(integrals[idx,:])
        varis[idx] = variance
        print("var = ", variance)
        
    return [integrals, varis, errors]

def cosineImportanceSampling(Ns, K):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])              
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N random points via monte carlo
        for j in range(K):
            omega = np.array([getCosineDistributedPointH2() for i in range(N)])
            integrals[idx, j] = np.sum(lightprobe.value(probe, omega[i,:])*np.max([0.0, np.cos(omega[i, 0])])/(np.cos(omega[i,0])/np.pi) for i in range(N))
        integrals[idx, :] *= 1/N
        #get mean error for all integrals in K
        errors[idx] = np.sum(integrals[idx, :] - 492.3)/K
        #compute variance for the K integrals
        variance = np.var(integrals[idx,:])
        varis[idx] = variance
        print("var = ", variance)
    return [integrals, varis, errors]

def stratifiedSampling(Ns, K):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N random points via monte carlo
        for j in range(K):
            omega = np.array(getStratifiedPointsH2(N, N))
            integrals[idx, j] = np.sum(lightprobe.value(probe, omega[i,:])*np.max([0.0, np.cos(omega[i, 0])]) for i in range(N))
        integrals[idx, :] *= 2 * np.pi * 1/N
        #get mean error for all integrals in K
        errors[idx] = np.sum(integrals[idx, :] - 492.3)/K
        #compute variance for the K integrals
        variance = np.var(integrals[idx,:])
        varis[idx] = variance
        print("var = ", variance)
    return [integrals, varis, errors]

def lowDiscrepancySequence(Ns, K):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])              
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N random points via monte carlo
        for j in range(K):
            omega = np.array(getSobolSequencePointsH2(N))
            integrals[idx, j] = np.sum(lightprobe.value(probe, omega[i,:])*np.max([0.0, np.cos(omega[i, 0])]) for i in range(N))
        integrals[idx, :] *= np.pi * 2 * 1/N
        errors[idx] = np.sum(integrals[idx, :] - 492.3) / K
        #compute variance for the K integrals
        variance = np.var(integrals[idx,:])
        varis[idx] = variance
        print("var = ", variance)
        
    return [integrals, varis, errors]
###############################################################################
# parameter initialisation

Ns = np.array(2**np.linspace(6,13,8), dtype = np.int32)
K = 10
probe = lightprobe.load("data/uffizi.png");
                       
###############################################################################
# calculate integral with different distributions

print("original monte carlo, uniform sampling")
[uniintegrals, univariance, unierrors] = uniformSamplingMonteCarlo(Ns, K)

print("cosine weighted importance sampling")
[cosintegrals, cosvariance, coserrors] = cosineImportanceSampling(Ns, K)

print("stratified sampling")
[stratintegrals, stratvariance, straterrors] = stratifiedSampling(Ns, K)

print("low discrepancy sequences")
[lowdisintegrals, lowdisvariance, lowdiserrors] = lowDiscrepancySequence(Ns, K)
###############################################################################
# plot

plt.plot(univariance, label="Uniform")
plt.plot(cosvariance, label="Cosine Weighted")
plt.plot(stratvariance, label="Stratified")
plt.plot(lowdisvariance, label="Low Discrepancy")
plt.legend()
plt.title("Variance")
plt.show()

plt.plot(unierrors, label="Uniform")
plt.plot(coserrors, label="Cosine Weighted")
plt.plot(straterrors, label="Stratified")
plt.plot(lowdiserrors, label="Low Discrepancy")
plt.legend()
plt.title("Errors")
plt.show()

###############################################################################
omegas = getStratifiedPointsH2(1024, 1024)

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












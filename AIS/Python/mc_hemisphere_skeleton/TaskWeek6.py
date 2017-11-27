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
    #see course notes for where this formula comes from    
    omega = np.random.rand(2)
    omega[0] = np.arcsin(np.sqrt(omega[0]))
    omega[1] *= 2*np.pi
    return omega

def getStratifiedPointsH2(n, num_stratas, phi = False, theta = True):

    num_stratas = np.int32(num_stratas)
    samplesPerStrata = np.int32(n/num_stratas)
        
    omega = np.zeros([n, 2])
    
    if (phi and theta):
        num_stratasPhi = 8
        num_stratasTheta = np.int32(num_stratas/num_stratasPhi)
        
        index = 0
        
        #slice sphere in theta and phi
        for i in range(num_stratasPhi):
            for j in range(num_stratasTheta):
                for k in range(samplesPerStrata):
                    rand = np.random.rand(2)
                    rand[0] = np.arccos(rand[0]/num_stratasTheta + j/num_stratasTheta)
                    rand[1] = rand[1]/num_stratasPhi + i/num_stratasPhi
                    omega[index, 0] = rand[0]
                    omega[index, 1] = rand[1] * 2 * np.pi
                    index += 1
                         
        return omega
    
    elif (phi):
        #sphere in num_stratas slices in phi direction
        for i in range(num_stratas):
            for j in range(samplesPerStrata):
                rand = np.random.rand(2)
                rand[1] = rand[1]/num_stratas + i/num_stratas
                omega[i*samplesPerStrata+j, 0] = np.arccos(rand[0])
                omega[i*samplesPerStrata+j, 1] = rand[1] * 2 * np.pi
                         
        return omega
    
    else:
        #sphere in num_stratas slices in theta direction
        for i in range(num_stratas):
            for j in range(samplesPerStrata):
                rand = np.random.rand(2)
                omega[i*samplesPerStrata+j, 0] = np.arccos(rand[0]/num_stratas + i/num_stratas)
                omega[i*samplesPerStrata+j, 1] = rand[1] * 2 * np.pi
                     
        return omega

    
def getSobolSequencePointsH2(n):
    
    sobolpoints = sobol_seq.i4_sobol_generate(2, n)
    
    sobolpoints[:, 1] = sobolpoints[:, 1] * 2 * np.pi
               
    #map to hemisphere
    sobolpoints[:, 0] = np.arccos(sobolpoints[:,0])
    
    return sobolpoints

def getSobolCosinePointsH2(n):
    
    sobolpoints = sobol_seq.i4_sobol_generate(2, n)
    
    #map to hemisphere cosine weighted
    sobolpoints[:, 0] = np.arcsin(np.sqrt(sobolpoints[:,0]))
    sobolpoints[:, 1] = sobolpoints[:, 1] * 2 * np.pi
    
    return sobolpoints

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
        #compute K time the integral with N random cosine distributed points via monte carlo
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

def stratifiedSampling(Ns, K, phi = False, theta = True):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N random, but better distributed points via monte carlo
        for j in range(K):
            omega = np.array(getStratifiedPointsH2(N, N, phi, theta))
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
        #compute K time the integral with N deterministic points via monte carlo
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

def lowDiscrepancyCosineSequence(Ns, K):
    #want to compute integral for different numbers N of sample points
    integrals = np.zeros([Ns.size, K])
    errors = np.zeros(Ns.size)
    varis = np.zeros([Ns.size])              
    for idx in range(Ns.size):
        N = Ns[idx]
        #variance definition = mean(abs(x - x.mean())**2)
        variance = 0
        #compute K time the integral with N deterministic cosine distributed points via monte carlo
        for j in range(K):
            omega = np.array(getSobolCosinePointsH2(N))
            integrals[idx, j] = np.sum(lightprobe.value(probe, omega[i,:])*np.max([0.0, np.cos(omega[i, 0])])/(np.cos(omega[i,0])/np.pi) for i in range(N))
        integrals[idx, :] *= 1/N
        errors[idx] = np.sum(integrals[idx, :] - 492.3) / K
        #compute variance for the K integrals
        variance = np.var(integrals[idx,:])
        varis[idx] = variance
        print("var = ", variance)
        
    return [integrals, varis, errors]
###############################################################################
# parameter initialisation

Exponents = np.linspace(6,15,10);
Ns = np.array(2**Exponents, dtype = np.int32)
K = 10
probe = lightprobe.load("data/uffizi.png");
                      
###############################################################################
# calculate integral with different distributions

print("original monte carlo, uniform sampling")
[uniintegrals, univariance, unierrors] = uniformSamplingMonteCarlo(Ns, K)

print("cosine weighted importance sampling")
[cosintegrals, cosvariance, coserrors] = cosineImportanceSampling(Ns, K)

print("stratified sampling phi")
[stratphiintegrals, stratphivariance, stratphierrors] = stratifiedSampling(Ns, K, True, False)

print("stratified sampling theta")
[stratthetaintegrals, stratthetavariance, stratthetaerrors] = stratifiedSampling(Ns, K, False, True)

print("stratified sampling phi,theta")
[stratphithetaintegrals, stratphithetavariance, stratphithetaerrors] = stratifiedSampling(Ns, K, True, True)

print("low discrepancy sequences")
[lowdisintegrals, lowdisvariance, lowdiserrors] = lowDiscrepancySequence(Ns, K)

print("low discrepancy + cosine weighted")
[lowcosintegrals, lowcosvariance, lowcoserrors] = lowDiscrepancyCosineSequence(Ns, K)
###############################################################################
# plot graphs

axes = plt.gca()
axes.set_ylim([-100, 2000])
plt.plot(Exponents, univariance, label="Uniform")
plt.plot(Exponents, cosvariance, label="Cosine Weighted")
plt.plot(Exponents, stratphivariance, label="Stratified (Phi)")
plt.plot(Exponents, lowdisvariance, label="Low Discrepancy")
plt.legend()
plt.title("Variance")
plt.show()

axes = plt.gca()
axes.set_ylim([-15, 15])
plt.plot(Exponents, unierrors, label="Uniform")
plt.plot(Exponents, coserrors, label="Cosine Weighted")
plt.plot(Exponents, stratphierrors, label="Stratified (Phi)")
plt.plot(Exponents, lowdiserrors, label="Low Discrepancy")
plt.legend()
plt.title("Errors")
plt.show()

axes = plt.gca()
axes.set_ylim([-100, 2000])
plt.plot(Exponents, stratphivariance, label="Stratified (Phi)")
plt.plot(Exponents, stratthetavariance, label="Stratified (Theta)")
plt.plot(Exponents, stratphithetavariance, label="Stratified (Phi + Theta)")
plt.legend()
plt.title("Variance, Stratified comparison")
plt.show()

axes = plt.gca()
axes.set_ylim([-15, 15])
plt.plot(Exponents, stratphierrors, label="Stratified (Phi)")
plt.plot(Exponents, stratthetaerrors, label="Stratified (Theta)")
plt.plot(Exponents, stratphithetaerrors, label="Stratified (Phi + Theta)")
plt.legend()
plt.title("Errors, Stratified")
plt.show()

axes = plt.gca()
axes.set_ylim([-15, 15])
plt.plot(Exponents, lowdiserrors, label="Low Discrepancy")
plt.plot(Exponents, lowcoserrors, label="Low Discrepancy + Cosine")
plt.legend()
plt.title("Errors, Low Discrepancy")
plt.show()

###############################################################################
omegas = np.array(getStratifiedPointsH2(1024, 1024, True, True))

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












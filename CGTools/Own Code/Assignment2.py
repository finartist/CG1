# -*- coding: utf-8 -*-
"""
Created on Tue Jun 20 15:08:09 2017

@author: User
"""

import numpy as np
import scipy

#get vanderCorput points on sphere
def vanderCorput(n,b):
  """ 
  Generate point of low discrepancy van der Corput sequence
  (based on http://codegist.net/code/low-discrepancy-sequence-algorithm/)
  
  n : index of point
  b : base (small prime number)
  """
  m=k=np.real(0)
  
  while(n):
    m = m * b + n % b
    n = np.int64(n/b)
    k += 1
    
  return m * 1.0/ np.power(b,k)

#spherical harmonics with parameter l and m on point(theta, phi)
def ylm( l, m, theta, phi) :
    clm = np.sqrt( ((2*l+1) /(4.0*np.pi)) * (np.math.factorial(l-m) / np.math.factorial(l+m)))
    return clm * scipy.special.lpmv( m, l, np.cos(theta)) * np.exp(1j * m * phi)

"""
#integrate the function given with spherical harmonic coeffs over the sphere
def integrateSphericalHarmonics(L, coeffs):
    val = 0
    for l in range(0, L+1):
        for m in range(-l, l+1):
            integrandr = lambda theta, phi : ( ylm( l, m, theta, phi)
                                 * np.sin(theta)).real
            integrandi = lambda theta, phi : ( ylm( l, m, theta, phi)
                                 * np.sin(theta)).imag
    ivalr = scipy.integrate.nquad( integrandr, [[0, np.pi],[0,2*np.pi]])    
    ivali = scipy.integrate.nquad( integrandi, [[0, np.pi],[0,2*np.pi]])
    val +=  coeffs[np.power(l,2) + l + m] * (ivalr[0] + ivali[0])
            
    return val

"""
#map points to sphere
def mapToSphere(points):
    newPoints = []
    for i in range(0, len(points)):
        newPoints.append( (np.math.asin(2*points[i][0] - 1) + np.pi/2.0, 2*np.pi * points[i][1]) )
    return newPoints


def integrateOverSphere(L, lambdais, function):
    #n = np.power(L, 2) + 2*L + 1
    K = []
    funcValues = []
    lambdaValues = lambdais.transpose()
    
    #reproducing kernel basis in spherical harmonic coeffs
    for l in range(0, L+1):
        for m in range(-l, l+1):
            K.append(ylm( l, m, lambdaValues[0], lambdaValues[1]))
    #dual basis
    Ktilde = np.linalg.inv(K)
    vector = Ktilde[:,0] * 2 * np.sqrt(np.pi)
    #vector of integrals of reproducing kernel basis
    """vector = []
    for i in range(n):
        vector.append(integrateSphericalHarmonics(L, Ktilde[i]))
    """ 
    funcValues = function(lambdaValues[0], lambdaValues[1])
    return np.dot(funcValues,vector)

def weightedSphericalHarmonic(l,m,coeff):
    return lambda theta, phi: coeff*ylm(l, m, theta, phi)

def weightedSphericalHarmonicReal(l,m,coeff):
    return lambda theta, phi: (coeff*ylm(l, m, theta, phi)* np.sin(theta)).real

def weightedSphericalHarmonicImag(l,m,coeff):
    return lambda theta, phi: (coeff*ylm(l, m, theta, phi)* np.sin(theta)).imag

def makeRandomFunction(L):
    n = np.power(L,2) + 2*L + 1
    coeffs = np.random.randn(n)
    coeffs[0] = 0
    arguments = []
    for l in range(L+1):
        for m in range(-l, l+1):
            arguments.append((l,m,coeffs[l*l+l+m]))
    functs = [0,0,0]
    functs[0] = lambda theta, phi: sum(weightedSphericalHarmonic(*a)(theta, phi) for a in arguments)
    functs[1] = lambda theta, phi: sum(weightedSphericalHarmonicReal(*a)(theta, phi) for a in arguments)
    functs[2] = lambda theta, phi: sum(weightedSphericalHarmonicImag(*a)(theta, phi) for a in arguments)
    return functs

#TesttestTesttestTest
#***********************************************************************************************************
L = 5
pts = []
for i in range(np.power(L, 2) + 2*L + 1):
    pts.append( (vanderCorput(i+1,2), vanderCorput(i+1,3)) )

ptsOnSphere = np.array(mapToSphere(pts))

errs = []
for i in range(20):
    funct = makeRandomFunction(L)
    functr = funct[1]
    functi = funct[2]

    myIntegralFunction = integrateOverSphere(L, ptsOnSphere, funct[0])
    scipyIntegralFunction = scipy.integrate.nquad( functr, [[0, np.pi],[0,2*np.pi]])[0] + scipy.integrate.nquad( functi, [[0, np.pi],[0,2*np.pi]])[0] 
    errs.append(myIntegralFunction-scipyIntegralFunction)
print("averageErrL5: ", sum(errs)/20)

errs = []
for i in range(20):
    funct = makeRandomFunction(L+1)
    functr = funct[1]
    functi = funct[2]

    myIntegralFunction = integrateOverSphere(L, ptsOnSphere, funct[0])
    scipyIntegralFunction = scipy.integrate.nquad( functr, [[0, np.pi],[0,2*np.pi]])[0] + scipy.integrate.nquad( functi, [[0, np.pi],[0,2*np.pi]])[0] 
    errs.append(myIntegralFunction-scipyIntegralFunction)
print("averageErrL6: ", sum(errs)/20)

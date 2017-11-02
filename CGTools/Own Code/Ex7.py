# -*- coding: utf-8 -*-
"""
Created on Thu Jun  8 11:14:29 2017

@author: User
"""

import numpy as np
import scipy
from scipy import misc

face = misc.face()
misc.imsave("uffizi.png", face)

pic = misc.imread("uffizi.png")

def ylm( l, m, theta, phi) :
    clm = np.sqrt( ((2*l+1) /(4.0*np.pi)) * (np.math.factorial(l-m) / np.math.factorial(l+m)))
    return clm * scipy.special.lpmv( m, l, np.cos(theta)) * np.exp(1j * m * phi)

#h(theta phi) lightsource
def lightprobe(theta, phi):
    v = theta/np.pi #yrichtung
    u = phi/(np.pi*2.0) #xrichtung
    
    vidx = np.int(np.floor(v * pic.shape[0]-1))
    uidx = np.int(np.floor(u * pic.shape[1]-1))
    
    return pic[vidx, uidx][0]

l = 2
m = 0

def coeffs(l, m):
    integrandr = lambda theta, phi : (lightprobe(theta, phi)
                                 * np.conjugate(ylm( l, m, theta, phi)) 
                                 * np.sin(theta)).real
    integrandi = lambda theta, phi : (lightprobe(theta, phi)
                                 * np.conjugate(ylm( l, m, theta, phi)) 
                                 * np.sin(theta)).imag

    ivalr = scipy.integrate.nquad( integrandr, [[0, np.pi],[0,2*np.pi]])    
    ivali = scipy.integrate.nquad( integrandi, [[0, np.pi],[0,2*np.pi]])

    return ivalr[0] + ivali[0]
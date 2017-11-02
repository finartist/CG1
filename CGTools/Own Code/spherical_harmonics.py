#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 07:57:54 2017

@author: lessig
"""

import numpy as np
import scipy.special

def ylm( l, m, theta, phi) :
    clm = np.sqrt( ((2*l+1) /(4.0*np.pi)) * (np.math.factorial(l-m) / np.math.factorial(l+m)))
    return clm * scipy.special.lpmv( m, l, np.cos(theta)) * np.exp(1j * m * phi)

def testOrtho( l, m, lp, mp) :
    
    integrandr = lambda theta, phi : (  ylm( l, m, theta, phi) 
                                 * np.conjugate(ylm( lp, mp, theta, phi)) 
                                 * np.sin(theta)).real
    integrandi = lambda theta, phi : (  ylm( l, m, theta, phi) 
                                 * np.conjugate(ylm( lp, mp, theta, phi)) 
                                 * np.sin(theta)).imag

    ivalr = scipy.integrate.nquad( integrandr, [[0, np.pi],[0,2*np.pi]])    
    ivali = scipy.integrate.nquad( integrandi, [[0, np.pi],[0,2*np.pi]])

    return ivalr[0] + ivali[0]

def coeffsPhong(l, m, p):
    integrandr = lambda theta, phi : (np.power(np.cos(theta), p)
                                 * np.conjugate(ylm( l, m, theta, phi)) 
                                 * np.sin(theta)).real
    integrandi = lambda theta, phi : (np.power(np.cos(theta), p)
                                 * np.conjugate(ylm( l, m, theta, phi)) 
                                 * np.sin(theta)).imag

    ivalr = scipy.integrate.nquad( integrandr, [[0, np.pi/2],[0,2*np.pi]])    
    ivali = scipy.integrate.nquad( integrandi, [[0, np.pi/2],[0,2*np.pi]])

    return ivalr[0] + ivali[0]
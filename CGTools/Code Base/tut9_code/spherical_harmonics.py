#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 07:57:54 2017

@author: lessig
"""

import numpy as np
import scipy.special

#formel vom blatt
def ylm( l, m, theta, phi) :
    clm = np.sqrt((2*l+1)/(np.math.pi*4)*(np.math.factorial(l-m)/np.math.factorial(l+m)))
    plm = scipy.special.lpmv(m, l, np.cos(theta))
    emphi = np.exp(1j*m*phi)
    return clm*plm*emphi

def testOrthonormal(l, m, lp, mp):
    
    #sinus ist skalierungsfaktor in integration
    #bei innerem Produkt mit complexen zahlen muss eines complex conjugiert werden
    integrand = lambda theta, phi: (ylm(l, m, theta, phi) * ylm(lp, mp, theta, phi).conj * np.sin(theta))
    
    #kein support für complexe zahlen :( müsste den complexen faktor umschreiben
    ival = scipy.integrate.nquad(integrand, [[0, np.pi], [0, np.pi*2]])
    print(ival)
    return ival
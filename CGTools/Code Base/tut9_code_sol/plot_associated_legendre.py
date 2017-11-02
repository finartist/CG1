#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed May 31 08:04:07 2017

@author: lessig
"""

import numpy as np
import scipy
import matplotlib.pyplot as plt

L = 4

z = np.linspace( -1.0, 1.0, 1000)
Plms = [[scipy.special.lpmv( m, l, z) for m in range(0,l+1)] for l in range(L+1)]

for l in range(L+1) :
    fig = plt.figure(l)
    for m in range( 0, l+1) :
        plt.plot( z, Plms[l][m])
        
        

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jun  8 11:44:42 2017

@author: lessig
"""

import numpy as np
from scipy import misc

#data/uffizi.png

def load( fname) :
    return misc.imread(fname)

def value( probe, omega) :

    theta = omega[0]
    phi = omega[1]
    
    v = theta / np.pi
    u = phi / (2.0*np.pi)

    vidx = np.int( np.floor(v * (probe.shape[0]-1)))
    uidx = np.int( np.floor(u * (probe.shape[1]-1)))

    return probe[vidx,uidx][0]

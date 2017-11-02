#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 08:34:07 2017

@author: lessig
"""

from matplotlib import cm, colors
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import scipy.special
import matplotlib.pyplot as plt

import spherical_harmonics

def ylm( l, m, theta, phi) :
    clm = np.sqrt( ((2*l+1) /(4.0*np.pi)) * (np.math.factorial(l-m) / np.math.factorial(l+m)))
    return clm * scipy.special.lpmv( m, l, np.cos(theta)) * np.exp(1j * m * phi)


# generate sampling points
thetas = np.linspace( 0, np.pi, 100)
phis = np.linspace( 0, 2*np.pi, 100)
thetas, phis = np.meshgrid(thetas, phis)

#wenn m = l, nur oszillation am Äquator (längsstreifen)
#wenn m = 0, nur oszillationen von pol zu pol
#wenn l höher, nehmen oszillationen zu
# Generate spherical harmonics values
ylmvals = spherical_harmonics.ylm( 3, 3, thetas, phis).real

# normalize to [0,1] for plotting             
fmax, fmin = ylmvals.max(), ylmvals.min()
ylmvals = (ylmvals - fmin)/(fmax - fmin)

# The Cartesian coordinates of the unit sphere
x = np.sin(thetas) * np.cos(phis)
y = np.sin(thetas) * np.sin(phis)
z = np.cos(thetas)

# Set the aspect ratio to 1 so our sphere looks spherical
fig = plt.figure(figsize=plt.figaspect(1.))
ax = fig.add_subplot(111, projection='3d')
ax.plot_surface(x, y, z,  rstride=1, cstride=1, facecolors=cm.seismic(ylmvals))
# Turn off the axis planes
ax.set_axis_off()
plt.show()

spherical_harmonics.testOrthonormal(2,3,4,5)

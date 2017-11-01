#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Oct 22 15:24:59 2017

@author: lessig
"""
import scipy.integrate as integrate
import numpy as np
import matplotlib.pyplot as plt

# events and probability density function (pdf)
events = np.linspace( 1, 5, 5)
pdf = np.array( [0.1, 0.2, 0.4, 0.2, 0.1])

# compute cumulate probability function (cdf) and plot using plt.bar()
cdf = np.cumsum(pdf)
plt.bar( events, cdf)

# sample from pdf using inversion method
 #usamples represent probabilities
usamples = np.random.rand( 64)
 #samples represent the correspondant values to the usamples
samples = np.zeros( pdf.size)

#for each random probability (usample), look in what cdf section (bin) 
#it belongs and +1 in the right sample spot
#eg what value can I expect with a probalbility of 0.34
# 0.34 > than 0.1, 0.34 > 0.1+0.2, 0.34 < 0.1 + 0.2 + 0.4
# therefore 0.34 falls to the bin of 3 
# (if you would draw a line in the cdf at y = 0.34 it would hit the column with the 3)
for i in range(usamples.size):
    temp = usamples[i] > cdf
    samples[np.count_nonzero(temp)] += 1
     
#normalize
samples /= usamples.size
    
# plot pdf and sample distribution
fig, ax = plt.subplots()
ax.bar( events - 0.25, pdf, 0.3, color='b')
ax.bar( events + 0.25, samples, 0.3, color='r')
plt.show()
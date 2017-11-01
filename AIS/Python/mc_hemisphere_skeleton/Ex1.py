# -*- coding: utf-8 -*-
"""
Created on Tue Oct 17 14:07:20 2017

@author: User
"""

import numpy as np
import matplotlib.pyplot as plt

#100 gleichverteilt random punkte
x = np.random.rand(100)
plt.plot(x, np.zeros(100), 'x')
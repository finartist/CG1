# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import numpy as np
import matplotlib.pyplot as plt

#1
poly3 = np.random.randn(5, 4) #randn normalverteilte Werte
poly5 = np.random.randn(5, 6)

#2
#==============================================================================
# xs = np.linspace(-1, 1, 1024) #1024 zahlen zwischen -1, 1, äquidistant
# pcs = poly3[0,:] #0. Zeile, alle Spalten
# ys = 0.0
# 
# for k in range(0,4):
#    ys+=xs**k*pcs[k] #nicht effizient -> Horner schema besser
# 
# ys = pcs[3]
# for k in range(3, 0, -1):
#     ys = ys*xs + pcs[k]
#
#single precision float32
#xs_f = np.linspace(1.99, 2.01, 256, dtype = 'float32') #1024 zahlen zwischen -1, 1, äquidistant
#pcs_f = np.array([-8.0, 12.0, -6.0, 1.0], dtype='float32')
#
#ys_f = 0.0
#
#for k in range(0,4):
#   ys_f+=xs_f**k*pcs_f[k]
#
# 
# #double precision float64
# xs_d = np.linspace(1.99, 2.01, 256, dtype = 'float64') #1024 zahlen zwischen -1, 1, äquidistant
# pcs_d = np.array([-8.0, 12.0, -6.0, 1.0], dtype='float64') #poly3[0,:] #0. Zeile, alle Spalten 
# 
# ys_d = 0.0 #_d float64 -> double precision
# for k in range(0,4) : #range(2, -1, -1)
#     ys_d+=xs_d**k*pcs_d[k]
#
#plt.plot(xs_f, ys_f, xs_d, ys_d) ->_f oszilliert stark (catastrophic cancellation)
#==============================================================================

xs = np.linspace(-1.0, 1.0, 1024, dtype = 'float32')

#3
#effizient für alle Polynome
#diese Variante effizient da intern in C ausgeführt
#xs zu 1024x1 SpaltenVektor und 5x nebeneinander kopiert -> Matrix
xss = np.reshape(xs,(1024,1))
xss = np.repeat(xss, 5 ,axis = 1)

qs = poly3[:,3]
for k in range(2,-2, -1) : #range(2, -1, -1)
     qs = xss*qs + poly3[:,k] #1024x5 * 5x1 + 5x1
     print(k)

#plt.plot(xs, qs) #xs=1024x1 Spaltenvektor, qs=1024 Zeilen, 5 Spalten

#4
np.sum(poly3, 0) #addieren entlang der Zeilen -> entlang 0er dimension => Liniearkombination der Polynome

      
#Harmonic Polynoms
hpoly3 = np.random.randn(5, 7)
print(hpoly3);
hpoly5 = np.random.randn(5, 11)

hxs = np.linspace(0, 2*np.pi, 1024)
hxss = np.reshape(hxs, (1024,1))
hxss = np.repeat(hxss, 5, axis=1)

hqs = 0;
for p in hpoly3[0] : print(p)
for k in range(0,4):
    hqs += np.cos(k*hxss)*hpoly3[:,k] + np.sin(k*hxss)*hpoly3[:,k+3]


#for k in range(0,6):
#    hqs += np.cos(k*hxss)*hpoly5[:,k] + np.sin(k*hxss)*hpoly5[:,k+3]
    
plt.plot(hxs, hqs)





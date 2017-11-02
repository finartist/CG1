# -*- coding: utf-8 -*-
"""
Created on Tue Apr 25 16:11:38 2017

@author: Lisa
"""

#Assignment1
import numpy as np
import matplotlib.pyplot as plt
import scipy
from mpl_toolkits.mplot3d import Axes3D

def plotVectors2D( vs, col='black') :
    """
    Plot 2D vectors into fig. vs is expected contain the vectors in its columns
    """
    ax = plt.gca()
    for i in range(vs.shape[0]) :
        ax.quiver( 0.0, 0.0, vs[i,0], vs[i,1], angles='xy', scale_units='xy', scale=1, color=col)
    maxaxis = 1.2 * np.max( np.abs(vs))
    ax.set_xlim( [-maxaxis, maxaxis])
    ax.set_ylim( [-maxaxis, maxaxis])
    plt.axes().set_aspect('equal')


#1c Dual Basis in R^2
def DualBase(a1=1, a2=1):
    if(np.abs(a1) <= np.finfo('float64').eps or np.abs(a1) <= np.finfo('float64').eps):
        raise("InvalidParameter")
    else:
        #might cause invalid values for a1,a2 near zero
        u1 = np.array([a1,0])
        u2 = np.array([0,a2])
        
        B = np.array([u1, u2]).transpose()
        Btilde = np.linalg.inv(B)
        
        u1tilde = Btilde[0]
        u2tilde = Btilde[1]
        return (u1tilde, u2tilde)

for count in range(1,11):
    basis = np.random.randn(2)
    dualbasis = DualBase(basis[0],basis[1])
    basis = np.array([[basis[0], 0.0],[0.0, basis[1]]])
    dualbasis = np.array([dualbasis[0], dualbasis[1]])
    print("basis: ", basis)
    print("dualbasis: ", dualbasis)
    plotVectors2D(basis)
    plotVectors2D(dualbasis, col='red')
    plt.show()
    
#3a Bernstein Polynoms
def EvaluateBernstein(n,v, xs):
    nov = scipy.special.binom(n,v) #n über v
    ys = nov * (xs ** v) * ((1-xs) ** (n-v))
    return ys
#b  
xs  = np.linspace(0.0, 1.0, 1024)
N = 5
for i in range(N+1):
    ys = np.zeros((1024,N+1))
    for j in range(i+1):
        ys[:,j] = EvaluateBernstein(i, j, xs)
    plt.plot(xs, ys)
    plt.show()
    
#d
n = 4
N = n+1
#Polynomials can be translated with f(x)+c (move in y direction)
# f(x+v) move in x direction
# a*f(x) scale in y direction
#f(x/a) scale in x direction 
#we want to scale the normalized Legendre Polynomials to half the size in x direction 
#and translate it by 1 in x direction so it fits to the domain [0,1]
#[xmin,xmax] -> [XMIN,XMAX] f(x) -> f(x') x'=ax+b a = (xmin – xmax) / (XMIN – XMAX) b = xmin – a XMIN
#[-1,1] -> [0,1] a = (-1 -1) / (0-1) = -2/-1 = 2 b = -1 -2*0 = -1
#->Pls[j](2*x-1)
Pls = [np.polynomial.Legendre(list(np.zeros(l))+[1.0])*np.sqrt((2.0*l+1.0)/2.0) for l in range(N)]

#coeff matrix
B=np.zeros((N,N))
for i in range(N):
    for j in range(N):
        #B coeff matrix to construct bernstein polynomials as linear combination of Legendre polynomials
        B[j,i] = scipy.integrate.quad(lambda x : (scipy.special.binom(n,i) * (x**i) * ((1-x)**(n-i))) * (Pls[j](2*x-1)), 0.0, 1.0)[0]

#Dual base coeffs
Btilde = np.linalg.inv(B)
for l in range(N) : 
    Btilde[:,l] *= np.sqrt((2.0*l+1.0)/2.0)

#Dual base polynomials
D = []
for i in range(N):
    D.append(np.polynomial.Legendre(Btilde[i]))
    ys = D[i](2*xs-1)
    plt.plot(xs, ys)
    
plt.ylim([-40,40])
plt.show()

F=np.zeros((N,N))
for i in range(N):
    for j in range(N):
        F[j,i] = scipy.integrate.quad(lambda x : (scipy.special.binom(n,i) * (x**i) * ((1-x)**(n-i))) * D[j](2*x-1), 0.0, 1.0)[0]


#2
#a In the Mercedes Benz for R2 frame are 3 equiangular Vectors, so we need equiangular Vectors that span R3
#A Tetraeder should suffice that as it is very regular and each face is the same                                
 
#Winkel erhält man zb durch den Cosinussatz, mittelpunkt(0,0,0) hat abstand r zu den ecken des tetraeders
#a = Seitenlänge, my der winkel zwischen den vektoren, Es gilt a²=R²+R²-2R²cos(my) mit R=(1/4)sqrt(6)a oder R²=(3/8)a².
#Dann ist a²=(3/4)a²-(3/4)a²cos(my) oder (1/4)=-(3/4)cos(my). 
#Daraus folgt cos(my)=-1/3 oder my=109,471221°.
frame = [[0.0,0.0,1.0]]
Rz = np.array([[np.cos(np.radians(120)), -np.sin(np.radians(120)), 0.0],[np.sin(np.radians(120)), np.cos(np.radians(120)), 0.0], [0.0,0.0,1.0]])
frame.append([0.0, np.cos(np.radians(-19.471220634491)), np.sin(np.radians(-19.471220634491))])

for i in range(2,4,1):
    frame.append(np.dot(Rz, frame[i-1]))
#print(frame)
#print('Angle : ', np.rad2deg(np.arccos(np.dot(frame[2], frame[3])/(np.linalg.norm(frame[2])*np.linalg.norm(frame[3])))))

"""
frame = [[-1.0, 0.0, -1.0/np.sqrt(2)],[1.0, 0.0, -1.0/np.sqrt(2)],[0.0, -1.0, 1.0/np.sqrt(2)],[0.0, 1.0, 1.0/np.sqrt(2)]]
for i in range(0,4):
    #normalize
    frame[i] = frame[i]/np.linalg.norm(frame[i])
#print(np.rad2deg(np.arccos(np.dot(frame[0], frame[1])/(np.linalg.norm(frame[0])*np.linalg.norm(frame[1])))))
"""

#b
def plotVectors3D(vs) :
    fig = plt.figure()
    ax = fig.add_subplot(111, projection = '3d')
    for i in range(vs.shape[0]) :
        ax.quiver( 0.0, 0.0, 0.0, vs[i,0], vs[i,1], vs[i,2])
        maxaxis = 1.2 * np.max( np.abs(vs))
    ax.set_xlim( [-maxaxis, maxaxis])
    ax.set_ylim( [-maxaxis, maxaxis])
    ax.set_zlim( [-maxaxis, maxaxis])

frame = np.array(frame).transpose()
plotVectors3D(frame.transpose())
plt.show()

#if frame is tight: v = 1/r *sum(<v,ui>ui) r = m/n 
#c
v = np.random.randn(3)
vcalc = 0
for j in range(0,4):
    vcalc += 3.0/4.0 * np.dot(v, frame[:,j]) * frame[:,j] 
err = np.linalg.norm(v-vcalc)
istight = (err < 10.0*np.finfo('float64').eps)
print('istight: ', istight, 'err: ', err)

#d
S = np.dot(frame, frame.transpose())
Sinvers = np.linalg.inv(S)
frametilde = np.dot(Sinvers, frame).transpose()

#print('Angle Tilde: ', np.rad2deg(np.arccos(np.dot(frametilde[0], frametilde[1])/(np.linalg.norm(frametilde[0])*np.linalg.norm(frametilde[1])))))

plotVectors3D(frametilde)
plt.show()

#e
def VecToFrame(frame, vector):
    S = np.dot(frame, frame.transpose())
    Sinvers = np.linalg.inv(S)
    frametilde = np.dot(Sinvers, frame).transpose()
    vframe = np.dot(frametilde, vector)
    return vframe

def VecToCanonical(frame, vector):
    vrec = np.dot(frame, vector)
    return vrec

def VecReconstruct(frame, vector):
    re = VecToCanonical(frame, VecToFrame(frame, vector))
    return re

err = np.linalg.norm(v-VecReconstruct(frame,v))
print('err: ', err)

#4
"""
N =6
PolyBase = [np.polynomial.Polynomial([1.0])]
xs = np.linspace(-1.0,1.0,1000.0)
ys = []
ys.append(PolyBase[0](xs))
for i in range(1,N+1):
    P = PolyBase[i-1]*np.polynomial.Polynomial([1.0,-i])
    PolyBase.append(P)
    ys.append(PolyBase[i](xs))

ys = np.array(ys)
ys = ys.transpose()
plt.plot(xs, ys)
plt.ylim([-1.5,1.5])
plt.show()
"""











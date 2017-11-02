#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Jun 14 17:59:29 2017

@author: lessig
"""

import numpy as np

def vanderCorput(n,b):
  """ 
  Generate point of low discrepancy van der Corput sequence
  (based on http://codegist.net/code/low-discrepancy-sequence-algorithm/)
  
  n : index of point
  b : base (small prime number)
  """
  m=k=np.real(0)
  
  while(n):
    m = m * b + n % b
    n = np.int64(n/b)
    k += 1
    
  return m * 1.0/ np.power(b,k)


pts = []
for i in range(10):
    pts.append( (vanderCorput(i+1,2), vanderCorput(i+1,3)) ) 
    

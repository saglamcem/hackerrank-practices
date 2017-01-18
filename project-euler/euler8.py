#!/bin/python3
import sys

# Project Euler #8: Largest product in a series
def largestproduct(n, k, num):
    largest, temp = 0, 1
    numstr = str(num)
    for i in range(0, n-k+2):
        for v in numstr[i:i+k]:
            temp *= int(v)
        largest = max(largest, temp)
        temp = 1
    return largest


t = int(input().strip())
for a0 in range(t):
    n,k = input().strip().split(' ')
    n,k = [int(n),int(k)]
    num = input().strip()
    print(largestproduct(n,k,num))

#!/bin/python3
import sys
import math

# Project Euler #3: Largest prime factor


def lpf(n):
    tmp = n
    # divide n by 2 as far as it goes
    if n % 2 is 0:
        while tmp % 2 is 0:
            tmp //= 2
        if tmp is 1: # if 1 remains, then it was simply multiples of 2
            return 2

    # check for composite numbers
    i = 3
    while i <= math.sqrt(tmp):
        if tmp % i is 0:
            tmp //= i
        else:
            i += 2
    if tmp > 2:
        return tmp
    else:
        return i

t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    print(lpf(n))

#!/bin/python3
import sys

# Project Euler #6: Sum square difference


def difference(limit):
    # result = sumSquare - squareSum
    # Sum of squares is calculated using Faulhaber's formula
    sumSquare = (limit * (limit+1) * ((2*limit) + 1)) // 6
    squareSum = (limit * (limit + 1) // 2) ** 2
    return abs(sumSquare - squareSum)


t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    print(difference(n))

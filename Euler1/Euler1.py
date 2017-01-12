#!/bin/python3
import sys

# Project Euler #1: Multiples of 3 and 5
# Time: O(1), Memory: O(1)


def getSum(limit):
    # Calculate the amount of threes and their sums
    num3s = limit // 3
    sum3s = (3 * (num3s * (num3s + 1))) // 2
    # Calculate the amount of fives and their sums
    num5s = limit // 5
    sum5s = (5 * (num5s * (num5s + 1))) // 2
    # Calculate the amount of fifteens and their sums
    num15s = limit // 15
    sum15s = (15 * (num15s * (num15s + 1))) // 2
    return num3s + num5s - num15s


t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    print(getSum(n-1))

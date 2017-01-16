#!/bin/python3
import sys

# Project Euler #2: Even Fibonacci numbers
# If we use Dynamic Programming, a list of size 3 is enough.
# Time: O(n), Memory: O(1)


def smartfibo(limit):

    fibos = [0, 0, 1] # initial list for fibonacci values
    i = 3
    evensums = 0 # sum of even fibonacci values

    while max(fibos) <= limit:
        fibos[(i) % 3] = fibos[(i - 1) % 3] + fibos[(i - 2) % 3]
        if fibos[(i) % 3] <= limit:
            if fibos[(i) % 3] % 2 is 0:
                evensums += fibos[(i) % 3]
        i += 1

    return evensums

t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    print(smartfibo(n))

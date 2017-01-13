#!/bin/python3
import sys

# Fibonacci Modified; Time: O(n), Memory: O(1)
# Fibonacci Modified goes as follows: t_i+2 = t_i + (t_i+1)^2
def fibomodified(t1, t2, n):
    fibos = [t1, t2, 0]
    for i in range(2, n + 1):
        fibos[(i) % 3] = fibos[(i - 2) % 3] + ((fibos[(i - 1) % 3]) ** 2)
    return fibos[((n - 1) % 3)]


t1, t2, n = map(int, input().split())
print(fibomodified(t1, t2, n))

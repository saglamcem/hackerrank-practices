'''
The algorithm will be,

STEP-1: if the given N is even repeatedly divide by 2.

STEP-2: if the N becomes 1 return 2 which is the largest prime factor.

STEP-3: start with i=3, and check if the N is divisible by i. increment i by 2 (because there are no even factors for an odd number..). run the loop till square root of the N. if the N is divisible by i then divide the N by i and run the loop again from i =3. this step will eliminate all the composite numbers.

STEP-4: finally if N is grater 2 then its a prime number. return N. else return i which is the largest prime number.
'''

#!/bin/python3
import sys
import math

# Project Euler #3: Largest prime factor

def lpf(n):
    tmp = n
    if n % 2 is 0:
        while tmp % 2 is not 0:
            tmp //= 2
        if tmp is 1:
            return 2

    tmp = n # refresh tmp value
    # check for composite numbers

    i = 3
    while i <= math.sqrt(n):
        if tmp % i is 0:
            tmp //= i
        else:
            i += 2
    if tmp > 2:
        return n
    else:
        return i

t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    print(lpf(n))

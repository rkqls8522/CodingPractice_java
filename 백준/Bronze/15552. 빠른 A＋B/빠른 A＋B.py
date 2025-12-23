# 15552 : 빠른 A+B

import sys

input = sys.stdin.readline

write = sys.stdout.write

t = int(input())

for _ in range(t):
    a, b = map(int, input().split())
    write(str(a + b) + "\n")
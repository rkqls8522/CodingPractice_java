# 15552 : 빠른 A+B

import sys

input = sys.stdin.readline
write = sys.stdout.write  # 보통 많은 출력값을 join과 함께 사용할 때 좋다.
out = []

for _ in range(int(input())):
    a, b = map(int, input().split())
    out.append(str(a + b))

write("\n".join(out))
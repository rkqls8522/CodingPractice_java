# 8393 합

import time

n = int(input())

# 1. 수학 공식을 이용한 방식
# 시간복잡도: O(1)
def my_sum(n):
    return n * (n + 1) // 2


def my_time(func, num):
    start = time.time()
    result = func(num)
    end = time.time()

    return result

print(my_time(my_sum, n))



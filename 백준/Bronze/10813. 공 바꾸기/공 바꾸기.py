N, M = map(int, input().split())

list = [i for i in range(N+1)]

for _ in range(M) :
    i, j = map(int, input().split())
    
    temp = list[i]
    list[i] = list[j]
    list[j] = temp

print(" ".join(str(num) for num in list[1:]))
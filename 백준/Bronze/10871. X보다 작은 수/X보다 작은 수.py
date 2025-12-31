N, X = map(int, input().split())
A = list(map(int, input().split()))

result = []

# 작은 수 찾는 과정
for num in A :
    if X > num :
        result.append(str(num))
        
# 출력
print(" ".join(result))
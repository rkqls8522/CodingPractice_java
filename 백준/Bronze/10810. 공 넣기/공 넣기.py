N, M = map(int, input().split())

list = [0] * N

for _ in range(M) :
    start, end, ball_num = map(int, input().split())
    
    for i in range(end-start+1) :
        list[start+i-1] = ball_num

print(" ".join(str(num) for num in list))
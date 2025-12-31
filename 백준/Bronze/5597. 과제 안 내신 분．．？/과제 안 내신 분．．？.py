list = [False] * 31
list[0] = True  # 0번째 학생은 없으니 먼저 처리.

for _ in range(28) :
    list[int(input())] = True
    
for i in range(31) :
    if not list[i] :
        print(i)
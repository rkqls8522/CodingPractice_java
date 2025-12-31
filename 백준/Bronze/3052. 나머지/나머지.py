num_set = set()

for _ in range(10) :
    num = int(input())  # 값 받아서
    num_set.add(num % 42)  # 나머지 넣기

print(len(num_set))
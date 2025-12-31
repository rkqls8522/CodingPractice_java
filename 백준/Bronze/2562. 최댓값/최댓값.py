list = []

for _ in range(9) :
    list.append(int(input()))

max_value = max(list)
print(max_value)
print(list.index(max_value)+1)
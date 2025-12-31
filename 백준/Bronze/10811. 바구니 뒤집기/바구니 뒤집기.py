N, M = map(int, input().split())

basket_list = [i for i in range(N+2)]  # end+1부분때문에 넉넉히 만듦.

for _ in range(M) :
    
    start, end = map(int, input().split())
    basket_list[start:end+1] = basket_list[start:end+1][::-1]
    
result = " ".join(str(i) for i in basket_list[1:-1])

print(result)
    
# N의 범위가 1,000이하. 10,000이상부터는 sys고려, 100,000부터는 필수로 사용하자.

N = int(input())
scores = list(map(int, input().split()))
result = []

max_score = max(scores)

for score in scores :
    result.append(score/max_score*100)

print(sum(result)/N)
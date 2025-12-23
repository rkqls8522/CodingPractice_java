# 2525 오븐 시계

hour, minute = map(int, input().split())
time = int(input())

hour += time // 60
minute += time % 60

if minute >= 60 :
    hour += 1
    minute -= 60

if hour >= 24 :
    hour -= 24

print(hour, minute)
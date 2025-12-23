# 2753 윤년
year = int(input())
print(int((year % 4 == 0 and year % 100 != 0) or year % 400 == 0))
# 1330 두 수 비교하기
# num1 = int(input())
# num2 = int(input())
num1, num2 = map(int, input().split())
print('<' if num1 < num2 else '>' if num1 > num2 else '==')
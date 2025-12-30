# vscode에서 실행 잘 됨. 백준에 제출 땐 이걸로 해야 함.
import sys
input = sys.stdin.readline
print = sys.stdout.write

num = int(input())  # int로 바꾸면 줄바꿈 개행문자 사라짐
numbers = list(map(int, input().split()))
target = int(input())

print(str(numbers.count(target)))
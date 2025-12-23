# 2480 주사위 세개

n1,n2,n3 = map(int,input().split())

if n1 == n2 and n2 == n3:
    print(10000+n1*1000)
elif n1 != n2 and n2 != n3 and n1 != n3:
    maxnum = max(n1,n2,n3)
    print(maxnum*100)
else :
    if n1 == n2:
        print(1000+n1*100)
    elif n2 == n3:
        print(1000+n2*100)
    else:
        print(1000+n1*100)
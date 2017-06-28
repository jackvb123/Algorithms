def computeT(a,t):
    ak = list(a.keys())
    for i in range(len(ak)):
        temp = t - ak[i]
        #print(temp)
        if(ak[i ]==temp):
            continue
        if(temp >= 0 and temp in a.keys()):
            return True
        
    return False
a={}    
b = [1,4,45,6,10,-8]
for i in b:
    a[i] = 1
f = open("in.txt")
A = f.readlines()
B={}
for i in range(len(A)):
    A[i] = A[i].replace('\n','')
    B[int(A[i])] = 1
count=0
for t in range(-10000,10001):
    
    if(computeT(B,t)):
        count+=1

print(count)

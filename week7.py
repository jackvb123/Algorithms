import heapq as H


def getmedian(e,m,l,r):
   
    if(len(l) == len(r)):
        if(e<m):
            l.append(e)
            H._heapify_max(l)
            m =  l[0]
        else:
            r.append(e)
            H.heapify(r)
            m=r[0]
    elif(len(l)> len(r)):
        if(e<m):
            r.append(l[0])
            H.heappop(l)
            l.append(e)
            H._heapify_max(l)
        else:
            r.append(e)
            H.heapify(r)

        m = (l[0])
    else:
        if(e<m):
            l.append(e)
            H._heapify_max(l)
        else:
            l.append(r[0])
            H._heapify_max(l)
            H.heappop(r)
            r.append(e)
            H.heapify(r)
        m = (l[0])
    
    return (m,l,r)
        
def median(a):
    m=0
    l=[]
    r=[]
    ans = 0
    for i in range(len(a)):
        (m,l,r) = getmedian(a[i],m,l,r)
        ans+=m
        #print(m,l,r)

    print(ans%10000)
f = open("in.txt")
A = f.readlines()
for i in range(10000):
    A[i] = A[i].replace('\n','')
    A[i] = int(A[i])
    
a=[6,1,3,4,5,2,9,7,8,10]  
        
median(A)        
        

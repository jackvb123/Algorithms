import sys
def minDistance(dist, incl,V):
    m=sys.maxsize
    min_index =0
    for v in range(V):
        if (incl[v] == False) and (dist[v] <= m):
            m = dist[v]
            min_index = v
    return min_index

def djikstra(graph, src,V):
    dist=[]
    incl=[]
    for i in range(V):
        dist.append(sys.maxsize)
        incl.append(False)
    dist[src]=0
    for i in range(V-1):
        u = minDistance(dist,incl,200)
        incl[u] = True
        for v in range(V):
            if(incl[v]==False) and (graph[u][v]) and (dist[u]!=sys.maxsize) and (dist[u] +graph[u][v] < dist[v]):
                dist[v] = dist[u]+graph[u][v]

    k=[7,37,59,82,99,115,133,165,188,197]
    for i in range(10):
        print(dist[k[i]-1],end=',')

def main():
    f = open("in.txt")
    A = f.readlines()
    graph = []
    for i in range(200):
        t=[]
        for j in range(200):
            t.append(0)
        graph.append(t)
    for i in range(len(A)):
        A[i]=A[i].replace('\t',' ')
        A[i]=A[i].replace('\n','')
        b = A[i].split(' ')
        b.remove('')
        #print(A[i])
        for j in range(1,len(b)):
            c = b[j].split(',')
            c[0]=int(c[0])
            c[1]=int(c[1])
            b[0]=int(b[0])
            #print(int(c[0]),int(c[1]))
            graph[b[0]-1][c[0]-1] = c[1]
        #print(b)
    #print(A[0])
    djikstra(graph,0,200)
    
main()

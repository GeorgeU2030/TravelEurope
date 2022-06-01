package model;

import java.util.*;

public class GraphM<T> {

    public int [][]matrixAdj;
    public int[][]matrixFloyd;

    public int[][]costMatrix;
    public ArrayList<T> datas = new ArrayList<>();

    public GraphM(int nV){

        matrixAdj = new int[nV][nV];
        this.matrixFloyd=new int[nV][nV];
        costMatrixweight();
    }

    public void costMatrixweight() {
        for(int i=0;i<costMatrix.length;i++) {
            for(int j=0;j<costMatrix[0].length;j++) {
                if(i==j) {
                    costMatrix[i][j]=0;
                }else {
                    costMatrix[i][j]=Integer.MAX_VALUE;
                }
            }
        }
    }
    public void addVertex(T data){

        datas.add(data);
    }
    public void addEdge(T v1,T v2,int cost){
        int a=0,b=0;
        for(int i=0;i<datas.size();i++){
            if(v1.equals(datas.get(i))){
                a=i;
                break;
            }
        }
        for(int i=0;i<datas.size();i++){
            if(v2.equals(datas.get(i))){
                b=i;
                break;
            }
        }
        matrixAdj[a][b]=1;
        matrixAdj[b][a]=1;
        costMatrix[a][b]=cost;
        costMatrix[b][a]=cost;
    }
    public void print() {
        for (int x=0; x < matrixAdj.length; x++) {
            System.out.print("|");
            for (int y=0; y < matrixAdj[x].length; y++) {
                System.out.print (matrixAdj[x][y]);
                if (y!=matrixAdj[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }
    public Set<T> bfs(GraphM graph, T root) {
        Set<T> visited = new LinkedHashSet<T>();
        Queue<T> queue = new LinkedList<T>();
        queue.add(root);
        visited.add(root);
        int pos=0;
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for(int i=0;i<datas.size();i++){
                if(datas.get(i).equals(vertex)){
                  pos=i;
                }
            }
            for(int j=0;j<datas.size();j++){
                if(matrixAdj[pos][j]==1){
                    T v=datas.get(j);
                    if(!visited.contains(v)){
                        visited.add((T) v);
                        queue.add((T) v);
                    }
                }
            }
        }
        return visited;
    }
    public Set<T> dfsStack(GraphM graph, T root) {
        Set<T> visited = new LinkedHashSet<T>();
        Stack<T> stack = new Stack<T>();
        stack.push(root);
        int pos=0;
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for(int i=0;i<datas.size();i++){
                    if(datas.get(i).equals(vertex)){
                        pos=i;
                    }
                }
                for(int j=0;j<datas.size();j++){
                    if(matrixAdj[pos][j]==1){
                        T v=datas.get(j);
                        stack.push(v);
                    }
                }

            }
        }
        return visited;
    }
    public void dijkstra(int graph[][], int src,int V)
    {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;


        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);


            sptSet[u] = true;


            for (int v = 0; v < V; v++)


                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }


        printSolution(dist);
    }
   public int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
    public void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(datas.get(i) + " \t\t " + dist[i]);
    }

    public void floydWarshall(int[][]graph) {
        int nV=graph.length;

        int i=0;
        int j=0;
        int k=0;

        for( i=0;i<matrixFloyd.length;i++) {
            for(j=0;j<matrixFloyd[0].length;j++) {
                matrixFloyd[i][j]=graph[i][j];

            }
        }

        for (k = 0; k < nV; k++) {
            for (i = 0; i < nV; i++) {
                for (j = 0; j < nV; j++) {

                    if (matrixFloyd[i][j] > (matrixFloyd[i][k] + matrixFloyd[k][j])
                            && (matrixFloyd[k][j] != Integer.MAX_VALUE
                            && matrixFloyd[i][k] != Integer.MAX_VALUE))
                        matrixFloyd[i][j] = matrixFloyd[i][k] + matrixFloyd[k][j];
                }
            }
        }
    }
    public int searchRoad(ArrayList<T>cities) {
        int time=9999999;
        int b=0;

        for(int i=0;i<cities.size();i++) {
            int a = searchPos(cities,cities.get(i));
            int parcialtime=0;
            for(int j=0;j<cities.size();j++) {

                b = searchPos(cities,cities.get(j));
                parcialtime+=matrixFloyd[a][b];

            }

            if(parcialtime<time) {
                time=parcialtime;

            }


        }
        return time;
    }
    public int searchPos(ArrayList<T>cities,T a) {
        int pos=0;
        for(int i=0;i<cities.size();i++) {
            for(int j=0;j<datas.size();j++) {
                if(a.equals(datas.get(j))) {
                    pos=j;
                    break;
                }
            }
        }
        return pos;
    }
}

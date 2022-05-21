package model;

import java.util.*;

public class GraphList<T> {


    public int[][]costMatrix;
    public int[][]matrixFloyd;
    public HashMap<T, ArrayList<T>> adjVertices;
    public ArrayList<T>costos=new ArrayList<>();

    public GraphList(int nv) {
        this.costMatrix=new int[nv][nv];
        this.adjVertices=new HashMap<>();
        this.matrixFloyd=new int[nv][nv];
        costMatrixweight();
    }
    public void addVertex(T data) {
        adjVertices.put(data, new ArrayList<>());
        costos.add(data);
    }

    public void removeVertex(T data) {
        City v = new City(data);
        //adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new City(data));
    }
    public void addEdge(T vertex1, T vertex2) {
        City v1 = new City(vertex1);
        City v2 = new City(vertex2);
        adjVertices.get(vertex1).add(vertex2);
        adjVertices.get(vertex2).add(vertex1);
        // adjVertices.get(vertex2).add(vertex1);
    }
    public void searchadj(T data) {
        System.out.println(adjVertices.get(data));
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
    public Set<T> bfs(GraphList graph, T root) {
        Set<T> visited = new LinkedHashSet<T>();
        Queue<T> queue = new LinkedList<T>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T v : adjVertices.get(vertex)) {
                if (!visited.contains(v)) {
                    visited.add((T) v);
                    queue.add((T) v);
                }
            }
        }
        return visited;
    }
    public Set<T> dfs(GraphList graph, T root) {
        Set<T> visited = new LinkedHashSet<T>();
        Stack<T> stack = new Stack<T>();
        stack.push(root);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (T v : adjVertices.get(vertex)) {
                    stack.push(v);
                }
            }
        }
        return visited;
    }
   

    public void addEdge(T v1,T v2,int cost) {
    	adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
        int i=0;
        for(T v:costos) {
            if(v.equals(v1)) {
                break;
            }
            i++;
        }
        int j=0;
        for(T v:costos) {
            if(v.equals(v2)) {
                break;
            }
            j++;
        }
        costMatrix[i][j]=cost;
        costMatrix[j][i]=cost;
    }
    public void printcost() {
        for (int x=0; x < costMatrix.length; x++) {
            System.out.print("|");
            for (int y=0; y < costMatrix[x].length; y++) {
                System.out.print (costMatrix[x][y]);
                if (y!=costMatrix[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
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
    int minDistance(int dist[], Boolean sptSet[])
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
    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(costos.get(i) + " \t\t " + dist[i]);
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
    public void printFloyd() {
    	for (int x=0; x < matrixFloyd.length; x++) {
    		  System.out.print("|");
    		  for (int y=0; y < matrixFloyd[x].length; y++) {
    		    System.out.print (matrixFloyd[x][y]);
    		    if (y!=matrixFloyd[x].length-1) System.out.print("\t");
    		  }
    		  System.out.println("|");
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
    		for(int j=0;j<costos.size();j++) {
    			if(a.equals(costos.get(j))) {
    				pos=j;
    				break;
    			}
    		}
    	}
    	return pos;
    }
}





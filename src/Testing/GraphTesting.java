package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Graph;


class GraphTesting {
	
	private Graph<Integer> graph;
	
	public void setupStage1() {
		
		graph =new Graph<Integer>(3);
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
	
	}
	
	@Test
	void addVertexTest() {
		setupStage1();
		boolean ok=false;
		if(graph.adjVertices.containsKey(0)) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void removeVertexTest() {
		setupStage1();
		int value = 1;
		graph.removeVertex(value);
		boolean ok=false;
		if(graph.adjVertices.containsValue(value)==false) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void addEdgeTest() {
		setupStage1();
		graph.addEdge(1, 2);
		boolean ok=false;
		if(graph.adjVertices.get(1).contains(2)) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void addEdgeCostTest() {
		setupStage1();
		graph.addEdge(1, 2,1);
		boolean ok=false;
		if(graph.adjVertices.get(1).contains(2)) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void bfsTest() {
		setupStage1();
		boolean ok=false;
		if(!graph.bfs(graph, 0).isEmpty()){
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void dfsTest() {
		setupStage1();
		boolean ok=false;
		if(!graph.dfs(graph, 0).isEmpty()){
			ok=true;
		}
		assertEquals(ok,true);
	}
	

}

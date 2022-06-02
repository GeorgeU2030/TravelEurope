package Testing;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import control.MenuWindow;
import model.City;

class SolutionTesting {
	
	private String id;
	private MenuWindow mw;
	private String city;
	public void setupStage1() {
		id="Diana";
		mw=new MenuWindow(id);
		city="Cali";
	}
	
	public void setupStage2() {
		id="Diana";
		mw=new MenuWindow(id);
		city="Cali";
		mw.getMap().addVertex(city);
	}

	public void setupStage3() {
		id="Diana";
		mw=new MenuWindow(id);
		mw.getCities().add(new City("Cali"));
		mw.getCities().add(new City("Cucuta"));
	
	}
	
	@Test
	void addCity()  {
		setupStage1();
		boolean ok=false;
		mw.getCities().add(new City(city));
		if(!mw.getCities().isEmpty()) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void deleteCity()  {
		setupStage1();
		boolean ok=false;
		mw.getCities().add(new City(city));
		mw.getCities().remove(0);
		if(mw.getCities().isEmpty()) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void addVertexCityGraph() {
		setupStage2();
		boolean ok=false;
		if(!mw.getMap().adjVertices.isEmpty()) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void deleteVertexCityGraph() {
		setupStage1();
		boolean ok=false;
		mw.getMap().removeVertex(city);
		if(mw.getMap().adjVertices.isEmpty()) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
	@Test
	void addEdgeCities() {
		setupStage1();
		boolean ok=false;
		String c1="Bogota";
		String c2= "Medellin";
		mw.getMap().addVertex(c1);
		mw.getMap().addVertex(c2);
		mw.getMap().addEdge(c1,c2);
		
		if(mw.getMap().adjVertices.get(c1).contains(c2)) {
			ok=true;
		}
		assertEquals(ok,true);
	}
	
}

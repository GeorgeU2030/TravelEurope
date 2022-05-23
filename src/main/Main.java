package main;

import java.util.ArrayList;
import java.util.Scanner;
import model.GraphList;

public class Main {

	private GraphList<String>mapEurope;
	private Scanner sc =new Scanner(System.in);
	public static void main(String[]args) {
		Main obj=new Main();
		obj.init();
		obj.requestData();
	}
	
	public void init() {
		mapEurope = new GraphList<>(6);
		
		mapEurope.addVertex("Barcelona");
		mapEurope.addVertex("Paris");
		mapEurope.addVertex("Londres");
		mapEurope.addVertex("Zurich");
		mapEurope.addVertex("Roma");
		mapEurope.addVertex("Moscu");
		
		mapEurope.addEdge("Barcelona", "Londres", 5);
		mapEurope.addEdge("Londres", "Paris",3);
		mapEurope.addEdge("Barcelona", "Paris",2);
		mapEurope.addEdge("Paris", "Zurich",1);
		mapEurope.addEdge("Paris", "Roma",2);
		mapEurope.addEdge("Roma", "Zurich",2);
		mapEurope.addEdge("Moscu", "Roma",7);
		
	}
	
	public void requestData() {
		
		System.out.println("Enter the number of Cities to visit");
		int t = sc.nextInt();
		ArrayList<String>cities=new ArrayList<>();
		for(int i=0;i<t;i++) {
			String city= sc.next();
			cities.add(city);
			
		}
		
		mapEurope.floydWarshall(mapEurope.costMatrix);
		System.out.println(mapEurope.searchRoad(cities));
	}
}

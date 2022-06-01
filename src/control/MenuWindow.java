package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.Main;
import model.City;
import model.CityData;
import model.Graph;

public class MenuWindow implements Initializable{

	private String id;
	
	private ArrayList<String>road=new ArrayList<>();
	
	private ArrayList<City>cities=new ArrayList<>();
	
	private Graph<String>map = new Graph<>(68);
	
	public MenuWindow(String id) {
		this.id = id;
	}
	@FXML
    private TextArea citiesTA;

    @FXML
    private TableColumn<City,String> cityColumn;

    @FXML
    private TableColumn<City, String> countryColumn;

    @FXML
    private TableColumn<City, String> flagColumn;

    @FXML
    private TableView<City> tableCountry;

    @FXML
    private Label welcomeLabel;
    
    private City tempCity;
    

    @FXML
    void addCity(ActionEvent event) {
        cities.add(tempCity);
        road.add(tempCity.getName());
        String text="";
        for(int i=0;i<cities.size();i++) {
        	
        	text+= i+1+" "+cities.get(i).getName()+" "+cities.get(i).getCode()+"\n";
        }
        citiesTA.setText(text);
    }

    @FXML
    void deleteCity(ActionEvent event) {
       cities.remove(tempCity);
       road.remove(tempCity.getName());
       String text="";
       for(int i=0;i<cities.size();i++) {
       	
       	text+= i+1+" "+cities.get(i).getName()+" "+cities.get(i).getCode()+"\n";
       }
       citiesTA.setText(text);
    }
    
    @FXML
    void searchClick(ActionEvent event)throws Exception {
         int time= map.searchRoad(road);
         FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/TimeWindow.fxml"));
 		 loader.setController(new TimeWindow(id,time));
 		 Parent parent = (Parent) loader.load();
 		 Stage stage = new Stage();
 		 Scene scene = new Scene(parent);
 		 stage.setScene(scene);
 		 stage.show();
 		 Stage stage2 = (Stage) citiesTA.getScene().getWindow();
 		 stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		citiesTA.setEditable(false);
		welcomeLabel.setText("WELCOME "+ id);
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		flagColumn.setCellValueFactory(new PropertyValueFactory<>("flag"));
		tableCountry.setOnMouseClicked(
				event->{
					tempCity = tableCountry.getSelectionModel().getSelectedItem();
				}
				);
		try {
			program();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chargeMap();
		map.floydWarshall(map.costMatrix);
		tableCountry.setItems(CityData.getData());
	}

	public void program() throws IOException {
		FileReader readFile;
		// read the dataset names
		readFile = new FileReader("src/data/Cities.csv");
		BufferedReader textFile = new BufferedReader(readFile);
		String line;
		String fileComplete = "";
		while ((line = textFile.readLine()) != null) {
			fileComplete += line + ",";
	    }
		String [] countries = fileComplete.split(",");
		int count=1;
		for(int i=0;i<countries.length;i=i+2) {
			String name = countries[i];
			String code = countries[i+1];
			ImageView image = setFlag(count);
			
			image.setFitHeight(15);
			image.setFitWidth(30);
			City city = new City(name,code,image);
			CityData.getData().add(city);
			count++;
		}
	}
	
	public void chargeMap() {
		
		for(int i=0;i<CityData.getData().size();i++) {
			map.addVertex(CityData.getData().get(i).getName());
		}
		
		map.addEdge("Tirana", "Atenas", 70);
		map.addEdge("Berlin", "Dortmund", 255);
		map.addEdge("Berlin", "Frankfurt", 70);
		map.addEdge("Berlin", "Stuttgart", 75);
		map.addEdge("Berlin", "Munich", 70);
		map.addEdge("Berlin", "Copenhague", 65);
		map.addEdge("Frankfurt", "Stuttgart", 75);
		map.addEdge("Munich", "Viena", 65);
		map.addEdge("Munich", "Paris", 95);
		map.addEdge("Munich", "Roma", 90);
		map.addEdge("Munich", "Londres", 120);
		map.addEdge("Munich", "Bruselas", 80);
		map.addEdge("Munich", "Dortmund", 70);
		map.addEdge("Salzburgo", "Viena", 195);
		map.addEdge("Bruselas", "Lieja", 495);
		map.addEdge("Bruselas", "Amberes", 500);
		map.addEdge("Bruselas", "Charleroi", 105);
		map.addEdge("Bruselas", "Amsterdam", 55);
		map.addEdge("Bruselas", "Paris", 55);
		map.addEdge("Charleroi", "Amberes", 105);
		map.addEdge("Minsk", "Moscu", 80);
		map.addEdge("Minsk", "Kiev", 65);
		map.addEdge("Saravejo", "Belgrado", 50);
		map.addEdge("Saravejo", "Zagreb", 50);
		map.addEdge("Sofia", "Atenas", 70);
		map.addEdge("Sofia", "Bucarest", 65);
		map.addEdge("Larnaca", "Atenas", 100);
		map.addEdge("Zagreb", "Belgrado", 65);
		map.addEdge("Copenhague", "Estocolmo", 70);
		map.addEdge("Copenhague", "Aalborg", 50);
		map.addEdge("Bratislava", "Liubliana", 40);
		map.addEdge("Bratislava", "Ginebra", 365);
		map.addEdge("Liubliana", "Niza", 190);
		map.addEdge("Madrid", "Paris", 180);
		map.addEdge("Madrid", "Lisboa", 80);
		map.addEdge("Madrid", "Las Palmas", 175);
		map.addEdge("Madrid", "Ibiza", 75);
		map.addEdge("Madrid", "Sevilla", 70);
		map.addEdge("Madrid", "Roma", 150);
		map.addEdge("Las Palmas", "Sevilla", 130);
		map.addEdge("Barcelona", "Ibiza", 60);
		map.addEdge("Barcelona", "Paris", 115);
		map.addEdge("Barcelona", "Londres", 140);
		map.addEdge("Barcelona", "Sevilla", 100);
		map.addEdge("Oslo", "Helsinki", 90);
		map.addEdge("Helsinki", "Estocolmo", 60);
		map.addEdge("Paris", "Niza", 85);
		map.addEdge("Paris", "Praga", 100);
		map.addEdge("Paris", "Londres",75 );
		map.addEdge("Paris", "Ginebra", 70);
		map.addEdge("Paris", "Amsterdam", 80);
		map.addEdge("Niza", "Roma", 70);
		map.addEdge("Niza", "Zurich", 70);
		map.addEdge("Niza", "Marsella", 130);
		map.addEdge("Nantes", "Paris", 65);
		map.addEdge("Nantes", "Marsella", 85);
		map.addEdge("Paris", "Marsella", 85);
		map.addEdge("Paris", "Lyon", 65);
		map.addEdge("Lyon", "Amsterdam", 100);
		map.addEdge("Atenas", "Budapest", 80);
		map.addEdge("Atenas", "Belgrado", 100);
		map.addEdge("Atenas", "Larnaca", 100);
		map.addEdge("Atenas", "Sofia", 240);
		map.addEdge("Atenas", "Estambul", 90);
		map.addEdge("Budapest", "Saravejo", 155);
		map.addEdge("Budapest", "Sofia", 80);
		map.addEdge("Dublin", "Londres", 80);
		map.addEdge("Dublin", "Keflavik", 165);
		map.addEdge("Florencia", "Roma", 55);
		map.addEdge("Florencia", "Milan", 70);
		map.addEdge("Roma", "Milan", 70);
		map.addEdge("Florencia", "Palermo", 80);
		map.addEdge("Palermo", "Roma", 65);
		map.addEdge("Roma", "Luqa", 85);
		map.addEdge("Roma", "Paris", 130);
		map.addEdge("Roma", "Atenas", 120);
		map.addEdge("Roma", "Zurich", 95);
		map.addEdge("Roma", "Turin", 75);
		map.addEdge("Turin", "Milan", 85);
		map.addEdge("Venecia", "Milan", 160);
		map.addEdge("Venecia", "Napoles", 75);
		map.addEdge("Riga", "Varsovia", 80);
		map.addEdge("Vilna", "Riga", 50);
		map.addEdge("Podgorica", "Belgrado", 50);
		map.addEdge("Podgorica", "Saravejo", 230);
		map.addEdge("Oslo", "Estocolmo", 60);
		map.addEdge("Oslo", "Helsinki", 80);
		map.addEdge("Oslo", "Copenhague", 70);
		map.addEdge("Eindhoven", "Amsterdam", 80);
		map.addEdge("Varsovia", "Berlin", 85);
		map.addEdge("Varsovia", "Moscu", 380);
		map.addEdge("Varsovia", "Kiev", 120);
		map.addEdge("Varsovia", "Kiev", 110);
		map.addEdge("Varsovia", "Cracovia", 55);
		map.addEdge("Las Palmas", "Lisboa", 140);
		map.addEdge("Edimburgo", "Londres", 90);
		map.addEdge("Edimburgo", "Dublin", 70);
		map.addEdge("Liverpool", "Londres", 160);
		map.addEdge("Liverpool", "Manchester", 120);
		map.addEdge("Norwich", "Londres", 40);
		map.addEdge("Dublin", "Londres", 80);
		map.addEdge("Copenhague", "Londres", 110);
		map.addEdge("Praga", "Liubliana", 195);
		map.addEdge("Praga", "Viena", 50);
		map.addEdge("Praga", "Ginebra", 110);
		map.addEdge("Praga", "Berna", 120);
		map.addEdge("Bucarest", "Sofia", 65);
		map.addEdge("Moscu", "Minsk", 80);
		map.addEdge("Belgrado", "Podgorica", 50);
		map.addEdge("Oslo", "Dublin", 165);
		map.addEdge("Ginebra", "Berna", 30);
		map.addEdge("Zurich", "Ginebra", 50);
		map.addEdge("Zurich", "Viena", 80);
		map.addEdge("Estambul", "Larnaca", 255);
		map.addEdge("Minsk", "Kiev", 65);
		
		
	}
	
	public ImageView setFlag(int count) {
		ImageView image=new ImageView(new Image("image/"+count+".png"));
		return image;
		
	}
}

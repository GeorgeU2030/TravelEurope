package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TimeWindow implements Initializable{

	 @FXML
	 private Label timeLabel;
	 
	 @FXML
	    private Label dataLabel;
	 
	 private String id;
	 private int time;
	 private ArrayList<String> road;
	 public TimeWindow(String id,int time, ArrayList<String> road) {
		 this.id=id;
		 this.time=time;
		 this.road=road;
	 }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String msj="";
		for(int i=0; i<road.size();i++) {
			msj+=(", "+road.get(i));
		}
		int hours = time/60;
		int minutes = time-(hours*60);
		timeLabel.setText(id+" the minimum time that you will spend \non the trip between the cities of: \n"+msj+" is:");
		dataLabel.setText(hours+" Hours and "+minutes+ " minutes");
	}
}

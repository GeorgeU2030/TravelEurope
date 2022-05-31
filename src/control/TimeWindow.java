package control;

import java.net.URL;
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
	 public TimeWindow(String id,int time) {
		 this.id=id;
		 this.time=time;
	 }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int hours = time/60;
		int minutes = time-(hours*60);
		timeLabel.setText(id+" the time that you will spend in the travel is ");
		dataLabel.setText(hours+" Hours and "+minutes+ " minutes");
	}
}

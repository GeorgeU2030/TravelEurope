package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class InitWindow implements Initializable{

	@FXML
    private TextField textname;
	
	@FXML
    private Button goBtn;

    @FXML
    void goClick(ActionEvent event) throws Exception{
    	
          if(textname.getText().equals("")) {
        	  Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("ERROR");
	            alert.setContentText("Enter a Valid Name");
	            alert.showAndWait();
	            
        	  
          }else {
        	  String id = textname.getText();
        	  FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MenuWindow.fxml"));
     		 loader.setController(new MenuWindow(id));
     		 Parent parent = (Parent) loader.load();
     		 Stage stage = new Stage();
     		 Scene scene = new Scene(parent);
     		 stage.setScene(scene);
     		 stage.show();
     		 Stage stage2 = (Stage) textname.getScene().getWindow();
     		 stage2.close();
          }
    }

    @FXML
    void startClick(ActionEvent event) {
     textname.setVisible(true);
     goBtn.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		goBtn.setVisible(false);
		textname.setVisible(false);
	}
}

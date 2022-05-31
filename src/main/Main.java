package main;


import control.InitWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			 launch();
		}
		 @Override
			public void start(Stage primaryStage)throws Exception{
			 FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/InitWindow.fxml"));
			 loader.setController(new InitWindow());
			 Parent parent = (Parent) loader.load();
			 Stage stage = new Stage();
			 Scene scene = new Scene(parent);
			 stage.setScene(scene);
			 stage.show();
		   }
	}

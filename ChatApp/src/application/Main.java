package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.closehandle;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			VBox root = FXMLLoader.load(getClass().getResource("/view/loggin.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle("Sign in");
			primaryStage.show();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		

}
}


 
 

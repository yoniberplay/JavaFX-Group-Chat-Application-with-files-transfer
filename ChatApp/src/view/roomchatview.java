package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.closehandle;

public class roomchatview  {
	
	private Stage primaryStage;
	
	public roomchatview() {
		try {
			primaryStage = new Stage(); 
			BorderPane root = FXMLLoader.load(getClass().getResource("/view/roomchat.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					event.consume();

					closehandle.handle();

				}
			});
			primaryStage.setTitle("Yoniber's Chat Room");

			// to access this Stage from everywhere
			closehandle.primaryStage = primaryStage;
			primaryStage.show();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

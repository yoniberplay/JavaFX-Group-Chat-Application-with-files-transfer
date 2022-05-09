package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.connectiondata;
import view.roomchatview;

public class loggincontroller implements Initializable{

	private Stage escenario;
	private double xOffset = 0;
	private double yOffset = 0;
	
	
	@FXML
	private TextField txtname;

	@FXML
	private TextField txtserverip;

	@FXML
	private TextField txtfolderaddress;

	@FXML
	void close(ActionEvent event) {
		System.exit(0);

	}

	@FXML
	void logginkey(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login(null);
		}

	}

	@FXML
	void login(ActionEvent event) {
		
		String name = txtname.getText();
		String folderaddres = txtfolderaddress.getText();
		String ipserver = txtserverip.getText();		
		
		if (!name.equals("") && !folderaddres.equals("") && !ipserver.equals("")) {
			
			connectiondata.folderfiles = folderaddres;
			connectiondata.ipserver = ipserver;
			connectiondata.name = name;
			
			escenario = (Stage) txtname.getScene().getWindow();
			escenario.hide();
			
			new roomchatview();
			
		}
		
	}

	@FXML
	void selectfolder(ActionEvent event) {
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(new Stage());
		if (selectedDirectory != null) {
			
			txtfolderaddress.setText(selectedDirectory.getAbsolutePath()+"\\");
			connectiondata.folderfiles = selectedDirectory.getAbsolutePath()+"\\";
		}
	}
	
	@FXML
	void MouseDrag(MouseEvent event) {
		escenario = (Stage) txtname.getScene().getWindow();
		escenario.setX(event.getScreenX() - xOffset);
		escenario.setY(event.getScreenY() - yOffset);
		
	}

	
	@FXML
	void Mousepress(MouseEvent event) {
		Scene escene = txtname.getScene();
    	escene.setCursor(Cursor.MOVE);
		 xOffset = event.getSceneX();
         yOffset = event.getSceneY();
	}
	
	@FXML
    void Mousereleased(MouseEvent event) {
    	Scene escene = txtname.getScene();
    	escene.setCursor(Cursor.DEFAULT);
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

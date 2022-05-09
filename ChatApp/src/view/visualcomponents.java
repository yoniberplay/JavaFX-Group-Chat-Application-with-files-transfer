package view;

import java.text.MessageFormat;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Usersconnected;

public class visualcomponents {
	
	private Usersconnected user;
	AnchorPane paneuser ;

	public visualcomponents(Usersconnected user) {
		this.user = user;
	}
	
	public AnchorPane userpane() {
		paneuser = new AnchorPane();
		paneuser.setStyle("-fx-background-color:  #303841;");
		Label texto = new Label(user.getName()+"\n"+user.getIp());
		texto.setMaxWidth(220);
		texto.setWrapText(true);
		texto.setFont(Font.font("", FontWeight.BOLD, 14));
		texto.setTextFill(Color.rgb(239, 255, 253));
		texto.setPadding(new Insets(0, 10, 0, 10));
		
		paneuser.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		paneuser.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	System.out.println(user.getName());
		    	Scene escene = paneuser.getScene();
		    	escene.setCursor(Cursor.HAND);
		    }
		});
		
		paneuser.setOnMouseMoved(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	paneuser.setStyle("-fx-background-color: #EFFFFD;");
		    	texto.setTextFill(Color.valueOf("#303841"));
		    	Scene escene = paneuser.getScene();
		    	escene.setCursor(Cursor.HAND);
		    }
		});
		
		paneuser.setOnMouseExited(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	paneuser.setStyle("-fx-background-color:  #303841;");
		    	texto.setTextFill(Color.rgb(239, 255, 253));
		    	Scene escene = paneuser.getScene();
		    	escene.setCursor(Cursor.DEFAULT);
		    }
		});
		
		paneuser.getChildren().addAll(texto);
		
		return paneuser;
	}
	
	
	


	//this method returns the style of the message that is sent to your chat 
	public static HBox bubble(String nombre, String text, String color, boolean alignPos) {

		Label texto = new Label(text);
		texto.setMaxWidth(430);
		texto.setWrapText(true);
		texto.setFont(Font.font("", FontWeight.BOLD, 14));
		texto.setTextFill(Color.WHITE);
		texto.setPadding(new Insets(0, 10, 0, 10));

		// String color="#7FB5FF";
		String estilo = "-fx-background-radius: 5; -fx-background-color: {0};";
		AnchorPane mensageconten = new AnchorPane(texto);
		mensageconten.setStyle(MessageFormat.format(estilo, color));

		HBox panemsj = new HBox();
		if (alignPos) {
			panemsj.setAlignment(Pos.TOP_LEFT);
			panemsj.setPadding((new Insets(5, 0, 0, 10)));
		} else {
			panemsj.setAlignment(Pos.TOP_RIGHT);
			panemsj.setPadding((new Insets(5, 20, 0, 10)));
		}
		Label lbnombre = new Label(nombre);
		lbnombre.setTextFill(Color.WHITE);

		if (alignPos)
			panemsj.getChildren().addAll(lbnombre, mensageconten);
		else
			panemsj.getChildren().addAll(mensageconten);

		return panemsj;
	}

	public Usersconnected getUser() {
		return user;
	}

	public void setUser(Usersconnected user) {
		this.user = user;
	}




}

package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
import java.text.MessageFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
*/

public class message implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private String color;
	private boolean alignPos;
	private String text,ip,nombre;
	private boolean conected;
	private Object []usersList;
	private HashMap<String, Usersconnected> usersconnected;
	private File file;
	
	
	public message(String nombre,String ip, String text,boolean conected,File file) {
		//this.color = "gray";
		this.alignPos = true;
		this.conected = conected;
		this.text = text;
		this.nombre=nombre;
		this.ip=ip;
		this.file = file;
	}
	
	
	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public HashMap<String, Usersconnected> getUsersconnected() {
		return usersconnected;
	}


	public void setUsersconnected(HashMap<String, Usersconnected> usersconnected) {
		this.usersconnected = usersconnected;
	}


	public boolean isConected() {
		return conected;
	}


	public void setConected(boolean conected) {
		this.conected = conected;
	}


	public Object[] getusersList() {
		return usersList;
	}


	public void setusersList(Object[] usersConnected) {
		this.usersList = usersConnected;
	}



	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isAlignPos() {
		return alignPos;
	}
	public void setAlignPos(boolean alignPos) {
		this.alignPos = alignPos;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}




	/*public HBox bubble() {
		
		Label texto = new Label(text);
    	texto.setMaxWidth(430);
    	texto.setWrapText(true);
    	texto.setFont(Font.font("",FontWeight.BOLD,14));
    	texto.setTextFill(Color.WHITE);    	
    	texto.setPadding(new Insets(0, 10, 0, 10));
    	
    	//String color="#7FB5FF";
    	String estilo= "-fx-background-radius: 5; -fx-background-color: {0};";
    	AnchorPane mensageconten = new AnchorPane(texto);
    	mensageconten.setStyle(MessageFormat.format(estilo, color));
    	
    	HBox panemsj = new HBox();
    	if (alignPos) {
    		panemsj.setAlignment(Pos.TOP_LEFT);
    		panemsj.setPadding((new Insets(0, 0, 5, 10)));
    	}
    	else {
    		panemsj.setAlignment(Pos.TOP_RIGHT);
    		panemsj.setPadding((new Insets(5, 20, 5, 10)));
    	}
    	Label lbnombre = new Label(nombre);
    	lbnombre.setTextFill(Color.WHITE);   
    	
    	if (alignPos) panemsj.getChildren().addAll(lbnombre,mensageconten);
    	else panemsj.getChildren().addAll(mensageconten);
    	
    	return panemsj;
	}*/
	
	

}

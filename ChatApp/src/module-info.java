module ChatApp {
	
	requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires java.xml;
	requires javafx.graphics;
	requires javafx.base;

	
	opens controller to javafx.fxml;
	opens model to javafx.fxml;
	
	exports controller;
	exports model;
	exports application;
	
	opens application to javafx.graphics;
}

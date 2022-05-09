package model;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.stage.Stage;

public class closehandle {
	
	public static Stage primaryStage;
	public static final String usuario = connectiondata.name;
	
	
	
	public static void handle() {

		if (primaryStage != null) {
			
			try {
				Socket s = new Socket(connectiondata.ipserver, 9999);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				String ip = InetAddress.getLocalHost().getHostAddress();
				oo.writeObject(new message(usuario, ip, "",false,null));
				oo.close();
				s.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Platform.exit();
			System.exit(0);
		} else
			System.out.println("PrimaryStage is null");

	}

}

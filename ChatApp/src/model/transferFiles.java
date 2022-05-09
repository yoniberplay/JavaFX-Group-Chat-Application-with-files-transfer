package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class transferFiles implements Runnable {
	
	
	private File filetemp;
	
	public transferFiles(File filetemp) {
		this.filetemp = filetemp;
	}


	public void run() {

		ServerSocket serverSocket = null;


			try {
				serverSocket = new ServerSocket(4444);
			} catch (IOException ex) {
				System.out.println("Can't setup server on this port number. ");
			}
			Socket socket = null;
			InputStream in = null;
			OutputStream out = null;
			String desktoppath = System.getProperty("user.home") + "\\Desktop\\";
			try {
				socket = serverSocket.accept();
				System.out.println("llego un archivo");
				in = socket.getInputStream();
				out = new FileOutputStream(desktoppath + filetemp.getName());
				byte[] bytes = new byte[16 * 1024];

				int count;

				while ((count = in.read(bytes)) > 0) {
					out.write(bytes, 0, count);
				}
				out.close();
				in.close();
				socket.close();
				serverSocket.close();
			} catch (IOException ex) {
				System.out.println("Can't accept client connection. ");
			}
			
	}

}

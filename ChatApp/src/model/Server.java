package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;


/*@YoniberPlay
 * This server must be execute in a same Lan
 * print its own ip on CMD
 * so clients know which server to point to
 * 
 */
public class Server  implements Runnable{
	
	static final int port = 9999;
	static final int portfiles = 4444;
	static HashSet<String> set = new HashSet<String>();
	static HashMap<String, Usersconnected> usersconnected = new HashMap<String, Usersconnected>();
	static ArrayList<String> listiptodelete = new ArrayList<String>();
	private static File filetemp;
	private static Object []listaip;
	
	
	public Server() {

		Thread tfiles = new Thread(this);
		tfiles.start();

	}

	public static void main(String[] args) {

		// I use HashSet because not allow duplicates.
		HashSet<String> colorlist = new HashSet<String>();

		Usersconnected user;

		ServerSocket ss;
		try {
			System.out.println("IP Server " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Port Server " + port);
			System.out.println("Files transfer Port " + portfiles);

			while (true) {
				ss = new ServerSocket(port);
				Socket s = ss.accept();
				ObjectInputStream oi = new ObjectInputStream(s.getInputStream());
				message msj = (message) oi.readObject();
				String ip = msj.getIp();
				// add user to list of users connected and set it's color
				if (!set.contains(ip)) {
					set.add(ip);
					user = new Usersconnected();
					user.setIp(ip);
					user.setName(msj.getNombre());
					// userslist.add(user);

					Random obj = new Random();
					while (true) {
						int rand_num = obj.nextInt(0xffffff + 1);
						// format it as hexadecimal string and print
						String colorCode = String.format("#%06x", rand_num);
						if (!colorlist.contains(colorCode) && !colorCode.equals("#7FB5FF")) {
								colorlist.add(colorCode);
								user.setColor(colorCode);
								usersconnected.put(msj.getIp(), user);
								break;
							}
						}
					}
					
					//remove disconnected user
					if (msj.isConected() == false) {
						set.remove(msj.getIp());
					}
					
					// sending a message to each ip with their own color
					listaip = set.toArray();
					
					msj.setusersList(listaip);
					msj.setUsersconnected(usersconnected);
					
					
					
					for (Object pu : listaip) {
						String color = usersconnected.get(msj.getIp()).getColor();
						msj.setColor(color);
						senttoallclient((String)pu, msj);
					}
					
					
					if (msj.getFile() != null) {
						filetemp = msj.getFile();
						new Server();
					}
					
					//delete clients disconect
					for (String dl : listiptodelete) {
						usersconnected.remove(dl);
					}
					listiptodelete.clear();
					
					// print each message info
					System.out.println(msj.getNombre() + ": " + msj.getIp());
					if(msj.getText() == null) System.out.println("Connected.");
					else if(msj.isConected() == false) System.out.println("Disconected.");
					else System.out.println(msj.getText());
					
					
				ss.close();
				s.close();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sendingfiles(String pu) {
		 try {
			 	String desktoppath = System.getProperty("user.home") + "\\Desktop\\"+filetemp.getName();
				Socket socket = null;
				File selectedFile  = new File(desktoppath);

				socket = new Socket(pu, 4455);

				// Get the size of the file
				long length = selectedFile.length();
				byte[] bytes = new byte[(int) length];
				InputStream in = new FileInputStream(selectedFile);
				OutputStream out = socket.getOutputStream();

				int count;
				while ((count = in.read(bytes)) > 0) {
					out.write(bytes, 0, count);
				}
				out.close();
				in.close();
				socket.close();

			} catch (IOException e) {
				System.out.println("Error to send "+pu);
				System.out.println(e.getMessage());
			}
	}

	private static void senttoallclient(String ip, message msj) {
		
		try {
			Socket s = new Socket(ip, 3000);
			ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
			oo.writeObject(msj);
			oo.close();
			s.close();
		} catch (Exception e) {
			System.out.println(ip+" is offline.");
			set.remove(ip);
			listiptodelete.add(ip);
			
			
		}
	}

	public void run() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(portfiles);
		} catch (IOException ex) {
			System.out.println("Can't setup server on this port number. ");
		}
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		
		String desktoppath = System.getProperty("user.home") + "\\Desktop\\"+filetemp.getName();
		
		try {
			socket = serverSocket.accept();
			System.out.println("File received");
			in = socket.getInputStream();
			out = new FileOutputStream(desktoppath);
			byte[] bytes = new byte[16 * 1024];

			int count;

			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}
			out.close();
			in.close();
			socket.close();
			serverSocket.close();
			
			for (Object pu : listaip) {
				sendingfiles((String)pu);
				
				//Thread.sleep(2000);
			}
			
		} catch (Exception ex) {
			System.out.println("Can't accept client connection. ");
		}
	}
	


}

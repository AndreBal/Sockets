package by.epamtc.jwd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import by.epamtc.jwd.server.controller.Controller;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.properties.ResourceManager;
import by.epamtc.jwd.server.dao.properties.ServerParameter;

public class Main {
	
		private static ResourceManager manager = ResourceManager.getInstance();
		private static final int PORT = Integer.parseInt(manager.getValue(ServerParameter.PORT));

	    private static ServerSocket serverSocket;

	public static void main(String[] args) {
		
		try {
            serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                Controller controller = new Controller();
                controller.start(clientSocket);
                clientSocket.close();
            }
        } catch ( DAOException | IOException e) { 
        	e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }

	}

}

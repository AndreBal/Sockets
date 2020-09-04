package by.epamtc.jwd.client;

import by.epamtc.jwd.client.controller.Controller;
import by.epamtc.jwd.client.dao.properties.ClientParameter;
import by.epamtc.jwd.client.dao.properties.ResourceManager;

public class Main {
	private static ResourceManager manager = ResourceManager.getInstance();
	private final static String HOST = manager.getValue(ClientParameter.HOST);
	private final static int PORT = Integer.parseInt(manager.getValue(ClientParameter.WAIT_TIME));

	public static void main(String[] args) {

		Controller controller = new Controller();
		controller.start(HOST, PORT);

	}

}

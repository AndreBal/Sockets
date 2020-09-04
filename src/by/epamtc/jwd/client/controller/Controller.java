package by.epamtc.jwd.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.client.dao.properties.ClientParameter;
import by.epamtc.jwd.client.dao.properties.ResourceManager;
import by.epamtc.jwd.client.dao.reader.Reader;
import by.epamtc.jwd.client.view.View;
import by.epamtc.jwd.server.dao.exception.DAOException;

public class Controller {

	private static ResourceManager manager = ResourceManager.getInstance();
	private static final int WAIT_TIME = Integer.parseInt(manager.getValue(ClientParameter.WAIT_TIME));
	private static final String END = manager.getValue(ClientParameter.END);

	private final BufferedReader consoleReader;
	private final View view;
	private final Reader txtReader;

	private Text desiredText;

	private Socket clientSocket;
	private ObjectInputStream objectIn;
	private OutputStream out;

	public Controller() {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		view = new View();
		txtReader = new Reader();
	}

	public void start(final String HOST, final int PORT) {
		try {
			try {
				clientSocket = new Socket(HOST, PORT);

				objectIn = new ObjectInputStream(clientSocket.getInputStream());
				out = clientSocket.getOutputStream();

				view.printWelcomeMessage(objectIn);

				sendRequest();
				deserializeText();

				view.printFormattedText(desiredText);
			} finally {
				clientSocket.close();
			}
		} catch (IOException | DAOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sendRequest() throws IOException, DAOException {
		String typeOfEdit = consoleReader.readLine() + "\n";
		String allText = txtReader.read();

		String request = typeOfEdit + allText + END;

		out.write(request.getBytes());
		out.flush();
	}

	private void deserializeText() throws IOException, ClassNotFoundException {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		desiredText = (Text) objectIn.readObject();
	}

}

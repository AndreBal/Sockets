package by.epamtc.jwd.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.server.dao.DocumentDao;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.impl.TextDocumentDao;
import by.epamtc.jwd.server.dao.properties.ResourceManager;
import by.epamtc.jwd.server.dao.properties.ServerParameter;
import by.epamtc.jwd.server.service.Service;
import by.epamtc.jwd.server.service.impl.TextService;

public class Controller {

	private static ResourceManager manager = ResourceManager.getInstance();
	private static final String END = manager.getValue(ServerParameter.END);

	private Service textService;

	private BufferedReader requestReader;
	private ObjectOutputStream output;
	private DocumentDao documentDao;

	public Controller() throws DAOException {
		textService = new TextService();
		documentDao = new TextDocumentDao();
	}

	public void start(final Socket clientSocket) {
		try {
			requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new ObjectOutputStream(clientSocket.getOutputStream());

			greetUser(output);

			String strCommand = readEditNumber();
			String rawText = readTextData();

			Text text = documentDao.stringToText(rawText);

			Text modifiedText = modifyText(text, strCommand);

			if (modifiedText != null) {
				output.writeObject(modifiedText);
			} else {
				output.write(manager.getValue(ServerParameter.ERROR).getBytes());
			}
		} catch (DAOException | IOException e) {
			e.printStackTrace();
		}
	}

	private void greetUser(ObjectOutputStream objectOut) throws IOException {
		String greetings = manager.getValue(ServerParameter.GREETINGS);
		objectOut.write(greetings.getBytes());
		objectOut.flush();
	}

	private String readEditNumber() throws IOException {
		return requestReader.readLine();
	}

	private String readTextData() throws IOException {
		StringBuilder buff = new StringBuilder();
		String line;
		while (true) {
			line = requestReader.readLine();
			if (line.contains(END)) {
				break;
			}
			buff.append(line).append("\n");
		}
		return buff.toString();
	}

	private Text modifyText(Text text, String strCommand) {
		int command = Integer.parseInt(strCommand);
		switch (command) {
		case (2):
			return textService.sortSentencesByWordCount(text);
		case (4):
			int wordLength = Integer.parseInt(strCommand.split(";")[1]);
			return textService.findWordsInQuestions(text, wordLength);
		case (13):
			char c = strCommand.split(";")[1].charAt(0);
			return textService.sortWordsByCharacterProportion(text, c);
		default:
			return null;

		}

	}
}

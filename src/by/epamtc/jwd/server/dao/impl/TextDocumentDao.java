package by.epamtc.jwd.server.dao.impl;

import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.server.dao.DocumentDao;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.impl.parser.ParserFactory;
import by.epamtc.jwd.server.dao.impl.parser.TextParser;

public class TextDocumentDao implements DocumentDao {
	
	private TextParser textParser;
	
	public TextDocumentDao() throws DAOException {
		textParser = ParserFactory.getInstance().getTextParser();
	}

	@Override
	public Text stringToText(String rawText) throws DAOException {
		return textParser.parseText(rawText);
	}

}

package by.epamtc.jwd.server.dao.impl.parser;

import by.epamtc.jwd.server.dao.exception.DAOException;

public class ParserFactory {
	
	private static final ParserFactory INSTANCE = new ParserFactory();
	
	private SentenceParser sentence;
	private TextParser text;
	private SentenceFragmentsParser fragments;
	
	private ParserFactory(){
	}
	
	public static ParserFactory getInstance() {
		return INSTANCE;
	}
	
	public SentenceParser getSentenceParser() throws DAOException {
		if(sentence == null) {
			sentence = new SentenceParser();
		}
		return sentence;
	}
	
	public TextParser getTextParser() throws DAOException {
		if(text == null) {
			text = new TextParser();
		}
		return text;
	}
	
	public SentenceFragmentsParser getSentenceFragmentsParser() throws DAOException {
		if(fragments == null) {
			fragments = new SentenceFragmentsParser();
		}
		return fragments;
	}
}

package by.epamtc.jwd.server.dao.impl.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.Code;
import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.properties.RegexParameter;
import by.epamtc.jwd.server.dao.properties.ResourceManager;

public class TextParser {

	private SentenceParser sentenceParser;
	private String componentsRegExp;
	private Pattern pattern;

	public TextParser() throws DAOException {
		sentenceParser = ParserFactory.getInstance().getSentenceParser();
		componentsRegExp = ResourceManager.getInstance().getValue(RegexParameter.CODE_OR_TEXT_REG_EXP);
		pattern = Pattern.compile(componentsRegExp);
	
	}

	public Text parseText(String rawText) throws DAOException {
		
		Text text = new Text();

		Matcher matcher = pattern.matcher(rawText);

		while (matcher.find()) {
			String textBlock = matcher.group("Text");
			String codeBLock = matcher.group("Code");

			if (textBlock != null) {
				List<Fragment> sentences = sentenceParser.parse(textBlock);
				for (Fragment c : sentences) {
					text.add(c);
				}
			}

			if (codeBLock != null) {
				text.add(new Code(codeBLock));
			}
		}

		return text;
	}

}

package by.epamtc.jwd.server.dao.impl.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.PunctuationMark;
import by.epamtc.jwd.bean.impl.Word;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.properties.RegexParameter;
import by.epamtc.jwd.server.dao.properties.ResourceManager;

public class SentenceFragmentsParser {

	private String SentenceFragmentsRegExp;
	private String wordRegEx;
	private Pattern pattern;

	public SentenceFragmentsParser() throws DAOException {
		SentenceFragmentsRegExp = ResourceManager.getInstance().getValue(RegexParameter.SENTENCE_FRAGMENTS_REG_EXP);
		pattern = Pattern.compile(SentenceFragmentsRegExp);

		wordRegEx = ResourceManager.getInstance().getValue(RegexParameter.WORD_REG_EXP);
	}

	public List<Fragment> parseSentenceFragments(String sentence) {
		List<Fragment> partsOfSentence = new ArrayList<>();

		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			String part = matcher.group().intern();
			if (part.matches(wordRegEx)) {
				partsOfSentence.add(new Word(part));
			} else {
				partsOfSentence.add(new PunctuationMark(part));
			}
		}

		return partsOfSentence;
	}

}

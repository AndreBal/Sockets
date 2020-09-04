package by.epamtc.jwd.server.dao.impl.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.Sentence;
import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.properties.RegexParameter;
import by.epamtc.jwd.server.dao.properties.ResourceManager;

public class SentenceParser {

	private String sentenceRegEx;
	private SentenceFragmentsParser sentenceFragmentParser;
	private Pattern sentencePattern;

	public SentenceParser() throws DAOException {
		sentenceRegEx = ResourceManager.getInstance().getValue(RegexParameter.SENTENCE_REG_EXP);

		sentenceFragmentParser = ParserFactory.getInstance().getSentenceFragmentsParser();
		sentencePattern = Pattern.compile(sentenceRegEx);
	}

	public List<Fragment> parse(String textBlock) {
		List<Fragment> sentences = new ArrayList<>();

		Matcher sentenceMatcher = sentencePattern.matcher(textBlock);

		while (sentenceMatcher.find()) {
			Sentence sentence = new Sentence();

			String sentenceString = sentenceMatcher.group();
			List<Fragment> partsOfSentence = sentenceFragmentParser.parseSentenceFragments(sentenceString);

			for (Fragment f : partsOfSentence) {
				sentence.add(f);
			}

			sentences.add(sentence);
		}

		return sentences;
	}

}

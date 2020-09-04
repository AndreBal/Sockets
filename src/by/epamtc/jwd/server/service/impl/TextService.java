package by.epamtc.jwd.server.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.Sentence;
import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.bean.impl.Word;
import by.epamtc.jwd.server.service.Service;
import by.epamtc.jwd.server.service.impl.comparator.CharCountComparator;
import by.epamtc.jwd.server.service.impl.comparator.WordCountComparator;

public class TextService implements Service {

	private Comparator<Sentence> wordCountComparartor = new WordCountComparator();
	private CharCountComparator charCountComparator = new CharCountComparator();
	private ConverterToText converter = new ConverterToText();

	// 2 вывести все предложения заданного текста в порядке возрастания количества
	// слов в каждом из них
	@Override
	public Text sortSentencesByWordCount(Text text) {
		List<Sentence> sentences = getSentecesFromText(text);
		sentences.sort(wordCountComparartor);
		return converter.convert(sentences);
	}

	private List<Sentence> getSentecesFromText(Text text) {
		List<Sentence> sentences = new ArrayList<Sentence>();
		List<Fragment> textFragments = text.getText();
		for (Fragment f : textFragments) {
			if (f instanceof Sentence) {
				sentences.add((Sentence) f);
			}
		}
		return sentences;
	}

	// 4 во всех вопросительных предложениях текста найти и напечатать без
	// повторений слова заданной длины
	@Override
	public Text findWordsInQuestions(Text text, int wordLength) {
		List<Sentence> questions = getQuestionsFromText(text);
		Set<Word> resultWords = new HashSet<Word>();
		for (Sentence s : questions) {
			List<Fragment> words = s.getSentence();
			for (Fragment f : words) {
				if (f instanceof Word && f.getContent().trim().length() == wordLength) {
					resultWords.add((Word) f);
				}
			}
		}
		List<Word> result = new ArrayList<Word>();
		result.addAll(resultWords);
		return converter.convert(result);
	}

	private List<Sentence> getQuestionsFromText(Text text) {
		List<Sentence> questions = new ArrayList<Sentence>();
		List<Fragment> textFragments = text.getText();
		for (Fragment sen : textFragments) {
			if (sen instanceof Sentence) {
				Sentence sentence = (Sentence) sen;
				List<Fragment> sentenseFragments = sentence.getSentence();
				for (Fragment f : sentenseFragments) {
					if (f.getContent().trim().equals("?")) {
						questions.add(sentence);
					}
				}
			}
		}
		return questions;
	}

	// 13 отсортировать слова в тексте по убыванию количества вхождений заданного
	// символа, а в случае равенства -- по алфавиту.
	@Override
	public Text sortWordsByCharacterProportion(Text text, char c) {
		List<Word> words = getWordsFromText(text);
		charCountComparator.setCompareChar(c);
		words.sort(charCountComparator);
		return converter.convert(words);
	}

	private List<Word> getWordsFromText(Text text) {
		List<Sentence> sentences = getSentecesFromText(text);
		List<Word> words = new ArrayList<Word>();
		for (Sentence s : sentences) {
			List<Fragment> fragments = s.getSentence();
			for (Fragment f : fragments) {
				if (f instanceof Word) {
					words.add((Word) f);
				}
			}
		}

		return words;
	}
}

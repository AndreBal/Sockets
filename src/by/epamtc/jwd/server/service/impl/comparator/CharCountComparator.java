package by.epamtc.jwd.server.service.impl.comparator;

import java.util.Comparator;

import by.epamtc.jwd.bean.impl.Word;

public class CharCountComparator implements Comparator<Word> {

	private char compareChar = 'a';

	@Override
	public int compare(Word o1, Word o2) {
		String word1 = o1.getContent();
		String word2 = o2.getContent();

		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != ' ')
				count1++;
		}
		for (int i = 0; i < word2.length(); i++) {
			if (word2.charAt(i) != ' ')
				count2++;
		}
		return count1 - count2;
	}

	public char getCompareChar() {
		return compareChar;
	}

	public void setCompareChar(char compareChar) {
		this.compareChar = compareChar;
	}

}

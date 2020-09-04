package by.epamtc.jwd.server.service.impl.comparator;

import java.util.Comparator;

import by.epamtc.jwd.bean.impl.Sentence;

public class WordCountComparator implements Comparator<Sentence> {

	@Override
	public int compare(Sentence o1, Sentence o2) {
		return o1.getSentence().size() - o2.getSentence().size();
	}

}

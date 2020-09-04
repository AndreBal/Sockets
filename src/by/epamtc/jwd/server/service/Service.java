package by.epamtc.jwd.server.service;


import by.epamtc.jwd.bean.impl.Text;

public interface Service {
	//2 вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них
	Text sortSentencesByWordCount(Text text);
	
	//4 во всех вопросительных предложениях текста найти и напечатать без повторений слова заданной длины
	Text findWordsInQuestions(Text text, int wordLength);
	
	//13 отсортировать слова в тексте по убыванию количества вхождений заданного символа, а в случае равенства -- по алфавиту.
	Text sortWordsByCharacterProportion(Text text, char c);
}

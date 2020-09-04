package by.epamtc.jwd.server.service;


import by.epamtc.jwd.bean.impl.Text;

public interface Service {
	//2 ������� ��� ����������� ��������� ������ � ������� ����������� ���������� ���� � ������ �� ���
	Text sortSentencesByWordCount(Text text);
	
	//4 �� ���� �������������� ������������ ������ ����� � ���������� ��� ���������� ����� �������� �����
	Text findWordsInQuestions(Text text, int wordLength);
	
	//13 ������������� ����� � ������ �� �������� ���������� ��������� ��������� �������, � � ������ ��������� -- �� ��������.
	Text sortWordsByCharacterProportion(Text text, char c);
}

package by.epamtc.jwd.server.dao;

import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.server.dao.exception.DAOException;

public interface DocumentDao {
	
	Text stringToText(String rawText) throws DAOException;

}

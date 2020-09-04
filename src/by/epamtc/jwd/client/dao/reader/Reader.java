package by.epamtc.jwd.client.dao.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import by.epamtc.jwd.server.dao.exception.DAOException;
import by.epamtc.jwd.server.dao.properties.ReaderParameter;
import by.epamtc.jwd.server.dao.properties.ResourceManager;

public class Reader {
	
	ResourceManager manager = ResourceManager.getInstance();
	String path = manager.getValue(ReaderParameter.PATH_TO_TEXT);
	Path pathToFile = Paths.get(path);
	
	public String read() throws DAOException {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader fileReader = Files.newBufferedReader(pathToFile)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }

        return builder.toString();
    }

}

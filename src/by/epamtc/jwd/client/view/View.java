package by.epamtc.jwd.client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.Text;
import by.epamtc.jwd.client.dao.properties.ClientParameter;
import by.epamtc.jwd.client.dao.properties.ResourceManager;

public class View {
	private static ResourceManager manager = ResourceManager.getInstance();
	private static final String END = manager.getValue(ClientParameter.END);

    public void printWelcomeMessage(ObjectInputStream objectIn) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(objectIn), 1);
        String line;
        while (true) {
            line = reader.readLine();
            if (line.contains(END)) {
                break;
            }
            printMessage(line);
        }
    }

    public void printFormattedText(Text text) {
        List<Fragment> fragments = text.getText();
        StringBuilder buff = new StringBuilder();
        buff.append("\n");
        for (Fragment f : fragments) {
            buff.append(f.getContent()).append("\n");
        }
        System.out.println(buff.toString());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}

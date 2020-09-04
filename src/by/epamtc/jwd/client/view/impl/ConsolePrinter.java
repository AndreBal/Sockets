package by.epamtc.jwd.client.view.impl;

import java.util.List;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.client.view.Printer;

public class ConsolePrinter implements Printer {

	@Override
	public void print(List<? extends Fragment> fragments) {
		StringBuilder builder = new StringBuilder();

       for (Fragment f : fragments) {
        	builder.append(f.getContent()).append("\n");
        }

        System.out.println(builder.toString());
		
	}

}

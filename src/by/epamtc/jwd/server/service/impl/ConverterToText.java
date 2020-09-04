package by.epamtc.jwd.server.service.impl;

import java.util.List;

import by.epamtc.jwd.bean.Fragment;
import by.epamtc.jwd.bean.impl.Text;

public class ConverterToText {
	public Text convert(List<? extends Fragment> fragments) {
		Text result = new Text();

       for (Fragment f : fragments) {
        	result.add(f);
        }

		 return result;
	}

}

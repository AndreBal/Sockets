package by.epamtc.jwd.bean.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamtc.jwd.bean.Fragment;

public class Text implements Fragment {
	
	private static final long serialVersionUID = 1L;
	private List<Fragment> text;
	
	public Text() {
		text = new ArrayList<Fragment>();
	}
	
	public void add(Fragment fragment) {
		text.add(fragment);
	}

	@Override
	public String getContent() {
		StringBuilder builder = new StringBuilder();

        for (Fragment f : text) {
        	builder.append(f.getContent());
        }

        return builder.toString();
	}

	public List<Fragment> getText() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Text other = (Text) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Text [text=" + text + "]";
	}

}

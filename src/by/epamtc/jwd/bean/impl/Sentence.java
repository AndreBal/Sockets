package by.epamtc.jwd.bean.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamtc.jwd.bean.Fragment;

public class Sentence implements Fragment{
	
	private static final long serialVersionUID = 1L;
	private List<Fragment> sentence;
	
	public Sentence(){
		this.sentence = new ArrayList<Fragment>();
		
	}
	
	public void add(Fragment fragment) {
		sentence.add(fragment);
	}

	@Override
	public String getContent() {
		StringBuilder builder = new StringBuilder();

        for (Fragment f : sentence) {
        	builder.append(f.getContent());
        }

        return builder.toString();
	}

	public List<Fragment> getSentence() {
		return sentence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sentence == null) ? 0 : sentence.hashCode());
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
		Sentence other = (Sentence) obj;
		if (sentence == null) {
			if (other.sentence != null)
				return false;
		} else if (!sentence.equals(other.sentence))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sentence [sentence=" + sentence + "]";
	}
	
	
	
}

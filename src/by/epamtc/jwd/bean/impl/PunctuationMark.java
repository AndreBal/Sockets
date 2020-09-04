package by.epamtc.jwd.bean.impl;

import by.epamtc.jwd.bean.Fragment;

public class PunctuationMark implements Fragment {
	
	private static final long serialVersionUID = 1L;
	private String mark;
	
	public PunctuationMark(String mark) {
        this.mark = mark;
    }

	@Override
	public String getContent() {
		return mark;
	}
	
	public String getMark() {
		return mark;
	}
	
	@Override
	public String toString() {
		return "PunctuationMark [mark=" + mark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
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
		PunctuationMark other = (PunctuationMark) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		return true;
	}
	
	
}

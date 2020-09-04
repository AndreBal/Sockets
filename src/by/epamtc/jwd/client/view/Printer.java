package by.epamtc.jwd.client.view;

import java.util.List;

import by.epamtc.jwd.bean.Fragment;

public interface Printer {
	void print(List<? extends Fragment> fragments);
}

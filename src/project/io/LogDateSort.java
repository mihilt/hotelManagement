package project.io;

import java.util.Comparator;

import project.vo.Log;

public class LogDateSort implements Comparator<Log> {

	@Override
	public int compare(Log o1, Log o2) {
		return o2.getSituationTime().compareTo(o1.getSituationTime());
	}

}

package main.java.com.SpringBatch02.trial01;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemReader<ParsedData> {
	
	private List<ParsedData> dataList;
	private Iterator<ParsedData> iterator;

	@Override
	public ParsedData read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {

		if (getIterator().hasNext()) {
			return getIterator().next();
		}
		return null;
	}

	public List<ParsedData> getDataList() {
		return dataList;
	}

	public void setDataList(List<ParsedData> dataList) {
		this.dataList = dataList;
	}

	public Iterator<ParsedData> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<ParsedData> iterator) {
		this.iterator = iterator;
	}

}

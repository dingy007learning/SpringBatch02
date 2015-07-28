package main.java.com.SpringBatch02.trial01;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<ParsedData, ParsedData> {

	@Override
	public ParsedData process(final ParsedData item) throws Exception {
		final String id = encode(item.getId());
		final String desc = encode(item.getDescription());
		final ParsedData encodedData = new ParsedData(id, desc);
		return encodedData;
	}

	private String encode(String rawString) {
		StringBuffer str = new StringBuffer(rawString);
		return str.reverse().toString();
	}

}

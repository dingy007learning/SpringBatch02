package main.java.com.SpringBatch02.trial01;

import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

public class CustomDelimitedAggregator extends DelimitedLineAggregator<ParsedData> {
	
	public String doAggregate(Object[] fields) {
		return super.doAggregate(fields);
	}
	
}

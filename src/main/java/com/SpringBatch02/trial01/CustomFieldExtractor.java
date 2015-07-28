package main.java.com.SpringBatch02.trial01;

import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;

public class CustomFieldExtractor extends BeanWrapperFieldExtractor<ParsedData> {
	
	public Object[] extract(ParsedData data) {
		System.out.println("Calling Data: " + data);
		
		Object[] results = super.extract(data);
		return results;
	}
}

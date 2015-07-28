package main.java.com.SpringBatch02.trial01;

import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

public class CustomFlatFileItemWriter extends FlatFileItemWriter<ParsedData> {

	public void write(List<? extends ParsedData> arg0) throws Exception {
		super.write(arg0);
	}
}

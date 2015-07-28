package main.java.com.SpringBatch02.trial01;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomWriter implements ItemWriter<ParsedData> {

	@Override
	public void write(List<? extends ParsedData> items) throws Exception {
		System.out.println("Writing Data : " + items);
	}

}

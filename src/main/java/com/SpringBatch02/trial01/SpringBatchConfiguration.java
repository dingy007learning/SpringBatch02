package main.java.com.SpringBatch02.trial01;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	private String mode = "flat";
	
	// Readers
	@Bean
	public ItemReader<ParsedData> reader() {
		if (("flat").equalsIgnoreCase(this.mode)) {
			FlatFileItemReader<ParsedData> reader = new FlatFileItemReader<ParsedData>();
			reader.setResource(new ClassPathResource("input.csv"));
			reader.setLineMapper(new DefaultLineMapper<ParsedData>() {
				{
					setLineTokenizer(new DelimitedLineTokenizer() {
						{
							setNames(new String[] {"id","description"});
						}
					});
					setFieldSetMapper(new BeanWrapperFieldSetMapper<ParsedData>() {
						{
							setTargetType(ParsedData.class);
						}
					});
				}
			});
			
			return reader;
		}
		else {
			//Using a dummy input data
			CustomItemReader reader = new CustomItemReader();
			List<ParsedData> datas = new ArrayList<ParsedData>();
			datas.add(new ParsedData("item1","Description1"));
			datas.add(new ParsedData("item2","Description2"));
			reader.setDataList(datas);
			reader.setIterator(datas.iterator());
			return reader;
		}
	}
	//Processors
	@Bean
	public ItemProcessor<ParsedData, ParsedData> processor() {
		return new CustomItemProcessor();
	}
	
	//Writers
	@Bean
	public ItemWriter<ParsedData> writer(DataSource datasource){
		if ("mysql".equalsIgnoreCase(this.mode)) {
			JdbcBatchItemWriter<ParsedData> writer = new JdbcBatchItemWriter<ParsedData>();
			writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ParsedData>());
			writer.setSql("INSERT into PARSEDDATA (id, description) VALUES (:id, :description)");
			writer.setDataSource(datasource);
			writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ParsedData>());
			return writer;
		}
		else if("flat".equalsIgnoreCase(this.mode)) {
			System.out.println("Writing to flat file");
			FlatFileItemWriter<ParsedData> writer = new CustomFlatFileItemWriter();
			writer.setResource(new ClassPathResource("output.csv"));
			
			BeanWrapperFieldExtractor<ParsedData> fieldExtractor = new BeanWrapperFieldExtractor<ParsedData>();
			fieldExtractor.setNames(new String[] {"id", "description"});
			
			DelimitedLineAggregator<ParsedData> delimitedLineAgg = new DelimitedLineAggregator<ParsedData>();
			delimitedLineAgg.setDelimiter(",");
			delimitedLineAgg.setFieldExtractor(fieldExtractor);
			
			writer.setLineAggregator(delimitedLineAgg);
			return writer;
		}
		else {
			CustomWriter writer = new CustomWriter();
			return writer;
		}
	}
	
	//JOBS
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1)//.next(s2)
				.end()
				.build();
	}

	//STEPS
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<ParsedData> reader, ItemWriter<ParsedData> writer,
			ItemProcessor<ParsedData, ParsedData> processor) {
		return stepBuilderFactory.get("step1")
				.<ParsedData, ParsedData> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	//UTILITY
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springwebmvcprj");
		dataSource.setUsername("root");
		return dataSource;
	}
	
	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}
}

package main.java.com.SpringBatch02.trial01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Adapted from http://www.javacodegeeks.com/2015/03/spring-batch-tutorial.html by Dani Buiza
 *
 */
@SpringBootApplication
public class SpringBatch02Main implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringBatch02Main.class, args);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Running Spring Batch process...");
	
	/*
	 * //different implementations for different scenarios are
	 * neededList<CustomPojo> pojos =
	 * jdbcTemplate.query("SELECT * FROM pojo", new RowMapper<CustomPojo>()
	 * {
	 * 
	 * @Override public CustomPojo mapRow(ResultSet rs, int row) throws
	 * SQLException { return new CustomPojo(rs.getString(1),
	 * rs.getString(2)); } });
	 * 
	 * 
	 * 
	 * 
	 */
//	for (ParsedData pojo : pojos) 
//	{
//		System.out.println(pojo); 
//	}
//	
	}
}
	
	

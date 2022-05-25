package mx.com.sixdelta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JsonToExcelSinkApplication{
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JsonToExcelSinkApplication.class);
	
	public static void main(String[] args) {
		log.info("Method main");
		SpringApplication.run(JsonToExcelSinkApplication.class, args);

	}
}

package mx.com.sixdelta;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.ServletContextAware;

import mx.com.sixdelta.stream.service.ReportingService;
import mx.com.sixdelta.stream.service.ReportingServiceImpl;

@SpringBootApplication
public class JsonToExcelSinkApplication{
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JsonToExcelSinkApplication.class);
	
	public static void main(String[] args) {
		log.info("Method main");
		
		//SpringApplication.run(JsonToExcelSinkApplication.class, args);
		
		String data = "C:\\Users\\6Delta\\Documents\\TestStreams\\TestMiercoles1.xlsx";
		
		String data2 = "[{\n"
				+ "	\"obj1\": {\n"
				+ " 	 \"lunes \": \"2km \", \n"
				+ " 	 \"martes \": \"3km \",\n"
				+ " 	 \"miercoles \": \"2km \"\n"
				+ " 		},\n"
				+ "  	\"obj2\": {\n"
				+ "  	\"lunes \": \"2km \",\n"
				+ "  	\"martes \": \"3km \",\n"
				+ "  	\"miercoles \": \"2km \"\n"
				+ " 	}\n"
				+ " }\n"
				+ "]";
		
		String path = "C:/Users/6Delta/Documents/OutputStreams";

		ReportingService transformer = new ReportingServiceImpl();
		//log.info(transformer.transformJSONtoExcel(data2, "Reporte1")); 
		log.info(transformer.transformExcelToJSON(data));

	}
}

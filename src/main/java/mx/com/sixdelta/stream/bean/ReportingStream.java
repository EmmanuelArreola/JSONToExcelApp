package mx.com.sixdelta.stream.bean;

import java.io.File;
import java.util.function.Function;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import mx.com.sixdelta.stream.service.ReportingService;
import mx.com.sixdelta.stream.service.ReportingServiceImpl;

@EnableConfigurationProperties(ReportingProperties.class)
@Configuration
public class ReportingStream{
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReportingStream.class);
	private ReportingProperties reportingProperties;

	private static final String ExcelToJsonValue = "ExcelToJson";
	private static final String JsonToExcelValue = "JsonToExcel";
	

	public ReportingStream(ReportingProperties reportingProperties) {
		this.reportingProperties = reportingProperties;
	}

//	@Bean
//	public Function<Message<?>, String> reportingService() throws ExceptionPath {
//		return payload ->  {
//			
//			String Payload = new String((byte[])payload.getPayload());
//			
//			log.info("Payload: " + Payload);
//			
//			ReportingService reporter = new ReportingServiceImpl();
//			log.info("/**************Report Start***************");
//				if (reportingProperties.getTransformTo().equals(ExcelToJsonValue)) {
//					log.info("Excel to Json building");
//					return reporter.transformExcelToJSON(Payload);
//				} else if (reportingProperties.getTransformTo().equals(JsonToExcelValue)) {
//					log.info("Json to excel building");
//					return reporter.transformJSONtoExcel(Payload, reportingProperties.getSheetName());
//				} else {
//					return "Not a correct value";
//				}
//		};
//	}
	
	
	@Bean
	public Function<Message<?>, byte[]> reportingService() throws ExceptionPath {
		return payload ->  {
			byte[] data = new String("data").getBytes(); 
			ReportingService reporter = new ReportingServiceImpl();
			if(reportingProperties.getTransformTo().equals(JsonToExcelValue)){
			return reporter.transformJSONtoExcel(new String((byte[])payload.getPayload()), reportingProperties.getSheetName());
			}else {
				return data;
			}
		};
	}
}

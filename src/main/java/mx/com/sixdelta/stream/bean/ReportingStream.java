package mx.com.sixdelta.stream.bean;

import java.util.function.Consumer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import mx.com.sixdelta.stream.service.ReportingService;
import mx.com.sixdelta.stream.service.ReportingServiceImpl;
import reactor.core.publisher.Flux;

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

	@Bean
	public Consumer<Flux<String>> reportingService() throws ExceptionPath {
		return xlsCreatorCnf -> xlsCreatorCnf.subscribe(data -> {
			log.info(data);
			
			ReportingService reporter = new ReportingServiceImpl();
			log.info("/**************Report Start***************");
				if (reportingProperties.getTransformTo().equals(ExcelToJsonValue)) {
					log.info("Excel to Json building");
					reporter.transformExcelToJSON(data);
				} else if (reportingProperties.getTransformTo().equals(JsonToExcelValue)) {
					log.info("Json to excel building");
					reporter.transformJSONtoExcel(data, reportingProperties.getSheetName());
				}

		});
	}

	
}

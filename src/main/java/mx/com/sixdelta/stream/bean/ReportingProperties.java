package mx.com.sixdelta.stream.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "reporting.params")
public class ReportingProperties {
	private String sheetName;
	private String transformTo = "ExcelToJson";

}

package mx.com.sixdelta;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.sixdelta.stream.service.ReportingService;
import mx.com.sixdelta.stream.service.ReportingServiceImpl;


@SpringBootTest
class JsonToExcelSinkApplicationTests {
	ReportingService mainMethod = new ReportingServiceImpl();

	String testData = "\n" + "    {\n" + "        \"mailFrom\": \"bahamut_jafet@hotmail.com\",\n"
			+ "        \"mailPerson\": \"Jafet Malváez\",\n" + "        \"mailTo\": \"jafet.malvaez@6delta.com\",\n"
			+ "        \"mailCc\": null,\n" + "        \"mailBcc\": null,\n"
			+ "        \"mailSubject\": \"Prueba Mail\",\n" + "        \"mailContent\": \"Saludos\",\n"
			+ "        \"contentType\": \"text/plain\",\n" + "        \"attachments\": null,\n"
			+ "        \"mailAccount\": {\n" + "            \"host\": \"smtp-mail.outlook.com\",\n"
			+ "            \"port\": \"587\",\n" + "            \"user\": \"bahamut_jafet@hotmail.com\",\n"
			+ "            \"password\": \"prueba\"\n" + "        }\n" + "    },    \n" + "    {\n"
			+ "        \"mailFrom\": \"bahamut_jafet@hotmail.com\",\n" + "        \"mailPerson\": \"Jafet Malváez\",\n"
			+ "        \"mailTo\": \"jafet.malvaez@6delta.com\",\n" + "        \"mailCc\": null,\n"
			+ "        \"mailBcc\": null,\n" + "        \"mailSubject\": \"Prueba Mail\",\n"
			+ "        \"mailContent\": \"Saludos\",\n" + "        \"contentType\": \"text/plain\",\n"
			+ "        \"attachments\": null,\n" + "        \"mailAccount\": {\n"
			+ "            \"host\": \"smtp-mail.outlook.com\",\n" + "            \"port\": \"587\",\n"
			+ "            \"user\": \"bahamut_jafet@hotmail.com\",\n" + "            \"password\": \"prueba\"\n"
			+ "        }\n" + "    },\n" + "    {\n" + "        \"host\": \"smtp-mail.outlook.com\",\n"
			+ "        \"port\": \"587\",\n" + "        \"user\": \"bahamut_jafet@hotmail.com\",\n"
			+ "        \"password\": \"prueba\"\n" + "    }\n" + "";

//	byte[] mainResult = mainMethod.transformJSONtoExcel(testData, "Ejemplo");
	
	String pathData = "C:/Users/6Delta/Documents/InputStreams/Testing.xlsx";
	
//	String mainResultJSON = mainMethod.transformExcelToJSON(pathData);

	@Test
	void testEqualsResults() {
		
		
//		System.out.println( mainResult);
//		
//		File temp;
//		try {
//			temp = File.createTempFile("temporal", ".xlsx");
//			FileOutputStream outputStream = new FileOutputStream(temp);
//					outputStream.write(mainResult);	
//					
//					System.out.println("Temporal bueno: " + temp.getAbsolutePath());
//					outputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		assertEquals(mainResult.toString(), new String("data").getBytes());
		
//		System.out.println("Resultado" + mainResultJSON);
//		assertEquals(mainResultJSON , "[{\"Data\":\"FinalData\"}]");
	}
	
//	@Test
//	void testEquals() {
//
//	}

}

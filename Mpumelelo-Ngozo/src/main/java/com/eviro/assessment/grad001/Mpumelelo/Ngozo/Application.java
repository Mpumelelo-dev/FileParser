package com.eviro.assessment.grad001.Mpumelelo.Ngozo;

import com.eviro.assessment.grad001.Mpumelelo.Ngozo.service.FileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.eviro.assessment.grad001.Mpumelelo.Ngozo.entity.AccountProfile;
@EntityScan(basePackages = "com.eviro.assessment.grad001.Mpumelelo.Ngozo.entity")
@SpringBootApplication
public class Application {

	private final FileParser fileParser;

	public Application(FileParser fileParser) {
		this.fileParser = fileParser;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
//accessing the csv file in the properties to extract the data
		Application mainClass = context.getBean(Application.class);
		mainClass.parseCSVWithIOException("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
	}

	// Parse CSV and handle IOException
	public void parseCSVWithIOException(String csvFileName) {
		try {
			// Reading the CSV file from the resources directory
			ClassPathResource csvResource = new ClassPathResource(csvFileName);
			MultipartFile csvFile = new MockMultipartFile(csvFileName, csvResource.getInputStream());

			// Calling the fileParser to parse the CSV data and save to the database
			fileParser.parseCSV(csvFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





}

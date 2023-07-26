package com.eviro.assessment.grad001.Mpumelelo.Ngozo.service;

import com.eviro.assessment.grad001.Mpumelelo.Ngozo.entity.AccountProfile;
import com.eviro.assessment.grad001.Mpumelelo.Ngozo.repository.AccountProfileRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class FileParserImpl implements FileParser {

    private final AccountProfileRepository accountProfileRepository;

    public FileParserImpl(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    @Override
    public void parseCSV(MultipartFile csvFile) {
        try {
            // Reading the CSV data from the MultipartFile and parse each line
            String csvContent = new String(csvFile.getBytes(), StandardCharsets.UTF_8);
            String[] lines = csvContent.split("\\r?\\n");
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    String surname = data[1];
                    String imageFormat = data[2];
                    String base64ImageData = data[3];

                    if (StringUtils.hasText(base64ImageData)) {
                        // Convert base64 image data to a physical image file
                        File imageFile = convertCSVDataToImage(name, surname, imageFormat, base64ImageData);

                        // Saving the image file path and name/surname data to the H2 database
                        AccountProfile accountProfile = new AccountProfile();
                        accountProfile.setAccountHolderName(name);
                        accountProfile.setAccountHolderSurname(surname);
                        accountProfile.setHttpImageLink(imageFile.getAbsolutePath());

                        accountProfileRepository.save(accountProfile);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }


    @Override
    public File convertCSVDataToImage(String name, String surname, String imageFormat, String base64ImageData) {
        try {
            // Decode the Base64 image data
            byte[] decodedImageData = Base64.decodeBase64(base64ImageData);
            // image path "images"  project root
            String imagesDirectory = "images/";
            String imageName = name + "_" + surname + "." + imageFormat;
            String imagePath = imagesDirectory + imageName;
            // Creating the image file
            File imageFile = new File(imagePath);
            // Writing the decoded image data to the file
            Files.write(Paths.get(imagePath), decodedImageData);
            return imageFile;
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            // Handling the exception appropriately, such as logging the error or displaying an error message.
            return null;
        }
    }

    @Override
    //creating an image link.
        public URI createImageLink(File fileImage) {
            if (fileImage == null) {
                return null;
            }
            String imagePath = fileImage.getName();
        // Building the URI using ServletUriComponentsBuilder and returning a file URI
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/") // Assuming the images are served from "/images/" endpoint
                .path(imagePath)
                .build()
                .toUri();
    }
    @Override
    public byte[] getImageFromDatabase(String name, String surname, String fileName) {
        return new byte[0];
    }
}

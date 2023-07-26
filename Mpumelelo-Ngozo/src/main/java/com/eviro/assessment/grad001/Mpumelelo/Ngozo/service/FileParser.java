package com.eviro.assessment.grad001.Mpumelelo.Ngozo.service;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.net.URI;

public interface FileParser {

    void parseCSV(MultipartFile csvFile);

    File convertCSVDataToImage(String name, String surname, String imageFormat, String base64ImageData);

    URI createImageLink(File fileImage);


    byte[] getImageFromDatabase(String name, String surname, String fileName);
}

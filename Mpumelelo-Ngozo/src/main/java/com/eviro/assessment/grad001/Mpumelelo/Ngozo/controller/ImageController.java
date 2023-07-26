package com.eviro.assessment.grad001.Mpumelelo.Ngozo.controller;
import com.eviro.assessment.grad001.Mpumelelo.Ngozo.service.FileParser;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
@RestController
@RequestMapping("v1/api/image")
public class ImageController {

    private final FileParser fileParser;

    public ImageController(FileParser fileParser) {
        this.fileParser = fileParser;
    }
//preparing URL with path variables
@GetMapping(value = "/{name}/{surname}/{fileName:.+}")
public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname,
                                           @PathVariable String fileName) {
    // Retrieve the image file from the database using FileParser
    byte[] imageBytes = fileParser.getImageFromDatabase(name, surname, fileName);

    // Checking for file existence or any errors during retrieval
    if (imageBytes == null) {
        // Handling the error response (Image not found or retrieval error) I'll return a 404 (Not Found) response here.
        return null;
    }

    //Return the image file as a FileSystemResource
    return new FileSystemResource(Arrays.toString(imageBytes));
}
}

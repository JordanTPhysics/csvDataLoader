package esg.csvloads.demo.controllers;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class HtmlController {

    @GetMapping(value = "/my-page", produces = MediaType.TEXT_HTML_VALUE)
    public Resource getStaticHtml() {
        // Load the HTML file from the classpath
        return new ClassPathResource("/templates/index.html");
    }



    @PostMapping("/csv-upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Check if the uploaded file is not empty
        if (!file.isEmpty()) {
            try {
                // Define the directory where you want to store uploaded files
                Path uploadDir = Path.of("uploads"); // Change to your desired directory
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Generate a unique file name (you can customize this logic)
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path filePath = uploadDir.resolve(fileName);

                // Copy the uploaded file to the desired directory
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Handle the uploaded file as needed
                // ...

                return "upload-success";
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error
                // ...
            }
        } else {
            // Handle the case where no file was uploaded
            // ...
        }
        // Return an error view in case of failure
        return "upload-error";
    }
}


package com.example.messagegenerator.runner;

import com.example.messagegenerator.model.ErrorObject;
import com.example.messagegenerator.model.ErrorType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tools {
    public String generateString(int number) {
        StringBuilder result = new StringBuilder(number);

        for (int i = 0; i < number; i++) {
            result.append("X"); // You can use any character or pattern you prefer
        }

        return result.toString();
    }

    public List<ErrorObject> flattenLocationMap(Map<String, Map<ErrorType, Map<String, String>>> locationMap) {
        List<ErrorObject> errorList = new ArrayList<>();

        for (Map.Entry<String, Map<ErrorType, Map<String, String>>> entry : locationMap.entrySet()) {
            String location = entry.getKey();

            for (Map.Entry<ErrorType, Map<String, String>> errorEntry : entry.getValue().entrySet()) {
                ErrorType errorType = errorEntry.getKey();
                Map<String, String> relevantData = errorEntry.getValue();

                ErrorObject error = new ErrorObject();
                error.setLocation(location);
                error.setErrorType(errorType.name());
                error.setRelevantData(relevantData);

                errorList.add(error);
            }
        }

        return errorList;
    }
    public  InputStream getInputStreamFromXmlFile(String filePath) throws IOException {
        File xmlFile = new File(filePath);

        if (!xmlFile.exists() || !xmlFile.isFile()) {
            throw new IOException("File not found or is not a regular file: " + filePath);
        }

        return new FileInputStream(xmlFile);
    }

    public  void writeStringToXmlFile(String filePath, String content) throws IOException {
        File xmlFile = new File(filePath);

        // Ensure the parent directories exist
        if (!xmlFile.getParentFile().exists()) {
            xmlFile.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile))) {
            writer.write(content);
        }
    }
    public String readFileFromResources(String fileName) throws IOException {
        // Get the path of the file in the resources folder
        Path filePath = Paths.get("src", "main", "resources", fileName);

        // Read the file content using Java Streams
        return Files.lines(filePath)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    public  Path createTempFileWithStringContent(String content) {
        try {
            // Create a temporary file
            Path tempFile = Files.createTempFile("temp", ".txt");

            // Write the content to the temporary file
            Files.write(tempFile, content.getBytes(), StandardOpenOption.WRITE);

            return tempFile;
        } catch (IOException e) {
            // Handle the exception (e.g., log it or throw a custom exception)
            e.printStackTrace();
            return null;
        }
    }
    }




package service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JobService {

    public void saveJobsFromResourceFile() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jobs.json")) {
            if (inputStream == null) {
                throw new RuntimeException("jobs.json not found in resources folder");
            }

            String content = new Scanner(inputStream, StandardCharsets.UTF_8)
                    .useDelimiter("\\A")
                    .next(); 

            System.out.println(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read or parse jobs.json", e);
        }
    }
}

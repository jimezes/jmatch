package me.mezesdimit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mezesdimit.repository.JobRepository;

import me.mezesdimit.entity.Job;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final ObjectMapper objectMapper;

    public JobService(JobRepository jobRepository, ObjectMapper objectMapper) {
        this.jobRepository = jobRepository;
        this.objectMapper = objectMapper;
    }

    public void saveJobsFromResourceFile() {
        if (jobRepository.count() > 0) {
            System.out.println("JobRepository already initialized.");
            return;
        }
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jobs.json")) {
            if (inputStream == null) {
                throw new RuntimeException("jobs.json not found in resources folder");
            }

            JsonNode root = objectMapper.readTree(inputStream);
            JsonNode jobsNode = root.path("jobs");

            List<me.mezesdimit.entity.Job> jobList = new ArrayList<>();
            for (JsonNode jobNode : jobsNode) {
                String title = jobNode.path("title").asText();
                String description = jobNode.path("description").asText();
                List<String> skills = new ArrayList<>();
                for (JsonNode skillNode : jobNode.path("skills")) {
                    skills.add(skillNode.asText());
                }

                Job job = new Job(title, description, skills);
                jobList.add(job);
            }

            jobRepository.saveAll(jobList);
            System.out.println("Jobs successfully imported from JSON file.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read or parse jobs.json", e);
        }
    }
}

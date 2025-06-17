package me.mezesdimit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.JobService;


@SpringBootApplication
@EntityScan("me.mezesdimit.entity")
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
        JobService jobService = new JobService();
        jobService.saveJobsFromResourceFile();
    }
}
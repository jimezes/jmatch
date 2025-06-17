package me.mezesdimit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import me.mezesdimit.service.JobService;


@SpringBootApplication
@EntityScan("me.mezesdimit.entity")
public class AppStarter {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppStarter.class, args);
        JobService jobService = context.getBean(JobService.class);
        jobService.saveJobsFromResourceFile();
    }
}
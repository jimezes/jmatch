package me.mezesdimit.controller;

import org.springframework.web.bind.annotation.*;
import me.mezesdimit.repository.JobRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
public class JobController {
    private final JobRepository jobRepository;


    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostMapping("/api/jobs/by_skill")
    public List<me.mezesdimit.entity.Job> getJobsBySkills(@RequestBody Set<String> selectedSkills) {
        List<me.mezesdimit.entity.Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .filter(job -> job.getSkills().containsAll(selectedSkills))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/skills")
    public Set<String> getAllSkills() {
        List<me.mezesdimit.entity.Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .flatMap(job -> job.getSkills().stream())
                .collect(Collectors.toSet());
    }

}

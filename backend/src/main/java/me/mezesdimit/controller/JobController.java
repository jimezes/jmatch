package me.mezesdimit.controller;

import me.mezesdimit.entity.Job;
import org.springframework.web.bind.annotation.*;
import me.mezesdimit.repository.JobRepository;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
public class JobController {
    private final JobRepository jobRepository;


    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostMapping("/api/jobs/by_skill")
    public List<me.mezesdimit.entity.Job> getJobsBySkills(@RequestBody Map<String, List<String>> input) {
        List<String> skills = input.get("skills");
        List<me.mezesdimit.entity.Job> jobs = jobRepository.findAll();
        Iterator<Job> iterator = jobs.iterator();
        ArrayList<Job> jobsMatching = new ArrayList<>();
        while(iterator.hasNext()){
            Job next = iterator.next();
            List<String> jobSkills = next.getSkills();
            Iterator<String> skillIterator = jobSkills.iterator();
            while(skillIterator.hasNext()){
                String skill = skillIterator.next();
                if(skills.contains(skill)){
                    if(!jobsMatching.contains(next)){
                        jobsMatching.add(next);
                    }

                }
            }
        }
        return jobsMatching;
    }

    @GetMapping("/api/skills")
    public Set<String> getAllSkills() {
        List<me.mezesdimit.entity.Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .flatMap(job -> job.getSkills().stream())
                .collect(Collectors.toSet());
    }

}

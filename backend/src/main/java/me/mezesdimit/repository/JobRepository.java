package me.mezesdimit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<me.mezesdimit.entity.Job,Long> {
}

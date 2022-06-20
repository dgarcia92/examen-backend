package com.examen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.backend.entity.Job;

@Repository
public interface IJob extends JpaRepository<Job, Long>{

}

package com.examen.backend.service;

import java.util.Optional;

import com.examen.backend.entity.Job;

public interface IJobService {
	
	Optional<Job> findById(Long id);

}

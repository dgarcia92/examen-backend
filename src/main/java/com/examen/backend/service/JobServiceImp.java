package com.examen.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.backend.entity.Job;
import com.examen.backend.repository.IJob;

@Service
public class JobServiceImp implements IJobService {

	@Autowired
	private IJob repository;
	
	@Override
	public Optional<Job> findById(Long id) {
		return repository.findById(id);
	}

}

package com.examen.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.backend.entity.Gender;
import com.examen.backend.repository.IGender;

@Service
public class GenderServiceImp implements IGenderService {


	@Autowired
	private IGender repository;
	
	@Override
	public Optional<Gender> findById(Long id) {
		return repository.findById(id);
	}

}

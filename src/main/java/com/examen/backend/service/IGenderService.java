package com.examen.backend.service;

import java.util.Optional;

import com.examen.backend.entity.Gender;

public interface IGenderService {

	Optional<Gender> findById(Long id);
}

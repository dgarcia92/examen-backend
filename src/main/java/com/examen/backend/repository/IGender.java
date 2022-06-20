package com.examen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.backend.entity.Gender;

@Repository
public interface IGender extends JpaRepository<Gender, Long> {

}

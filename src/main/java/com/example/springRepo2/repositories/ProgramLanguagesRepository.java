package com.example.springRepo2.repositories;

import com.example.springRepo2.features.entities.ProgramLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramLanguagesRepository extends JpaRepository<ProgramLanguages, Long> {
}

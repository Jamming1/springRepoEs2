package com.example.springRepo2.repositories;

import com.example.springRepo2.features.entities.ProgramLanguages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/repo-prog-languages",
        collectionResourceDescription = @Description("this is the repo-prog-languages description"),
        collectionResourceRel = "program language",
        itemResourceDescription = @Description("single program language description"))
public interface ProgramLanguagesRepository extends JpaRepository<ProgramLanguages, Long> {
}

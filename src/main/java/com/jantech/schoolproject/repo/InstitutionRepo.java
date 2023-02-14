package com.jantech.schoolproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jantech.schoolproject.models.Institution;

@Repository
public interface InstitutionRepo extends JpaRepository<Institution, Long> {

    Institution findByName(String name);
    Boolean existsByName(String name);

}

package com.upiiz.relaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upiiz.relaciones.models.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

}

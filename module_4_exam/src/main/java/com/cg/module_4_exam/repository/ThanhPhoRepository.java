package com.cg.module_4_exam.repository;

import com.cg.module_4_exam.model.ThanhPho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhPhoRepository extends JpaRepository<ThanhPho, Long> {
}

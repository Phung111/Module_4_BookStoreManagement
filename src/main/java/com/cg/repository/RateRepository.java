package com.cg.repository;

import com.cg.model.*;

import com.cg.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findByName(String name);

    @Query("SELECT NEW com.cg.model.dto.RateDTO (" +
            "rate.id, " +
            "rate.name " +
            ") " +
            "FROM Rate AS rate "
    )
    List<RateDTO> findAllRateDTOS();
}

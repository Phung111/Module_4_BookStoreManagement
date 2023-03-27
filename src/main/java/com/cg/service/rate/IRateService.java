package com.cg.service.rate;

import com.cg.model.Rate;
import com.cg.model.dto.RateDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IRateService extends IGeneralService<Rate> {
    Rate findByName(String name);
    Optional<Rate> findById(Long id);

    List<RateDTO> findAllRateDTOS();
}

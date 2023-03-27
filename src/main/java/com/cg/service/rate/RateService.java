package com.cg.service.rate;

import com.cg.model.Rate;
import com.cg.model.dto.RateDTO;
import com.cg.repository.RateRepository;
import com.cg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService implements IRateService{

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate findByName(String name) {
        return rateRepository.findByName(name);
    }

    @Override
    public Optional<Rate> findById(Long id) {
        return null;
    }

    @Override
    public List<Rate> findAll() {
        return null;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Rate save(Rate rate) {
        return null;
    }

    @Override
    public void delete(Rate rate) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<RateDTO> findAllRateDTOS() {
        return rateRepository.findAllRateDTOS();
    }
}

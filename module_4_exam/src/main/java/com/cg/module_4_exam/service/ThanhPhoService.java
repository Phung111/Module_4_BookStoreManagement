package com.cg.module_4_exam.service;

import com.cg.module_4_exam.model.ThanhPho;
import com.cg.module_4_exam.repository.ThanhPhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ThanhPhoService implements IThanhPhoService{

    @Autowired
    private ThanhPhoRepository thanhPhoRepository;

    @Override
    public List<ThanhPho> findAll() {
        return thanhPhoRepository.findAll();
    }

    @Override
    public Optional<ThanhPho> findById(Long id) {
        return thanhPhoRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return thanhPhoRepository.existsById(id);
    }

    @Override
    public ThanhPho save(ThanhPho thanhPho) {
        return thanhPhoRepository.save(thanhPho);
    }

    @Override
    public void delete(ThanhPho thanhPho) {
        thanhPhoRepository.delete(thanhPho);
    }

    @Override
    public void deleteById(Long id) {
        thanhPhoRepository.deleteById(id);
    }
}

package org.example.carservice.service;

import org.example.carservice.model.SparePart;
import org.example.carservice.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {
    private final SparePartRepository sparePartRepository;

    @Autowired
    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    public List<SparePart> findAllParts() {
        return sparePartRepository.findAll();
    }

    public Optional<SparePart> findPartById(Long id) {
        return sparePartRepository.findById(id);
    }

    public List<SparePart> findPartsByName(String partName) {
        return sparePartRepository.findByPartName(partName);
    }

    public void savePart(SparePart sparePart) {
        sparePartRepository.save(sparePart);
    }

    public void deletePartById(Long id) {
        sparePartRepository.deleteById(id);
    }
}

package org.example.carservice.service;

import org.example.carservice.model.SparePart;
import org.example.carservice.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return sparePartRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<SparePart> findPartById(Long id) {
        return sparePartRepository.findById(id);
    }

    public List<SparePart> findPartsByName(String partName) {
        return sparePartRepository.findByPartName(partName);
    }

    public Page<SparePart> findAllPartsPaginated(Pageable pageable) {
        return sparePartRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id")));
    }

    public void savePart(SparePart sparePart) {
        sparePartRepository.save(sparePart);
    }

    public void deletePartById(Long id) {
        sparePartRepository.deleteById(id);
    }
}

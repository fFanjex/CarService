package org.example.carservice.service;

import org.example.carservice.model.Owner;
import org.example.carservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner findByPhone(String phone) {
        return ownerRepository.findByPhone(phone);
    }

}

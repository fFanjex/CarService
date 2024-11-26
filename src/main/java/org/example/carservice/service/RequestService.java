package org.example.carservice.service;

import org.example.carservice.model.Request;
import org.example.carservice.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public List<Request> getAllRequest() {
        return requestRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

//    public Page<Request> getAllRequestPaginated(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return requestRepository.findAll(pageable);
//    }

    public List<Request> findByVin(String vin) {
        return  requestRepository.findByCar_VinNumber(vin);
    }
}

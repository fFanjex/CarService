package org.example.carservice.service;

import org.example.carservice.model.EmployeeInfo;
import org.example.carservice.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeInfoService {
    private final EmployeeInfoRepository employeeInfoRepository;

    @Autowired
    public EmployeeInfoService(EmployeeInfoRepository employeeInfoRepository) {
        this.employeeInfoRepository = employeeInfoRepository;
    }

    public List<EmployeeInfo> getAllInfo() {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        employeeInfoRepository.findAll().forEach(employeeInfoList::add);
        return employeeInfoList;
    }

    public void deleteInfo(Long id) {
        employeeInfoRepository.deleteById(id);
    }

    public Optional<EmployeeInfo> findEmployeeInfoByEmployeeId(Integer id) {
        return employeeInfoRepository.findEmployeeInfoByEmployeeId(id);
    }

    public EmployeeInfo addEmployeeInfo(EmployeeInfo employeeInfo) {
        return employeeInfoRepository.save(employeeInfo);
    }
}

package org.example.carservice.service;

import org.example.carservice.model.EmployeeInfo;
import org.example.carservice.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public EmployeeInfo getEmployeeInfoById(Long id) {
        return employeeInfoRepository.findById(id).orElse(null);
    }

    public void deleteInfo(Long id) {
        employeeInfoRepository.deleteById(id);
    }
}

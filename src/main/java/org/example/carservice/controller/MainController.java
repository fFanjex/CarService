package org.example.carservice.controller;

import org.example.carservice.model.*;
import org.example.carservice.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final CarService carService;
    private final OwnerService ownerService;
    private final RequestService requestService;
    private final EmployeeService employeeService;
    private final EmployeeInfoService employeeInfoService;

    public MainController(CarService carService, OwnerService ownerService,
                          RequestService requestService, EmployeeService employeeService,
                          EmployeeInfoService employeeInfoService) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.requestService = requestService;
        this.employeeService = employeeService;
        this.employeeInfoService = employeeInfoService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/car/add")
    public String addCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "actionsWithCars/add-car";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car,
                         @RequestParam("ownerPhone") String ownerPhone,
                         @RequestParam("ownerFirstName") String ownerFirstName) {
        Owner existingOwner = ownerService.findByPhone(ownerPhone);

        if (existingOwner != null) {
            car.setOwner(existingOwner);
        } else {
            Owner newOwner = new Owner();
            newOwner.setPhone(ownerPhone);
            newOwner.setFirstName(ownerFirstName);
            ownerService.saveOwner(newOwner);
            car.setOwner(newOwner);
        }
        carService.saveCar(car);
        return "redirect:/";
    }


    @GetMapping("/owner/add")
    public String addOwnerPage(Model model) {
        model.addAttribute("owner", new Owner());
        return "actionsWithOwner/add-owner";
    }

    @PostMapping("/owner/add")
    public String addOwner(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("phone") String phone) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setPhone(phone);
        ownerService.saveOwner(owner);
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String viewAllOrders(Model model) {
        List<Request> requests = requestService.getAllRequest();
        model.addAttribute("requests", requests);
        return "actionsWithRequest/all-request";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Integer id, Model model) {
        Optional<Request> requestOptional = requestService.getRequestById(Long.valueOf(id));
        requestOptional.ifPresent(request -> {
            model.addAttribute("request", request);
        });

        return requestOptional.isPresent() ? "actionsWithRequest/edit-request" : "error";
    }

    @PostMapping("/orders/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Request requestDetails) {
        Optional<Request> requestOptional = requestService.getRequestById(id);
        if (requestOptional.isPresent()) {
            Request existingRequest = requestOptional.get();
            existingRequest.setDescription(requestDetails.getDescription());
            existingRequest.setRepair(requestDetails.getRepair());
            existingRequest.setResolutionDate(requestDetails.getResolutionDate());
            if (requestDetails.getRequestDate() != null) {
                existingRequest.setRequestDate(requestDetails.getRequestDate());
            } else {
                existingRequest.setRequestDate(LocalDate.now());
            }
            requestService.saveRequest(existingRequest);
            return "redirect:/orders";
        } else {
            return "error";
        }
    }


    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/orders";
    }

    @GetMapping("/employee")
    public String employee(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee";
    }

    @PostMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }

    @GetMapping("/employee/details/{id}")
    public String viewEmployeeDetails(@PathVariable Integer id, Model model) {
        Optional<EmployeeInfo> employeeInfoOptional = employeeInfoService.findEmployeeInfoByEmployeeId(id);
        if (employeeInfoOptional.isPresent()) {
            EmployeeInfo employeeInfo = employeeInfoOptional.get();
            model.addAttribute("employeeInfo", employeeInfo);
            return "employee-details";
        }
        return "error";
    }

}
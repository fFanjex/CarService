package org.example.carservice.controller;

import org.example.carservice.model.Car;
import org.example.carservice.model.Owner;
import org.example.carservice.model.Request;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OwnerService;
import org.example.carservice.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final CarService carService;
    private final OwnerService ownerService;
    private final RequestService requestService;

    public MainController(CarService carService, OwnerService ownerService,
                          RequestService requestService) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.requestService = requestService;
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
    public String editOrder(@PathVariable Long id, Model model) {
        Optional<Request> requestOptional = requestService.getRequestById(id);
        requestOptional.ifPresent(request -> {
            model.addAttribute("request", request);
        });

        return requestOptional.isPresent() ? "actionsWithRequest/edit-request" : "error";
    }

    @PostMapping("/orders/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Request request) {
        Optional<Request> existingOptionalRequest = requestService.getRequestById(id);

        existingOptionalRequest.ifPresent(existingRequest -> {
            existingRequest.setCar(request.getCar());
            existingRequest.setOwner(request.getOwner());
            existingRequest.setRequestDate(request.getRequestDate());
            existingRequest.setDescription(request.getDescription());
            existingRequest.setRepair(request.getRepair());
            existingRequest.setResolutionDate(request.getResolutionDate());

            requestService.saveRequest(existingRequest);
        });

        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/orders";
    }
}

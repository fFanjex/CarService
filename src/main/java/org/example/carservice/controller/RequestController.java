package org.example.carservice.controller;

import org.example.carservice.model.Car;
import org.example.carservice.model.Owner;
import org.example.carservice.model.Request;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OwnerService;
import org.example.carservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RequestController {
    private final RequestService requestService;
    private final CarService carService;
    private final OwnerService ownerService;

    @Autowired
    public RequestController(RequestService requestService, CarService carService,
                             OwnerService ownerService) {
        this.requestService = requestService;
        this.carService = carService;
        this.ownerService = ownerService;
    }

    @GetMapping("/request/add")
    public String showRequestForm(Model model) {
        List<Car> cars = carService.findAllCars();
        List<Owner> owners = ownerService.findAllOwners();
        model.addAttribute("cars", cars);
        model.addAttribute("owners", owners);
        model.addAttribute("request", new Request());
        return "actionsWithRequest/add-request";
    }

    @PostMapping("/request/add")
    public String addRequest(@ModelAttribute("request") Request request, Model model) {
        Car car = carService.findByVinNumber(request.getCar().getVinNumber());
        Owner owner = ownerService.findByPhone(request.getOwner().getPhone());

        if (car == null) {
            model.addAttribute("error", "Машина не найдена");
            return "actionsWithRequest/add-request";
        }

        if (owner == null) {
            model.addAttribute("error", "Владелец не найден");
            return "actionsWithRequest/add-request";
        }

        request.setCar(car);
        request.setOwner(owner);
        request.setRequestDate(LocalDate.now());

        requestService.saveRequest(request);
        model.addAttribute("request", "Ваш заказ успешно добавлен!");
        return "actionsWithRequest/add-request";
    }

    @GetMapping("/requests/search")
    public String searchRequestByVin(@RequestParam("vin") String vin,
                                     Model model) {
        List<Request> requests = requestService.findByVin(vin);
        model.addAttribute("requests", requests);
        return "requests";
    }

    @GetMapping("/requests/all")
    public String allRequest(Model model) {
        List<Request> requests = requestService.getAllRequest();
        model.addAttribute("requests", requests);
        return "actionsWithRequest/all-request";
    }

    @GetMapping("/orders/filter")
    public String filterOrdersByCarName(@RequestParam(value = "carName", required = false) String carName,
                                        Model model) {
        List<Request> requests = carName == null || carName.isEmpty()
                ? requestService.getAllRequest()
                : requestService.findByCarBrand(carName);
        model.addAttribute("requests", requests);
        model.addAttribute("carName", carName); // сохраняем фильтр в представлении
        return "actionsWithRequest/all-request";
    }

}
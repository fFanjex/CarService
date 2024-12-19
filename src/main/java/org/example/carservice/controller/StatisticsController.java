package org.example.carservice.controller;

import org.example.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticsController {
    private final CarService carService;
    @Autowired
    public StatisticsController (CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/statistics")
    public String viewStatistics(Model model) {
        List<Object[]> statistics = carService.getCarStatistics();
        List<Map<String, Object>> statisticsList = new ArrayList<>();
        for (Object[] row : statistics) {
            Map<String, Object> rowMap = Map.of(
                    "carBrand", row[0],
                    "carCount", row[1]
            );
            statisticsList.add(rowMap);
        }
        model.addAttribute("statistics", statisticsList);
        return "statistics";
    }
}

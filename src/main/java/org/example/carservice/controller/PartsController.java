package org.example.carservice.controller;

import org.example.carservice.model.SparePart;
import org.example.carservice.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PartsController {

    private final SparePartService sparePartService;

    @Autowired
    public PartsController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping("/parts")
    public String parts(Model model) {
        List<SparePart> parts = sparePartService.findAllParts();
        model.addAttribute("parts", parts);
        model.addAttribute("newPart", new SparePart());
        return "parts";
    }

    @PostMapping("/parts/add")
    public String addPart(@ModelAttribute("newPart") SparePart newPart) {
        sparePartService.savePart(newPart);
        return "redirect:/parts";
    }


    @GetMapping("/parts/delete/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        sparePartService.deletePartById(id);
        return "redirect:/parts";
    }

    @GetMapping("/parts/edit/{id}")
    public String editPart(@PathVariable("id") Long id, Model model) {
        SparePart sparePart = sparePartService.findPartById(id).orElse(null);
        if (sparePart != null) {
            model.addAttribute("partToEdit", sparePart);
        }
        return "actionsWithParts/edit-part";
    }

    @PostMapping("/parts/edit/{id}")
    public String updatePart(@PathVariable Long id, @ModelAttribute SparePart partDetails) {
        Optional<SparePart> partOptional = sparePartService.findPartById(id);
        if (partOptional.isPresent()) {
            SparePart existingPart = partOptional.get();
            existingPart.setPartName(partDetails.getPartName());
            existingPart.setPartNumber(partDetails.getPartNumber());
            sparePartService.savePart(existingPart);
            return "redirect:/parts";
        } else {
            return "error";
        }
    }

    @GetMapping("/parts/search")
    public String searchParts(@RequestParam("partName") String partName, Model model) {
        List<SparePart> parts = sparePartService.findPartsByName(partName);
        model.addAttribute("parts", parts);
        model.addAttribute("searchQuery", partName);
        return "actionsWithParts/search-results";
    }
}

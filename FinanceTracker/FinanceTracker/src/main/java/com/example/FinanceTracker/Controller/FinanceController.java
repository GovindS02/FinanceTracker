package com.example.FinanceTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.FinanceTracker.Models.TrackerModel;
import com.example.FinanceTracker.Service.FinanceService;

@Controller
public class FinanceController {

    @Autowired
    private FinanceService service;

    // ADD PAGE
    @GetMapping("/add")
    public String addFinancePage(Model model) {
        model.addAttribute("finance", new TrackerModel());
        return "add";
    }

    // ADD PROCESS
    @PostMapping("/add")
    public String addFinanceProcess(@ModelAttribute("finance") TrackerModel financeData,
                                    RedirectAttributes redirect) {

        service.add(financeData);
        redirect.addFlashAttribute("success", "Expense added successfully!");

        return "redirect:/add";
    }

    // LIST ALL DATA
    @GetMapping("/all")
    public String allFinanceDetails(Model model) {
        List<TrackerModel> finances = service.all();
        model.addAttribute("finances", finances);
        return "finance";
    }

    // UPDATE PAGE
    @GetMapping("/update/{id}")
    public String updateFinancePage(@PathVariable Integer id, Model model) {
        model.addAttribute("finance", service.getOrThrow(id));
        return "update";
    }

    // UPDATE PROCESS
    @PostMapping("/update/{id}")
    public String updateFinanceProcess(@PathVariable Integer id,
                                       @ModelAttribute("finance") TrackerModel financeData,
                                       RedirectAttributes redirect) {

        service.update(id, financeData);
        redirect.addFlashAttribute("success", "Record updated successfully!");
        return "redirect:/all";
    }

    // DELETE PAGE
    @GetMapping("/delete/{id}")
    public String deleteFinancePage(@PathVariable Integer id, Model model) {
        model.addAttribute("finance", service.getOrThrow(id));
        return "delete";
    }

    // DELETE PROCESS
    @PostMapping("/delete/{id}")
    public String deleteFinanceProcess(@PathVariable Integer id,
                                       RedirectAttributes redirect) {

        service.delete(id);
        redirect.addFlashAttribute("success", "Record deleted successfully!");
        return "redirect:/all";
    }
}

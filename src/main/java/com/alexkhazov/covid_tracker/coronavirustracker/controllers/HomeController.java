package com.alexkhazov.covid_tracker.coronavirustracker.controllers;

import com.alexkhazov.covid_tracker.coronavirustracker.models.LocationStats;
import com.alexkhazov.covid_tracker.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats =  coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        String totalReportedCases2 = String.format("%.2fM", totalReportedCases/ 1000000.0);
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("total",totalReportedCases2);
        model.addAttribute("newCases",totalNewCases);

        return "home";

    }
}

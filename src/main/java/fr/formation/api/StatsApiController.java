package fr.formation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.service.EditCsvService;

@RestController
@RequestMapping("/api/stats")
public class StatsApiController {
    @GetMapping
    public String getStats() {
        EditCsvService editCsv = new EditCsvService();

        return editCsv.read();
    }
}
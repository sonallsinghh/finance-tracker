package com.sonal.finance.Controller;

import com.sonal.finance.DTO.EntryRequestDTO;
import com.sonal.finance.entity.Entry;
import com.sonal.finance.entity.EntryType;
import com.sonal.finance.Service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/entries")
public class EntryController {

    @Autowired
    private EntryService service;

    @PostMapping
    public Entry createEntry(@RequestBody EntryRequestDTO request) {
        return service.createEntry(request);
    }

    @GetMapping
    public List<Entry> getAllEntries() {
        return service.getAllEntries();
    }

    @GetMapping("/{id}")
    public Entry getEntryById(@PathVariable Long id) {
        return service.getEntryById(id);
    }

    @GetMapping("/type/{type}")
    public List<Entry> filterByType(@PathVariable EntryType type) {
        return service.filterByType(type);
    }

    @GetMapping("/category/{category}")
    public List<Entry> filterByCategory(@PathVariable String category) {
        return service.filterByCategory(category);
    }

    @GetMapping("/date-range")
    public List<Entry> filterByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return service.filterByDateRange(startDate, endDate);
    }

    @PutMapping("/{id}")
    public Entry updateEntry(@PathVariable Long id, @RequestBody EntryRequestDTO request) {
        return service.updateEntry(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        service.deleteEntry(id);
    }

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        return service.getSummary();
    }
}

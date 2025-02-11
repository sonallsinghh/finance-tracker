package com.sonal.finance.Service;

import com.sonal.finance.DTO.EntryRequestDTO;
import com.sonal.finance.entity.Entry;
import com.sonal.finance.Repository.EntryRepository;
import com.sonal.finance.entity.EntryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository repository;

    @Override
    public Entry createEntry(EntryRequestDTO request) {
        Entry entry = new Entry(null, request.getAmount(), request.getType(),
                request.getCategory(), request.getDescription(), request.getDate());
        return repository.save(entry);
    }

    @Override
    public List<Entry> getAllEntries() {
        return repository.findAll();
    }

    @Override
    public List<Entry> filterByType(EntryType type) {
        return repository.findByType(type);
    }

    @Override
    public List<Entry> filterByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Entry> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Entry updateEntry(Long id, EntryRequestDTO request) {
        Optional<Entry> existingEntry = repository.findById(id);
        if (existingEntry.isPresent()) {
            Entry entry = existingEntry.get();
            entry.setAmount(request.getAmount());
            entry.setType(request.getType());
            entry.setCategory(request.getCategory());
            entry.setDescription(request.getDescription());
            entry.setDate(request.getDate());
            return repository.save(entry);
        }
        throw new RuntimeException("Entry not found");
    }

    @Override
    public Entry getEntryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found with ID: " + id));
    }


    @Override
    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Double> getSummary() {
        List<Entry> entries = repository.findAll();

        double totalIncome = entries.stream()
                .filter(entry -> entry.getType() == EntryType.INCOME)
                .mapToDouble(Entry::getAmount)
                .sum();

        double totalExpense = entries.stream()
                .filter(entry -> entry.getType() == EntryType.EXPENSE)
                .mapToDouble(Entry::getAmount)
                .sum();

        return Map.of("totalIncome", totalIncome, "totalExpense", totalExpense);
    }
}

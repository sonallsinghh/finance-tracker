package com.sonal.finance.Service;

import com.sonal.finance.DTO.EntryRequestDTO;
import com.sonal.finance.entity.Entry;
import com.sonal.finance.entity.EntryType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EntryService {
    Entry createEntry(EntryRequestDTO request);
    List<Entry> getAllEntries();
    List<Entry> filterByType(EntryType type);
    List<Entry> filterByCategory(String category);
    List<Entry> filterByDateRange(LocalDate startDate, LocalDate endDate);
    Entry updateEntry(Long id, EntryRequestDTO request);
    Entry getEntryById(Long id);
    void deleteEntry(Long id);
    Map<String, Double> getSummary();
}

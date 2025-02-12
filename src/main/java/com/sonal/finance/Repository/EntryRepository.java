package com.sonal.finance.Repository;

import com.sonal.finance.entity.Entry;
import com.sonal.finance.entity.EntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByType(EntryType type);
    List<Entry> findByCategory(String category);
    List<Entry> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

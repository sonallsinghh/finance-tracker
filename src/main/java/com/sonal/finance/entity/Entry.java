package com.sonal.finance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private EntryType type; // INCOME or EXPENSE

    private String category; // Food, Entertainment, Salary, etc.

    private String description;

    private LocalDate date;
}


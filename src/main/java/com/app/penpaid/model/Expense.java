package com.app.penpaid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Expense {
    private String expenseId;

    private LocalDate expenseDate;

    @JsonProperty(required = true)
    private double expenseAmount;

    @JsonProperty(required = true)
    private List<String> expenseTags;

    private String expenseDescription;

    private LocalDateTime expenseEntryMoment;
}

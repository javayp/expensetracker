package com.app.penpaid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

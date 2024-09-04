package com.app.penpaid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Expense extends BasicDBObject {

    private String expenseId;

    private LocalDate expenseDate;

    @JsonProperty(required = true)
    private double expenseAmount;

    @JsonProperty(required = true)
    private List<String> expenseTags;

    private String expenseDescription;

    private LocalDateTime expenseCreationDate;
}

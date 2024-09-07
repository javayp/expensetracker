package com.app.penpaid.query;

import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class ExpenseQuery {

    public static List<Document> findByExpenseId(String expenseId) {
        return Arrays.asList(new Document("$match",
                        new Document("expenseId", expenseId)),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("expenseId", 0L)));
    }
}

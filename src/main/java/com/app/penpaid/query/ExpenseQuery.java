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

    public static List<Document> getLatestExpense() {
        return Arrays.asList(new Document("$sort",
                        new Document("expenseDate", -1L)),
                new Document("$limit", 10),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("expenseId", 0L).append("expenseCreationDate", 0L).append("expenseEntryMoment", 0L)));
    }
}

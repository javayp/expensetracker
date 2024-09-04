package com.app.penpaid.service;

import com.app.penpaid.factory.MongoClientFactory;
import com.app.penpaid.model.Expense;
import com.mongodb.client.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExpenseService {

    private final MongoClientFactory mongoClientFactory;

    @Autowired
    public ExpenseService(MongoClientFactory mongoClientFactory) {
        this.mongoClientFactory = mongoClientFactory;
    }

    public void createExpense(List<Expense> expenseList) {
        if (expenseList.size()>1){
            log.info("Creating multiple expenses of size: {}", expenseList.size());
            mongoClientFactory.getCollection("penpaid", "expense", Expense.class).insertMany(expenseList);
        }else {
            log.info("Creating a single expense");
        }
    }
}

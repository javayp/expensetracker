package com.app.penpaid.service;

import com.app.penpaid.exception.custom.MongoDataInsertException;
import com.app.penpaid.factory.MongoClientFactory;
import com.app.penpaid.model.Expense;
import com.app.penpaid.util.ExpenseUtil;
import com.app.penpaid.util.PropertiesReaderUtil;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExpenseService {

    private final MongoClientFactory mongoClientFactory;
    private final PropertiesReaderUtil propertiesReaderUtil;

    @Autowired
    public ExpenseService(MongoClientFactory mongoClientFactory, PropertiesReaderUtil propertiesReaderUtil) {
        this.mongoClientFactory = mongoClientFactory;
        this.propertiesReaderUtil = propertiesReaderUtil;
    }

    public boolean createExpense(List<Expense> expenseList) {
        try{
            ExpenseUtil.modifyExpense(expenseList);
            if (expenseList.size() > 1) {
                log.info("Creating multiple expenses of size: {}", expenseList.size());
                MongoCollection<Expense> collection = mongoClientFactory.getCollection("penpaid", "expense", Expense.class);
                return collection.insertMany(expenseList).wasAcknowledged();
            } else {
                log.info("Creating a single expense");
                MongoCollection<Expense> collection = mongoClientFactory.getCollection("penpaid", "expense", Expense.class);
                return collection.insertOne(expenseList.get(0)).wasAcknowledged();
            }
        }catch (Exception e){
            log.error("Error while creating expense:", e);
            throw new MongoDataInsertException(propertiesReaderUtil.getValue("D_001"), "D_001");
        }
    }
}

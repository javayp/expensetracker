package com.app.penpaid.service;

import com.app.penpaid.exception.custom.MongoDataInsertException;
import com.app.penpaid.exception.custom.MongoDocumentNotFoundException;
import com.app.penpaid.factory.MongoClientFactory;
import com.app.penpaid.model.Expense;
import com.app.penpaid.query.ExpenseQuery;
import com.app.penpaid.util.ExpenseUtil;
import com.app.penpaid.util.PropertiesReaderUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.penpaid.constants.PenPaidConstants.EXPENSE;
import static com.app.penpaid.constants.PenPaidConstants.PENPAID;

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
        try {
            ExpenseUtil.modifyExpense(expenseList);
            if (expenseList.size() > 1) {
                log.info("Creating multiple expenses of size: {}", expenseList.size());
                MongoCollection<Expense> collection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
                return collection.insertMany(expenseList).wasAcknowledged();
            } else {
                log.info("Creating a single expense");
                MongoCollection<Expense> collection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
                return collection.insertOne(expenseList.get(0)).wasAcknowledged();
            }
        } catch (Exception e) {
            log.error("Error while creating expense:", e);
            throw new MongoDataInsertException(propertiesReaderUtil.getValue("D_001"), "D_001");
        }
    }

    public Expense retriveExpense(String expenseId) {
        try {
            MongoCollection<Expense> collection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
            Expense expense = collection.aggregate(ExpenseQuery.findByExpenseId(expenseId)).first();
            if (expense == null) {
                log.error("Expense not found for id: {}", expenseId);
                throw new MongoDocumentNotFoundException(propertiesReaderUtil.getValue("A_001"), "A_001");
            }else {
                return expense;
            }
        }catch (MongoDocumentNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Error while retrieving expense:", e);
            throw new MongoDataInsertException(propertiesReaderUtil.getValue("D_001"), "D_001");
        }
    }
}

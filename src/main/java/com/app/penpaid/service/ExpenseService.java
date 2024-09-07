package com.app.penpaid.service;

import com.app.penpaid.exception.custom.*;
import com.app.penpaid.factory.MongoClientFactory;
import com.app.penpaid.model.Expense;
import com.app.penpaid.query.ExpenseQuery;
import com.app.penpaid.util.ExpenseUtil;
import com.app.penpaid.util.PropertiesReaderUtil;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                MongoCollection<Expense> expenseCollection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
                return expenseCollection.insertMany(expenseList).wasAcknowledged();
            } else {
                log.info("Creating a single expense");
                MongoCollection<Expense> collection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
                return collection.insertOne(expenseList.get(0)).wasAcknowledged();
            }
        }
        catch (MongoException e) {
            log.error("General Mongo exception:", e);
            throw new MongoGeneralException(propertiesReaderUtil.getValue("AD_000"), "AD_000");
        }
        catch (Exception e) {
            log.error("Error while creating expense:", e);
            throw new ApiInternalServerException(propertiesReaderUtil.getValue("AD_003"), "AD_003");
        }
    }


    public Expense retriveExpense(String expenseId) {
        try {
            MongoCollection<Expense> expenseCollection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
            Expense expense = expenseCollection.aggregate(ExpenseQuery.findByExpenseId(expenseId)).first();
            if (expense == null) {
                log.error("Expense not found for id: {}", expenseId);
                throw new MongoDocumentNotFoundException(propertiesReaderUtil.getValue("AD_001"), "AD_001");
            }else {
                return expense;
            }
        }catch (MongoDocumentNotFoundException e) {
            throw e;
        }catch (Exception e) {
            log.error("Error while retrieving expense:", e);
            throw new ApiInternalServerException(propertiesReaderUtil.getValue("AD_003"), "AD_003");
        }
    }

    public boolean deleteExpense(String expenseId) {
        try {
            MongoCollection<Expense> expenseCollection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
            DeleteResult deleteResult = expenseCollection.deleteOne(new Document("expenseId", expenseId));
            if (deleteResult.getDeletedCount() == 0) {
                log.error("Expense not found for expenseId: {}", expenseId);
                throw new MongoInvalidExpenseIdException(propertiesReaderUtil.getValue("AD_001"), "AD_001");
            }else {
                return deleteResult.wasAcknowledged();
            }
        }catch (MongoInvalidExpenseIdException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Error while deleting expense:", e);
            throw new ApiInternalServerException(propertiesReaderUtil.getValue("AD_003"), "AD_003");
        }
    }

    public List<Expense> getLatestExpense() {
        try {
            MongoCollection<Expense> expenseCollection = mongoClientFactory.getCollection(PENPAID, EXPENSE, Expense.class);
            return expenseCollection.aggregate(ExpenseQuery.getLatestExpense(), Expense.class).into(new ArrayList<>());
        }catch (MongoException e){
            log.error("General Mongo exception:", e);
            throw new MongoGeneralException(propertiesReaderUtil.getValue("AD_000"), "AD_000");
        }
    }
}

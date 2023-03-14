package com.sabanciuniv.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	public List<Transaction> findAll();
	public List<Transaction>  findByToAccountId(String id);
	public List<Transaction>  findByFromAccountId(String id);
}
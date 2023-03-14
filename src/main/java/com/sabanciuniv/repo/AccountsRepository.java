package com.sabanciuniv.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Accounts;

@Repository
public interface AccountsRepository extends MongoRepository<Accounts, String> {
	
	public List<Accounts> findAll();
	public Optional<Accounts> findById(String id);
	public Accounts findByOwner(String owner);
	
}

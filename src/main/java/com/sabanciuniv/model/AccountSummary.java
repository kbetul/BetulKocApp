package com.sabanciuniv.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class AccountSummary {
	
	@Id private String id;
	private String owner;
	private LocalDateTime createDate;
	
	List<MakeTransaction> transactionsOut;
	List<MakeTransaction> transactionsIn;
	double balance;
	
	public AccountSummary() {
		// TODO Auto-generated constructor stub
	}

	public AccountSummary(String id, String owner, LocalDateTime createDate, List<MakeTransaction> transactionsOut,
			List<MakeTransaction> transactionsIn, double balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = createDate;
		this.transactionsOut = transactionsOut;
		this.transactionsIn = transactionsIn;
		this.balance = balance;
	}
	
	public AccountSummary(String id, String owner, LocalDateTime createDate, List<MakeTransaction> transactionsOut,
			List<MakeTransaction> transactionsIn) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = createDate;
		this.transactionsOut = transactionsOut;
		this.transactionsIn = transactionsIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public List<MakeTransaction> getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(List<MakeTransaction> transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

	public List<MakeTransaction> getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(List<MakeTransaction> transactionsIn) {
		this.transactionsIn = transactionsIn;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
	
	
	

}

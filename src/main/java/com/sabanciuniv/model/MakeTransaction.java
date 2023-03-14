package com.sabanciuniv.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class MakeTransaction {
	
	@Id private String id;
	@DBRef private Accounts from;
	@DBRef private Accounts to;
	private LocalDateTime createDate;
	private double amount = 0;
	
	public MakeTransaction() {
		// TODO Auto-generated constructor stub
	}



	public MakeTransaction(String id, Accounts from, Accounts to, LocalDateTime createDate, double amount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.createDate = createDate;
		this.amount = amount;
	}



	public MakeTransaction(Accounts fromAcc, Accounts toAcc, double amount) {
		super();
		this.from = fromAcc;
		this.to = toAcc;
		this.amount = amount;
	}

	public Accounts getFrom() {
		return from;
	}

	public void setFrom(Accounts from) {
		this.from = from;
	}

	public Accounts getTo() {
		return to;
	}

	public void setTo(Accounts to) {
		this.to = to;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	

}

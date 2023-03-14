package com.sabanciuniv.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Accounts {
	
	@Id private String id;
	private String owner;
	private LocalDateTime createDate;
	
	public Accounts() {
		// TODO Auto-generated constructor stub
	}

	public Accounts(String id, String owner, LocalDateTime localDateTime) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = localDateTime;
	}
	
	public Accounts(String id, String owner) {
		super();
		this.id = id;
		this.owner = owner;
	}
	
	public Accounts(String id) {
		super();
		this.id = id;
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
	
	

}

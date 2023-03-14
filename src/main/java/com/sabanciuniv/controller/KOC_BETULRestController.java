package com.sabanciuniv.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.AccountSummary;
import com.sabanciuniv.model.Accounts;
import com.sabanciuniv.model.MakeTransaction;
import com.sabanciuniv.model.Response;
import com.sabanciuniv.model.Transaction;
import com.sabanciuniv.repo.AccountsRepository;
import com.sabanciuniv.repo.TransactionRepository;
import jakarta.annotation.PostConstruct;

@RestController
public class KOC_BETULRestController {
	
	@Autowired private AccountsRepository accountsRepository;
	@Autowired private TransactionRepository transactionRepository;


	@PostConstruct
	public void init() {
		if(accountsRepository.count() == 0) {
			Accounts acc1 = new Accounts("1111", "Jack Johns", LocalDateTime.now());
			Accounts acc2 = new Accounts("2222", "Henry Williams", LocalDateTime.now());
		
			accountsRepository.save(acc1);
			accountsRepository.save(acc2);
			
			Transaction transaction1 = new Transaction(acc1.getId(), acc2.getId(), LocalDateTime.now(), 1500);
			Transaction transaction2 = new Transaction(acc2.getId(), acc1.getId(), LocalDateTime.now(), 2500);
			
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			
			accountsRepository.findAll();
			transactionRepository.findAll();
		}
	}
	
	@PostMapping("/account/save")
	public Response<Accounts> saveAccounts(@RequestBody Accounts account) {
		Response<Accounts> response;
		if (account.getId()!= null && account.getOwner()!=null) {
			account.setCreateDate(LocalDateTime.now());
			Accounts accountSaved = accountsRepository.save(account);
			response = new Response<Accounts>("SUCCESS", accountSaved);
		} else {
			response = new Response<Accounts>("ERROR:account doesnt exist!", null);
		}
		return response;
	}
	
	@PostMapping("/transaction/save")
	public Response<MakeTransaction> saveTransaction (@RequestBody Transaction transaction) {
		Response<MakeTransaction> response;
		
		if(transaction.getFromAccountId() == null || transaction.getFromAccountId() == null || transaction.getAmount() == 0) {
			response = new Response<MakeTransaction>("ERROR:missing fields", null);
		}  else {
			Optional<Accounts> fromAcc = accountsRepository.findById(transaction.getFromAccountId());
			Optional<Accounts> toAcc = accountsRepository.findById(transaction.getToAccountId());
			if(!fromAcc.isPresent() || !toAcc.isPresent()) {
				response = new Response<MakeTransaction>("ERROR: account id", null);
			} else {
				Accounts from = fromAcc.get();
				Accounts to = toAcc.get();
				Transaction makeTransaction = new Transaction(from.getId(), to.getId(), LocalDateTime.now(), transaction.getAmount());
				Transaction madeTransaction = transactionRepository.save(makeTransaction);
				MakeTransaction print = new MakeTransaction(makeTransaction.getId(), from, to, makeTransaction.getCreateDate() ,transaction.getAmount());
				response = new Response<MakeTransaction>("SUCCESS", print);
			}
		}
		
		return response;
	}
	
	@GetMapping("/account/{accountId}")
	public Response<AccountSummary> accounts(@PathVariable String accountId){
		Response<AccountSummary> response;
		Optional<Accounts> acc = accountsRepository.findById(accountId);
		Transaction temp;
		double total = 0;
		List<MakeTransaction> fromPrint = new ArrayList<MakeTransaction>();
		List<MakeTransaction> toPrint =  new ArrayList<MakeTransaction>();
		
		if(acc.isPresent()) {
			List<Transaction>  fromT = transactionRepository.findByFromAccountId(accountId);
			for (int index = 0; index < fromT.size(); index++) {
				temp = fromT.get(index);
				total -= temp.getAmount();
				Optional<Accounts> fromAcc = accountsRepository.findById(temp.getFromAccountId());
				Optional<Accounts> toAcc = accountsRepository.findById(temp.getToAccountId());
				Accounts from = fromAcc.get();
				Accounts to = toAcc.get();
				MakeTransaction add = new MakeTransaction(temp.getId(), from, to, temp.getCreateDate(), temp.getAmount());
				fromPrint.add(index, add);
			}
			
			
			List<Transaction>  toT = transactionRepository.findByToAccountId(accountId);
			for (int index = 0; index < toT.size(); index++) {
				temp = toT.get(index);
				total += temp.getAmount();
				Optional<Accounts> fromAcc = accountsRepository.findById(temp.getFromAccountId());
				Optional<Accounts> toAcc = accountsRepository.findById(temp.getToAccountId());
				Accounts from = fromAcc.get();
				Accounts to = toAcc.get();
				MakeTransaction add = new MakeTransaction(temp.getId(), from, to, temp.getCreateDate(), temp.getAmount());
				toPrint.add(index, add);
			}
			Accounts myAcc = acc.get();
			AccountSummary account = new AccountSummary(myAcc.getId(), myAcc.getOwner(), myAcc.getCreateDate(),fromPrint, toPrint, total);
			response = new Response<AccountSummary>("SUCCESS", account);
		} else {
			response = new Response<AccountSummary>("ERROR:account doesnt exist!", null);
		}
		return response;
	}
	
	@GetMapping("/transaction/to/{accountId}")
	public Response<List<MakeTransaction>> toTransactions(@PathVariable String accountId){
		Response<List<MakeTransaction>> response;
		List<Transaction> trans = transactionRepository.findByToAccountId(accountId);
		List<Transaction> transactionList = transactionRepository.findAll();
		List<MakeTransaction> toPrint =  new ArrayList<MakeTransaction>();
		Transaction temp;
		if(!trans.isEmpty()) {
			for (int index = 0; index < trans.size(); index++) {
				temp = trans.get(index);
				Optional<Accounts> fromAcc = accountsRepository.findById(temp.getFromAccountId());
				Optional<Accounts> toAcc = accountsRepository.findById(temp.getToAccountId());
				Accounts from = fromAcc.get();
				Accounts to = toAcc.get();
				MakeTransaction add = new MakeTransaction(temp.getId(), from, to, temp.getCreateDate(), temp.getAmount());
				toPrint.add(index, add);
			}
			response = new Response<List<MakeTransaction>>("SUCCESS", toPrint);
		} else {
			response = new Response<List<MakeTransaction>>("ERROR:account doesnt exist!", null);
		}
		return response;
	}
	
	@GetMapping("/transaction/from/{accountId}")
	public Response<List<MakeTransaction>> fromTransactions(@PathVariable String accountId){
		Response<List<MakeTransaction>> response;
		List<Transaction> trans = transactionRepository.findByFromAccountId(accountId);
		List<Transaction> transactionList = transactionRepository.findAll();
		List<MakeTransaction> toPrint =  new ArrayList<MakeTransaction>();
		Transaction temp;
		if(!trans.isEmpty()) {
			for (int index = 0; index < trans.size(); index++) {
				temp = trans.get(index);
				Optional<Accounts> fromAcc = accountsRepository.findById(temp.getFromAccountId());
				Optional<Accounts> toAcc = accountsRepository.findById(temp.getToAccountId());
				Accounts from = fromAcc.get();
				Accounts to = toAcc.get();
				MakeTransaction add = new MakeTransaction(temp.getId(), from, to, temp.getCreateDate(), temp.getAmount());
				toPrint.add(index, add);
			}
			response = new Response<List<MakeTransaction>>("SUCCESS", toPrint);
		} else {
			response = new Response<List<MakeTransaction>>("ERROR:account doesnt exist!", null);
		}
		return response;	
	}
}

package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.Transaction;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEsecureDaoTransaction extends CrudRepository<Transaction, Long>{
public List<Transaction> findAllTransactions(); 
public List<Transaction> findAllTransactionNotValidated(); 
public Transaction findTransactionById(Long id); 
public List<Transaction> findTransactionsBetweenDate(Date startDate, Date endDate); 
public List<Transaction> findTransactionsToday(Date date); 
//public Client findClientByIdTransaction(Long id); 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.metier;

import fr.esecure.banking.DocumentType;
import fr.esecure.banking.Periodicite;
import fr.esecure.banking.modele.client.entities.*;
import java.util.Date;

import java.util.List;

/**
 *
 * @author sniang
 */
public interface IEsecureMetier{
    public Object saveObject(Object objet);
    public Demande saveDemande(Demande objet);
    public Object createNewUserAccount(User user);
    public CompteRendu findCompteRenduById(Long id);
    public Object findEntityById(Long id, Class entityClass);
    public boolean exists(Long id);
    public boolean isAccountWithLoginExist(String login);
    public Compte getAccountWithLoginExist(String login);
    public Compte findAccountByLogin(String login);
    public boolean isUserWithEmailExist(String email);
    public List<Transaction> findAllTransaction();
    public List<Transaction> findAllTransactionNotValidated(); 
    public List<Transaction> findTransactionsBetweenDate(Date startDate, Date endDate);
    public List<Transaction> findTransactionsToday(Date date);
    public Transaction findTransactionById(Long id);
    public User findUserById(Long id);
    public User findUserByEmail(String userMail);
    public User findUserByIdCompte(Long idCompte);
    public void updateUser(User user);
    public void updateCompte(Compte compte);
    public long count();
    public Long delete(Long id, Class clazz);
    public String getUserNameFromEmail(String email);
    public List<User> findAllUsers();
    public Demande findDemandeById(Long idDemande);
    public Long updateDemande(Demande demande );
    public List<Adresse> findAdresseByClientId(Long idClient);
    public List<User> findAllUsersWithoutUserConnected(String login);
    public String generateTransactionReport(DocumentType type, Periodicite periodicite, String reportPath) ;
    public String generateTransactionReport( List<Transaction> listeTransactions,DocumentType type);
    public Banque findCurrentBanque();

    public ParamAcces findParamAcces();

    public void send(User user, String subject, String passwordGenerated);
    public void sendAccountCreated(User user, String subject, String passwordGenerated);
    public void sendAccountUpdated(User user, String subject);
    public void notifyCustomer(Client client);
    public void sendRapport(String filePath);
    public void logJobExecution(JobExecutorHistory jobDetail);
    public ParamAcces getDefaultParamAcces();
     
}

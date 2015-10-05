/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.metierImpl;


import fr.esecure.banking.DocumentType;
import fr.esecure.banking.IEsecureGenarator;
import fr.esecure.banking.Periodicite;
import fr.esecure.banking.mail.sender.IEmailSender;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.metier.utils.DateUtils;
import fr.esecure.banking.metier.utils.PasswordGenerator;
import fr.esecure.banking.modele.client.entities.*;
import fr.esecure.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sniang
 */
@Service
public class IEsecureMetierImpl implements IEsecureMetier{
    @Autowired
    IEsecureDaoCompteRendu compteRenduDao;
    @Autowired
    IEsecureDaoAccuseReception accuseReception;
    @Autowired
    IEsecureDaoUsers user;
    @Autowired
    IEsecureDaoRole role;
    @Autowired
    IEsecureDaoCompte compte;
    @Autowired
    IEsecureDaoClient client;
    @Autowired
    IEmailSender sender;
    @Autowired
    IEsecureDaoTransaction transaction;
    @Autowired
    IEsecureDaoParamAcces parametre;
    @Autowired
    IEsecureDaoDemande demande;
    @Autowired
    IEsecureDaoAdresse adresse;
    @Autowired
    IEmailSender emailSender;
    @Autowired
    IEsecureDaoInfoFormulaire infoFormulaire;

    @Autowired
    IEsecureDaoBanque banque;
    
    @Autowired
    IEsecureGenarator report;

    @Autowired
    IEsecureDaoJobExecutor job;

    @Autowired
    private org.springframework.core.env.Environment env;

    Logger LOG = Logger.getLogger(IEsecureMetierImpl.class.getName());

    @Override
    public CompteRendu findCompteRenduById(Long id) {
        return null;
      
    }

 
    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountWithLoginExist(String login) {
        return compte.isAccountWithLoginExist(login); 
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findEntityById(Long id, Class entityClass) {
        Object  response=null;
        if(entityClass==User.class){
             response = user.findOne(id);
          }else
        if(entityClass==Transaction.class){
             response = transaction.findOne(id);

    }else if(entityClass==Demande.class){
        response = demande.findOne(id);
    }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return response;
    }

    @Override
    public Long delete(Long id, Class clazz) {
        if(clazz==User.class){
             user.delete(id);
          }  else {

            throw new UnsupportedOperationException("Not supported yet.");
        }

        return id;
    }
  
      
     @Override
    public Object saveObject(Object objet) {
       if(objet.getClass()==CompteRendu.class){   
             return compteRenduDao.save((CompteRendu)objet);
          }
          if(objet.getClass()==Compte.class){
            return  compte.save((Compte)objet);
          }
          if(objet.getClass()==User.class){
            return  user.save((User)objet);
          }
          
          if(objet.getClass()==Transaction.class){
              return transaction.save((Transaction)objet);
          }
          if(objet.getClass()==ParamAcces.class){
              return parametre.save((ParamAcces)objet);
          }
            if(objet.getClass()==Demande.class){
              return demande.save((Demande)objet);
          }
         if(objet.getClass()==AccuseReception.class){
             return accuseReception.save((AccuseReception)objet);
         }
         if(objet.getClass()==Client.class){
             return client.save((Client)objet);
         }
         if(objet.getClass()==InfoFormulaire.class){
             return infoFormulaire.save((InfoFormulaire)objet);
         }
         if(objet.getClass()==ParamAcces.class){
             return parametre.save((ParamAcces)objet);
         }
         if(objet.getClass()==Banque.class){
             return banque.save((Banque)objet);
         }
                return null;
    }

    @Override
    public Demande saveDemande(Demande objet) {
        Client cl= (Client) saveObject(objet.getTransaction().getCarteBancaire().getClient());
        CarteBancaire carte =  objet.getTransaction().getCarteBancaire();
        carte.setClient(cl);
        saveObject(carte);
        return (Demande) saveObject(objet);
    }

    @Override
   public List<Transaction> findAllTransaction() {
            return transaction.findAllTransactions();     
   }
    
    
    @Override
    public List<Transaction> findAllTransactionNotValidated() {
            return transaction.findAllTransactionNotValidated();     
    }


    @Override
    public Object createNewUserAccount(User pUser) {

        //1 - Instancier un compte et le remplir
        Compte cpt = new Compte();
        cpt.setDateCreation();
        cpt.setEtatCompte(1);
        cpt.setLogin(PasswordGenerator.splitEmailForLogin(pUser.getUserMail()));
        String passwordToSendInEmail =PasswordGenerator.genererDefaultPassword();
        //2 - Generer un password pour le compte
        cpt.setPassword(PasswordGenerator.crypter(passwordToSendInEmail));
        //3 - Enregistrer l'utilisateur après un setCompte()
        pUser.setCompte(cpt);
        Role r = role.findRoleByName(pUser.getRoles().get(0).getNameRole());
        pUser.addRole(r, true);
        User response=user.save(pUser);
        ParamAcces params= getDefaultParamAcces();

        sender.sendAccountCreated(response,"Création Compte Utilisateur",passwordToSendInEmail,params);
        return response;
    }
    @Override
    public List<User> findAllUsers() {
       return user.findAllUsers();
    }

    @Override
    public boolean isUserWithEmailExist(String email) {
        return user.isUserWithEmailExist(email);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getUserNameFromEmail(String email) {
        return PasswordGenerator.splitEmailForLogin(email);
    }

    @Override
    public Compte getAccountWithLoginExist(String login) {
        return compte.findAccountByLogin(login);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Transaction findTransactionById(Long id) {
        return transaction.findTransactionById(id);
    }

    @Override
    public Demande findDemandeById(Long idDemande) {
       return demande.findDemandeById(idDemande);
    }

    @Override
     public User findUserById(Long idUser) {
        return user.findUserById(idUser);
        
    }

    @Override
    public User findUserByEmail(String userMail) {
        return user.findUserByEmail(userMail);
    }

    @Override
    public void updateUser(User editUser) {
        user.updateUser(editUser);
    }

    @Override
    public Compte findAccountByLogin(String login) {
       return  compte.findAccountByLogin(login);
    }

    @Override
    public User findUserByIdCompte(Long idCompte) {
        return user.findUserByIdCompte(idCompte);
    }

    @Override
    public void updateCompte(Compte cpt) {
        compte.updateCompte(cpt);
    }

    @Override
    public Long updateDemande(Demande demande) {
         this.demande.updateDemande(demande);
         if(demande.getEtatDemande()==Demande.STATE.REJECTED){
             //send notif to client
             Client client = demande.getTransaction().getCarteBancaire().getClient();
             ParamAcces params=getDefaultParamAcces();
             if(findCurrentBanque() !=null)
                params=  findCurrentBanque().getParamAcces();
             sender.notifyCustomer(client,params);
             LOG.log(Level.INFO, "Notification sur une tentative de validation d'une transaction pour le client : "+client.toString());
            
         }
        return demande.getIdDemande();
    }

    @Override
    public List<User> findAllUsersWithoutUserConnected(String login) {
       return user.findAllUsersWithoutUserConnected(login);
        
    }

    @Override
    public List<Transaction> findTransactionsBetweenDate(Date startDate, Date endDate) {

       return  transaction.findTransactionsBetweenDate(startDate, endDate);
    }

    @Override
    public List<Transaction> findTransactionsToday(Date date) {
        return transaction.findTransactionsToday(date);
    }

    @Override
    public String generateTransactionReport(DocumentType type, Periodicite periodicite, String reportPath) {
        String path =null;
        ParamAcces defaultParamAcces = getDefaultParamAcces();
        if(reportPath == null || reportPath.length() <0)
            reportPath=defaultParamAcces.getRapportParam().getParamRapportRepertoire();

        Date dateDuJour = new Date();
        List<Transaction> listeTransactions = null;
        switch (periodicite){
            case TOUS_LES_LUNDIS :
                Date dateSemainePassee = DateUtils.getWeekAgo();
                listeTransactions = findTransactionsBetweenDate(dateSemainePassee, dateDuJour);
                break;
//            case MENSUEL :
//              Date dateMonthAgo = DateUtils.getMonthAgo();
//              listeTransactions = findTransactionsBetweenDate(dateMonthAgo, dateDuJour);
//                 break;
//           case QUOTIDIEN :
//               Date dateToDay = DateUtils.getDateToday();
//               listeTransactions = findTransactionsToday(dateToDay);
//                break;
           default:
               Date semainePassee = DateUtils.getWeekAgo();
               listeTransactions = findTransactionsBetweenDate(semainePassee, dateDuJour);
               break;
        }

        if(listeTransactions == null || listeTransactions.size()<1)
            return null;
        try {
            path=  report.generateReportTransaction(listeTransactions,type, reportPath);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Exception pendant la generation du rapport périodique.....", ex);
        }
        return path;
    }

    @Override
    public String generateTransactionReport( List<Transaction> listeTransactions,DocumentType type) {
        String path =null;

        String reportPath=getDefaultParamAcces().getRapportParam().getParamRapportRepertoire();

        try {
            if(listeTransactions == null || listeTransactions.size()<1)
                return null;
            path=  report.generateReportTransaction(listeTransactions,type, reportPath);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Une exception s'est produite pendant la generation du rapport", ex);
        }
        return path;
    }

    public Banque findCurrentBanque() {
        return banque.findCurrentBanque();
    }


    @Override
    public void send(User user, String subject, String passwordGenerated) {
        sender.send(user, subject, passwordGenerated, getDefaultParamAcces());
    }

    @Override
    public void sendAccountCreated(User user, String subject, String passwordGenerated) {
        sender.sendAccountCreated(user, subject, passwordGenerated, getDefaultParamAcces());
    }

    @Override
    public void sendAccountUpdated(User user, String subject) {
        sender.sendAccountUpdated(user, subject, getDefaultParamAcces());
    }

    @Override
    public void notifyCustomer(Client client) {
        sender.notifyCustomer(client, getDefaultParamAcces());
    }

    @Override
    public void sendRapport( String filePath) {
        sender.sendRapport(getDefaultParamAcces(), filePath);
    }
    
     @Override
    public List<Adresse> findAdresseByClientId(Long idClient) {
       return adresse.getAdresseByClientId(idClient);
    }


    @Override
    public ParamAcces findParamAcces() {
       return findCurrentBanque().getParamAcces();
    }

    @Override
    public void logJobExecution(JobExecutorHistory jobDetail) {
        job.save(jobDetail);
    }
    @Override
    public ParamAcces getDefaultParamAcces(){
        ParamAcces params= findParamAcces();
        if(params == null){
            params= new ParamAcces();
            MessagerieParam msg= new MessagerieParam();

            msg.setParamSmtpEmail(env.getProperty("esecure.param.messagerie.email","demo@groupeidyal.com")); // à recuperer depuis fichier properties
            msg.setParamSmtpPassword(env.getProperty("esecure.param.messagerie.password","tests4idyal"));
            msg.setParamSmtpUsername(env.getProperty("esecure.param.messagerie.username","demo@groupeidyal.com"));
            msg.setParamSmtpHost(env.getProperty("esecure.param.messagerie.host","smtp.groupeidyal.com"));
            msg.setParamSmtpPort(Integer.valueOf(env.getProperty("esecure.param.messagerie.port","25")));
            msg.setParamSmtpSsl(true);
            msg.setParamSmtpTransport("TLS");
            RapportParam rapportParam=new RapportParam();
            rapportParam.setParamRapportFormat(env.getProperty("esecure.param.rapport.format","PDF"));
            rapportParam.setParamRapportBeneficiaire(env.getProperty("esecure.param.rapport.beneficiaire", "demo@groupeidyal.com"));

            params.setRapportParam(rapportParam);
            params.setMessagerieParam(msg);

        }else if(! params.isSmtpEnabled() ){
            MessagerieParam msg= new MessagerieParam();
            msg.setParamSmtpEmail(env.getProperty("esecure.param.messagerie.email","demo@groupeidyal.com")); // à recuperer depuis fichier properties
            msg.setParamSmtpPassword(env.getProperty("esecure.param.messagerie.password","tests4idyal"));
            msg.setParamSmtpUsername(env.getProperty("esecure.param.messagerie.username","demo@groupeidyal.com"));
            msg.setParamSmtpHost(env.getProperty("esecure.param.messagerie.host","smtp.groupeidyal.com"));
            msg.setParamSmtpPort(Integer.valueOf(env.getProperty("esecure.param.messagerie.port","25")));
            msg.setParamSmtpSsl(true);
            msg.setParamSmtpTransport("TLS");
            params.setMessagerieParam(msg);
            if(! params.isRapporEnabled()){
                RapportParam rapportParam=new RapportParam();
                rapportParam.setParamRapportFormat(env.getProperty("esecure.param.rapport.format","PDF"));
                rapportParam.setParamRapportBeneficiaire(env.getProperty("esecure.param.rapport.beneficiaire","demo@groupeidyal.com"));
                rapportParam.setParamRapportRepertoire(env.getProperty("esecure.param.rapport.path.location","usr/tmp/esecure/rapports"));
                params.setRapportParam(rapportParam);
            }
        }else if(! params.isRapporEnabled() ){
            RapportParam rapportParam=new RapportParam();
            rapportParam.setParamRapportFormat(env.getProperty("esecure.param.rapport.format","PDF"));
            rapportParam.setParamRapportBeneficiaire(env.getProperty("esecure.param.rapport.beneficiaire","demo@groupeidyal.com"));
            rapportParam.setParamRapportRepertoire(env.getProperty("esecure.param.rapport.path.location","usr/tmp/esecure/rapports"));
            params.setRapportParam(rapportParam);
        }
        return params;
    }
}

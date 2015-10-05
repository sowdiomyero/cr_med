package fr.banking.esecure.controller;

import fr.banking.esecure.dto.BasicResponse;
import fr.banking.esecure.dto.TransactionClientDTO;
import fr.esecure.banking.TransactionRow;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.modele.client.entities.Adresse;
import fr.esecure.banking.modele.client.entities.Client;
import fr.esecure.banking.modele.client.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 12/02/15
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TransactionController {
@Autowired
IEsecureMetier metier;

    Logger LOG = Logger.getLogger(TransactionController.class.getName());

    //@RequestMapping(value="trans/get", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="transaction/get", method = RequestMethod.GET)
    public @ResponseBody TransactionClientDTO getTransactionById(@RequestParam("idTransaction")Long id){


        TransactionClientDTO trans = new TransactionClientDTO();
        Transaction transaction = metier.findTransactionById(id);
        if(transaction==null){
            trans.setMsg("Aucune transaction existe avec cet Id"+id);
            trans.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return trans;
        }
        else{
        Client client = transaction.getCarteBancaire().getClient();
        Adresse adresse = transaction.getAdresse();
           if(client== null || adresse==null )
           {
               trans.setMsg("Le client son adresse ne doivent pas etre null.");
               trans.setResultat(BasicResponse.RETOUR_EXCEPTION);
               return trans;
           }
           else
           {
            trans.setNomClient(transaction.getCarteBancaire().getClient().getNomClient().toUpperCase());
            trans.setPrenomClient(transaction.getCarteBancaire().getClient().getPrenomClient().toUpperCase());
            trans.setNumCarte(transaction.getCarteBancaire().getNumCarte());
            trans.setNumCompte(transaction.getCarteBancaire().getClient().getNumeroCompte());
            trans.setEmail(transaction.getCarteBancaire().getClient().getEmail());
            trans.setAdresseClient(transaction.getAdresse().getNomRue().toUpperCase());
            trans.setNomCommercant(transaction.getNomCommercant().toUpperCase());
            trans.setSiteWebCommercant(transaction.getSiteWebCommercant());
            trans.setAdresseCommercant(transaction.getAdresse().getNomResidence());
            trans.setMontantTransaction(""+transaction.getMontantBrute());
            trans.setDateTransaction(""+transaction.getDateTransaction());
            trans.setMsg("Visualisation passée avec succés");
            trans.setResultat(BasicResponse.RETOUR_OK);
           }
        }
        
           List <Transaction> listeTransactions = new ArrayList<Transaction>();
           List <TransactionClientDTO> listeTransactionsClient = new ArrayList<TransactionClientDTO>();
           Transaction transactionClient= new Transaction();
           TransactionClientDTO transactionDTO = new TransactionClientDTO();
           listeTransactions= metier.findAllTransaction();
           if(listeTransactions.isEmpty()){
               return trans;
           }
           else
           {
               listeTransactionsClient.clear();
               Iterator it=listeTransactions.iterator();
            while( it.hasNext()) 
                {
                 transactionClient=(Transaction) it.next();
                 if(transactionClient.getCarteBancaire().getClient().getNomClient().equals(transaction.getCarteBancaire().getClient().getNomClient())){
                     transactionDTO.setNomClient(transactionClient.getCarteBancaire().getClient().getNomClient());
                     transactionDTO.setPrenomClient(transactionClient.getCarteBancaire().getClient().getPrenomClient());
                     transactionDTO.setDateTransaction(transactionClient.getDateTransaction().toString());
                     transactionDTO.setSiteWebCommercant(transactionClient.getSiteWebCommercant());
                     transactionDTO.setMontantTransaction(""+transactionClient.getMontantBrute());
                     transactionDTO.setEtatTransaction(transactionClient.getEtatTransaction().toString());
                     
                     listeTransactionsClient.add(transactionDTO);
                 }
                }
            if(listeTransactionsClient.isEmpty())
                {                   
                    return trans;
                }
            listeTransactions.clear();
            trans.setTransactions(listeTransactionsClient);
           }          
        return trans;
    }

    @RequestMapping(value = "transaction/rapport.csv", method = RequestMethod.GET)
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "raport.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        List<Transaction> listTransactions = metier.findAllTransaction();

        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = TransactionRow.getHeaders();

        csvWriter.writeHeader(header);

        for (Transaction tr : listTransactions) {
            TransactionRow row = new TransactionRow(tr);
            csvWriter.write(row, header);
        }

        csvWriter.close();
    }
}

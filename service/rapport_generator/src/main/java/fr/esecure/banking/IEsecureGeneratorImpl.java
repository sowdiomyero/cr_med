/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking;

import fr.esecure.banking.modele.client.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sniang
 */
@Component
public class IEsecureGeneratorImpl implements IEsecureGenarator {

    @Autowired
    PDFTransactionRapport transactionRapport;
    @Autowired
    CSVTransactionRapport csvRapportGenerator ;

    Logger LOG = Logger.getLogger(IEsecureGeneratorImpl.class.getName());

    @Override
    /**
     * retour le chemin du fichier rapport generé
     */
    public String generateReportTransaction(List<Transaction> listeTransactions, DocumentType docType, String outputFilename) throws Exception {
        try {
            switch (docType){
                case CSV:
                   return  csvRapportGenerator.generateReportTransaction(listeTransactions, outputFilename);
                case PDF:
                  return  transactionRapport.generateReport(listeTransactions, outputFilename);
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Une exception s'est produite pendant la generation du rapport des transactions ....", ex);
            throw ex;
        }
        return null;
    }
}

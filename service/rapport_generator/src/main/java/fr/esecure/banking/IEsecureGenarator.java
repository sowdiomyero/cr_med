/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking;

import fr.esecure.banking.modele.client.entities.Transaction;
import java.util.List;

/**
 *
 * @author sniang
 */
public interface IEsecureGenarator {
    public String generateReportTransaction(List<Transaction> transactions, DocumentType docType, String outputFilename) throws Exception ;
}

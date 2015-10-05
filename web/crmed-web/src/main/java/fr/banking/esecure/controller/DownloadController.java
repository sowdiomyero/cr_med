package fr.banking.esecure.controller;

import fr.esecure.banking.DocumentType;
import fr.esecure.banking.TransactionRow;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.modele.client.entities.Transaction;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 13/03/15
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DownloadController {

    @Autowired
    IEsecureMetier metier;

    Logger LOG = Logger.getLogger(DownloadController.class.getName());

    @RequestMapping(value = "/download/rapport.csv", method = RequestMethod.GET)
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

    @RequestMapping(value = "/download/rapport_failed.csv", method = RequestMethod.GET)
    public void downloadFailedTransactions(HttpServletResponse response) throws IOException {

        String csvFileName = "raport.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        List<Transaction> listTransactions = metier.findAllTransactionNotValidated();

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
    
     @RequestMapping(value = "/download/rapport.pdf", method = RequestMethod.GET)
    public void downloadPDF(HttpServletResponse response){
          String pdfFileName = "rapport.pdf";

        response.setContentType("text/pdf");

        // creates mock data
        String headerKey = "Content-Disposition";

        List<Transaction> listtransactions = metier.findAllTransaction();
         String filePathToDownload =metier.generateTransactionReport(listtransactions,DocumentType.PDF);

         if(filePathToDownload !=null){
             File fileToDownload = new File(filePathToDownload);
             String headerValue = String.format("attachment; filename=\"%s\"",
                     fileToDownload.getName());

             response.setHeader(headerKey, headerValue);

             try {
                 InputStream inputStream = new FileInputStream(fileToDownload);

                 IOUtils.copy(inputStream, response.getOutputStream());
                 response.flushBuffer();
                 inputStream.close();
             } catch (Exception e){
                 LOG.log(Level.SEVERE, "Une exception s'est produite pendant l'envoie du fluw pdf vers le client : "+e.getMessage());
             }
         }else{
             LOG.log(Level.WARNING, "La liste des transaction pour la construction des rapports est vide. Aucun rapport ne peut être renvoyé au client.");
         }



     }
 
    @RequestMapping(value = "/download/rapport_failed.pdf", method = RequestMethod.GET)
    public void downloadFailedPDF(HttpServletResponse response){

        response.setContentType("application/pdf");
        // creates mock data
        String headerKey = "Content-Disposition";

        String filePathToDownload =  metier.generateTransactionReport(metier.findAllTransactionNotValidated(),DocumentType.PDF);

        if(filePathToDownload !=null){
            File fileToDownload = new File(filePathToDownload);
            String headerValue = String.format("attachment; filename=\"%s\"",
                    fileToDownload.getName());

            response.setHeader(headerKey, headerValue);

            try {
                InputStream inputStream = new FileInputStream(fileToDownload);

                IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
                inputStream.close();
            } catch (Exception e){
                LOG.log(Level.SEVERE, "Une exception s'est produite pendant l'envoie du fluw pdf vers le client : "+e.getMessage());
            }
        }else{
            LOG.log(Level.WARNING, "La liste des transaction pour la construction des rapports est vide. Aucun rapport ne peut être renvoyé au client.");
        }



    }
}

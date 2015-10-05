package fr.esecure.banking;

import fr.esecure.banking.modele.client.entities.Transaction;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 12/03/15
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Component
public class CSVTransactionRapport {

    Logger LOG = Logger.getLogger(CSVTransactionRapport.class.getName());

    @Value(value="${esecure.param.rapport.path.location}")
    String defaultPath;

    public CSVTransactionRapport() {
    }

    public String generateReportTransaction(List<Transaction> transactions, String outputFilename) throws Exception {

        final List<TransactionRow> tr = getRows(transactions);
        String filePath=null;
        if(outputFilename == null){
            outputFilename=defaultPath;
        }
        ICsvBeanWriter beanWriter = null;
        try {
            LocalDate localDate = new LocalDate();
            String rapportDate= localDate.getDayOfMonth()+"_"+localDate.getMonthOfYear()+"_"+localDate.getYear();
            filePath= outputFilename+"/rapport_"+rapportDate+".csv";
            FileWriter fileWriter= new FileWriter(filePath);
            beanWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);

            final String[] header = TransactionRow.getHeaders();

            beanWriter.writeHeader(header);

            for( final TransactionRow row : tr ) {
                beanWriter.write(row, header);
            }

        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Une exception s'est produite pendant la generation du rapport CSV : "+ex.getMessage());
        }
        finally {
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }

        return filePath;
    }

    private List<TransactionRow> getRows(List<Transaction> transactions) {
        List<TransactionRow> retour= new ArrayList<TransactionRow>();
        for(Transaction tr : transactions){
            TransactionRow row = new TransactionRow(tr);
            retour.add(row);
        }
        return retour;
    }

   /* private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[] {
                new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new FmtDate("dd/MM/yyyy"), // birthDate
                new NotNull(), // mailingAddress
                new Optional(new FmtBool("Y", "N")), // married
                new Optional(), // numberOfKids
                new NotNull(), // favouriteQuote
                new NotNull(), // email
                new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
        };

        return processors;
    }*/
}

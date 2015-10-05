/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import fr.esecure.banking.modele.client.entities.Transaction;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sniang
 */
@org.springframework.stereotype.Component
public class PDFTransactionRapport {

    private  Collection<TransactionRow> list = null;
    int numCursor=0;
    //int nbTransaction=0;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY", Locale.FRENCH);

    @Value(value="${esecure.param.rapport.path.location}")
    String defaultPath;

    Logger LOG = Logger.getLogger(PDFTransactionRapport.class.getName());

     public PDFTransactionRapport(List<Transaction> listInput, Periodicite periode) {
         remplirListTransactionRow(listInput);
         //nbTransaction=list.size();
         
    }

    public String generateReport(List<Transaction> listInput,String outputFilename) throws Exception {
        remplirListTransactionRow(listInput);
        if(outputFilename == null){
            outputFilename=defaultPath;
        }
        LocalDate localDate = new LocalDate();
        String rapportDate= localDate.getDayOfMonth()+"_"+localDate.getMonthOfYear()+"_"+localDate.getYear();
        outputFilename= outputFilename+"/rapport_"+rapportDate+".pdf";
        try {
            generateAndSaveReport(outputFilename);
            listInput=null;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Une exception s'est produite pendant la generation du rapport PDF : "+ex.getMessage());
            throw ex;
        }
        return outputFilename;
    }

    public PDFTransactionRapport() {
    }
    private void remplirListTransactionRow(List<Transaction> transactions){

        list=new ArrayList<TransactionRow>();
        for(Transaction transaction : transactions){
            TransactionRow row = new TransactionRow();

            row.setDate(format.format(transaction.getDateTransaction()));
            row.setEtat(transaction.getEtatTransaction().name());
            //Double db = transaction.getMontant();
            row.setMontant(transaction.getMontantBrute());
            row.setNom(transaction.getCarteBancaire().getClient().getNomClient());
            row.setPrenom(transaction.getCarteBancaire().getClient().getPrenomClient());
            row.setCarte(transaction.getCarteBancaire().getNumCarte());
            list.add(row);
        }
    }
    private JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException, Exception {
        Style headerStyle = createHeaderStyle();
        Style detailTextStyle = createDetailTextStyle();        
        Style detailNumberStyle = createDetailNumberStyle();        
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle,detailNumberStyle);
        JasperPrint jp =null;
        
        try{
           jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(list));  
           list=null;
        } catch (JRException  ex) {
            throw ex;
        }catch (ColumnBuilderException ex) {
            LOG.log(Level.SEVERE, "Une exception s'est produite pendant la recuperation d'un JasperPrint : PDF Report : "+ex.getMessage());
            throw ex;
        }
        return jp;
    }

    private void generateAndSaveReport(String outputFilename) throws Exception {
            JasperPrint jp = getReport();
                JasperExportManager.exportReportToPdfFile(jp, outputFilename);
            // A definir
                
    }
    
    private Style createHeaderStyle() {        
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM_BOLD);
        sb.setBorder(Border.THIN());
        sb.setBorderBottom(Border.PEN_2_POINT());
        sb.setBorderColor(Color.BLACK);
        sb.setBackgroundColor(Color.ORANGE);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setTransparency(Transparency.OPAQUE);        
        return sb.build();
    }
    
    private Style createDetailTextStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM);
        sb.setBorder(Border.DOTTED());        
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingLeft(5);        
        return sb.build();
    }
    
      private Style createDetailNumberStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM);
        sb.setBorder(Border.DOTTED());        
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingRight(5);        
        return sb.build();
    }
    private AbstractColumn createColumn(String property, Class type,
            String title, int width, Style headerStyle, Style detailStyle)
            throws ColumnBuilderException {
        AbstractColumn columnState = ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName()).setTitle(
                        title).setWidth(Integer.valueOf(width))
                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();
        return columnState;
    }
 
    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle) throws ColumnBuilderException, ClassNotFoundException {
        
        DynamicReportBuilder report=new DynamicReportBuilder();
        
        AbstractColumn columnEtat = createColumn("etat", String.class,"ETAT", 30, headerStyle, detailTextStyle);
        AbstractColumn columnPrenom = createColumn("prenom", String.class,"PRENOM", 20, headerStyle, detailTextStyle);
        AbstractColumn columnNom = createColumn("nom", String.class,"NOM", 20, headerStyle, detailTextStyle);
        AbstractColumn columnNumeroCarte = createColumn("carte", String.class,"NUMERO CARTE", 45, headerStyle, detailTextStyle);
        AbstractColumn columnMontant = createColumn("montant", String.class,"MONTANT", 20, headerStyle, detailTextStyle);
        AbstractColumn columnDate = createColumn("date", String.class,"DATE TRANSACTION", 40, headerStyle, detailTextStyle);
        
        report
              .addColumn(columnPrenom)
              .addColumn(columnNom)
              .addColumn(columnNumeroCarte)
              .addColumn(columnDate)
              .addColumn(columnMontant)
              .addColumn(columnEtat);
                
        StyleBuilder titleStyle=new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
        
        StyleBuilder subTitleStyle=new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        
        
        
        report.setTitle("Liste des "+list.size()+" Transactions des 7 derniers jours");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("Date d'impression : "+format.format(new Date()));
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true); 
        return report.build();
    }
    private String getMonth(int month) {
    return new DateFormatSymbols().getMonths()[month-1];
}
}

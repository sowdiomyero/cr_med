/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking;

/**
 *
 * @author sniang
 */
public enum Periodicite {
    
    TOUS_LES_LUNDIS("Semaine Courante");

//    QUOTIDIEN("Mois de Fevrier");
    
    String desc ;

    private Periodicite(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
    
    
}

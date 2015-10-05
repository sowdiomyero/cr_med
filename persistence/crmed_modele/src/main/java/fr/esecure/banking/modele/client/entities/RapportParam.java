package fr.esecure.banking.modele.client.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 10/03/15
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "rapport_param", schema = "esecure")
@NamedQueries({

        @NamedQuery(name = RapportParam.FIND_PARAM_RAPPORT, query = "SELECT RP FROM RapportParam RP"),
})
public class RapportParam extends AbstractDateEntity implements Serializable{
    
     public static final String FIND_PARAM_RAPPORT = "findParamRapport";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_PARAM")
    private Long idParamRapport;

    @Column(name="PARAM_RAPPORT_FREQUENCE")
    private String paramRapportFrequence;

    @Column(name="PARAM_RAPPORT_REPERTOIRE")
    private String paramRapportRepertoire;

    @Column(name="PARAM_RAPPORT_DONNES")
    private String paramRapportDonnees;

    @Column(name="PARAM_RAPPORT_BENEFICIAIRE")
    private String paramRapportBeneficiaire;

    @Column(name="PARAM_RAPPORT_FORMAT")
    private String paramRapportFormat;

    @OneToOne(mappedBy = "rapportParam")
    private ParamAcces params;



    public RapportParam() {

    }

    public String getParamRapportFrequence() {
        return paramRapportFrequence;
    }

    public void setParamRapportFrequence(String paramRapportFrequence) {
        this.paramRapportFrequence = paramRapportFrequence;
    }

    public String getParamRapportRepertoire() {
        return paramRapportRepertoire;
    }

    public void setParamRapportRepertoire(String paramRapportRepertoire) {
        this.paramRapportRepertoire = paramRapportRepertoire;
    }

    public String getParamRapportDonnees() {
        return paramRapportDonnees;
    }

    public void setParamRapportDonnees(String paramRapportDonnees) {
        this.paramRapportDonnees = paramRapportDonnees;
    }

    public String getParamRapportBeneficiaire() {
        return paramRapportBeneficiaire;
    }

    public void setParamRapportBeneficiaire(String paramRapportBeneficiaire) {
        this.paramRapportBeneficiaire = paramRapportBeneficiaire;
    }

    public String getParamRapportFormat() {
        return paramRapportFormat;
    }

    public void setParamRapportFormat(String paramRapportFormat) {
        this.paramRapportFormat = paramRapportFormat;
    }

    public Long getIdParamRapport() {
        return idParamRapport;
    }

    public ParamAcces getParams() {
        return params;
    }

    public void setParams(ParamAcces params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RapportParam{" +
                "idParamRapport=" + idParamRapport +
                ", paramRapportFrequence='" + paramRapportFrequence + '\'' +
                ", paramRapportRepertoire='" + paramRapportRepertoire + '\'' +
                ", paramRapportDonnees='" + paramRapportDonnees + '\'' +
                ", paramRapportBeneficiaire='" + paramRapportBeneficiaire + '\'' +
                ", paramRapportFormat='" + paramRapportFormat + '\'' +
                ", params=" + params +
                '}';
    }
}

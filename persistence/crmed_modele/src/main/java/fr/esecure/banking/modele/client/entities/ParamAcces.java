package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="parametre", schema="esecure")
/*@NamedQueries({
        @NamedQuery(name = ParamAcces.FIND_BANQUE_BY_GROUPE, query = "SELECT p FROM ParamAcces p where p.paramGroupe =:groupe")
})*/
public class ParamAcces extends AbstractDateEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    public static final String FIND_BANQUE_BY_GROUPE = "findParamByGroupe";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_PARAM")
	private Long idParam;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="ID_RAPPORT")
    private RapportParam rapportParam;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="ID_MESSAGERIE")
    private MessagerieParam messagerieParam;

    @OneToOne(mappedBy = "paramAcces")
    private Banque banque;

	public ParamAcces() {
		super();
	}


	
	public Long getIdParam() {
		return idParam;
	}
	public void setIdParam(Long idParam) {
		this.idParam = idParam;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getFindBanqueByGroupe() {
        return FIND_BANQUE_BY_GROUPE;
    }

    /**
     * @return the banque
     */
    public Banque getBanque() {
        return banque;
    }

    /**
     * @param banque the banque to set
     */
    public void setBanque(Banque banque) {
        this.banque = banque;
    }
   public boolean isSmtpEnabled(){
       if(this.getMessagerieParam() != null && getMessagerieParam().getIdParamSmtp() >0)
           return true;
       return false;
   }

    public boolean isRapporEnabled(){
        if(this.getRapportParam() != null && this.getRapportParam().getIdParamRapport() >0 && this.getRapportParam().getParamRapportBeneficiaire() != null)
            return true;
        return false;
    }

    public RapportParam getRapportParam() {
        return rapportParam;
    }

    public void setRapportParam(RapportParam rapportParam) {
        this.rapportParam = rapportParam;
    }

    public MessagerieParam getMessagerieParam() {
        return messagerieParam;
    }

    public void setMessagerieParam(MessagerieParam messagerieParam) {
        this.messagerieParam = messagerieParam;
    }
}

package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 19/01/15
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="compte_rendu")
public class CompteRendu extends AbstractDateEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_COMPTE_RENDU")
	private Long idCompteRendu;
	@Column(name="DATE_COMPTE_RENDU")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dateCompteRendu;
        @Column(name="MESSAGE")
        private String message;
        @Column(name="TYPE_RETOUR")
        private String typeRetour;
	public CompteRendu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompteRendu(Date dateCompteRendu, String message, String typeRetour) {
		super();
		this.dateCompteRendu = dateCompteRendu;
                this.message=message;
                this.typeRetour=typeRetour;
	}
	
	public Long getIdCompteRendu() {
		return idCompteRendu;
	}
	public void setIdCompteRendu(Long idCompteRendu) {
		this.idCompteRendu = idCompteRendu;
	}
	

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the typeRetour
     */
    public String getTypeRetour() {
        return typeRetour;
    }

    /**
     * @param typeRetour the typeRetour to set
     */
    public void setTypeRetour(String typeRetour) {
        this.typeRetour = typeRetour;
    }

    /**
     * @return the dateCompteRendu
     */
    public Date getDateCompteRendu() {
        return dateCompteRendu;
    }

    /**
     * @param dateCompteRendu the dateCompteRendu to set
     */
    public void setDateCompteRendu(Date dateCompteRendu) {
        this.dateCompteRendu = dateCompteRendu;
    }
	
	
}

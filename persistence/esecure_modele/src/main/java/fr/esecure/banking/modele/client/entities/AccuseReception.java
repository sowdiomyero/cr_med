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
@Table(name="accuse_reception", schema="esecure")
public class AccuseReception extends AbstractDateEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ACCUSE_RECEPTION")
	private Long idAccuseReception;
	@Column(name="DATE_ACCUSE_RECEPTION")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dateAccuseReception;
        @Column(name="MESSAGE")
        private String message;
        @Column(name="TYPE_RETOUR")
        private String typeRetour;

	public AccuseReception() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccuseReception(Date dateAccuseReception, String message, String typeRetour) {
		super();
		this.dateAccuseReception = dateAccuseReception;
                this.message=message;
                this.typeRetour=typeRetour;
	}

	

	public Long getIdAccuseReception() {
		return idAccuseReception;
	}

	public void setIdAccuseReception(Long idAccuseReception) {
		this.idAccuseReception = idAccuseReception;
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
     * @return the dateAccuseReception
     */
    public Date getDateAccuseReception() {
        return dateAccuseReception;
    }

    /**
     * @param dateAccuseReception the dateAccuseReception to set
     */
    public void setDateAccuseReception(Date dateAccuseReception) {
        this.dateAccuseReception = dateAccuseReception;
    }
	
	
}

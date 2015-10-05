package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="banque", schema="esecure")
@NamedQueries({
        @NamedQuery(name = Banque.FIND_BANQUE_USER, query = "SELECT d FROM Banque d")
})
public class Banque extends AbstractDateEntity implements Serializable{

	private static final long serialVersionUID = 1L;
    public static final String FIND_BANQUE_USER = "findBanqueUser";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_BANQUE")
    private Long idBanque;
    @Column(name="USERNAME_WS_BANQUE")
    private String wsUsername;
    @Column(name="PASSWORD_WS_BANQUE")
    private String wsPassword;
    @Column(name="CONTACT_BANQUE")
    private String contactBanque;
    @Column(name="CODE_BANQUE")
    private String codeBanque;
    @Column(name="CIGLE_BANQUE")
    private String cigleBanque;

    @Column(name="VALIDATION_FORM_URL")
    private String validationFormUrl;
    @Column(name="FRONT_END_URL")
    private String frontEndUrl;
    @Column(name="WS_ENDPOINT_URL")
    private String wsEndpointUrl;


	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="ID_PARAM")
	private ParamAcces paramAcces;

	
	public Banque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(Long idBanque) {
		this.idBanque = idBanque;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWsUsername() {
        return wsUsername;
    }

    public void setWsUsername(String wsUsername) {
        this.wsUsername = wsUsername;
    }

    public String getWsPassword() {
        return wsPassword;
    }

    public void setWsPassword(String wsPassword) {
        this.wsPassword = wsPassword;
    }

    public String getContactBanque() {
        return contactBanque;
    }

    public void setContactBanque(String contactBanque) {
        this.contactBanque = contactBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getCigleBanque() {
        return cigleBanque;
    }

    public void setCigleBanque(String cigleBanque) {
        this.cigleBanque = cigleBanque;
    }

    public ParamAcces getParamAcces() {
		return paramAcces;
	}

	public void setParamAcces(ParamAcces paramAcces) {
		this.paramAcces = paramAcces;
	}

    public String getValidationFormUrl() {
        return validationFormUrl;
    }

    public void setValidationFormUrl(String validationFormUrl) {
        this.validationFormUrl = validationFormUrl;
    }

    public String getFrontEndUrl() {
        return frontEndUrl;
    }

    public void setFrontEndUrl(String frontEndUrl) {
        this.frontEndUrl = frontEndUrl;
    }

    public String getWsEndpointUrl() {
        return wsEndpointUrl;
    }

    public void setWsEndpointUrl(String wsEndpointUrl) {
        this.wsEndpointUrl = wsEndpointUrl;
    }
}

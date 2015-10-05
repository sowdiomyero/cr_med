package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="adresse", schema="esecure")
@NamedQueries({

    @NamedQuery(name = Adresse.FIND_ADRESSE_BY_CUSTOMER_ID, query = "SELECT a FROM Adresse a where a.idClient=:idClient")  
})
public class Adresse extends AbstractDateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        public static final String FIND_ADRESSE_BY_CUSTOMER_ID = "findAdresseByClientId";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ADRESSE")
	private Long idAdresse;
        @Column(name="ID_CLIENT", insertable = false, updatable = false )
	private Long idClient;
	@Column(name="NUMERO_RUE")
	private int numeroRue;
	@Column(name="NOM_RUE")
	private String nomRue;
        @Column(name="TYPE_RESIDENCE")
	private String typeResidence;
        @Column(name="NOM_RESIDENCE")
	private String nomResidence;
	@Column(name="NUMERO_ETAGE")
	private int numeroEtage;
	@Column(name="PORTE")
	private int porte;
        @Column(name="CODE_POSTAL")
	private String codePostal;
        @Column(name="VILLE")
	private String ville;
        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="ID_CLIENT")
	private Client client;

	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Adresse(int numeroRue, String ville, String nomRue, int numeroEtage,
			int porte,String codePostal) {
		super();
		this.numeroRue = numeroRue;
		this.ville = ville;
		this.nomRue = nomRue;
		this.numeroEtage = numeroEtage;
		this.porte = porte;
                this.codePostal=codePostal;
	}


	public Long getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(Long idAdresse) {
		this.idAdresse = idAdresse;
	}

	public int getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(int numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	
    /**
     * @return the numeroEtage
     */
    public int getNumeroEtage() {
        return numeroEtage;
    }

    /**
     * @param numeroEtage the numeroEtage to set
     */
    public void setNumeroEtage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
    }


	public int getPorte() {
		return porte;
	}

	public void setPorte(int porte) {
		this.porte = porte;
	}

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the typeResidence
     */
    public String getTypeResidence() {
        return typeResidence;
    }

    /**
     * @param typeResidence the typeResidence to set
     */
    public void setTypeResidence(String typeResidence) {
        this.typeResidence = typeResidence;
    }

    /**
     * @return the nomResidence
     */
    public String getNomResidence() {
        return nomResidence;
    }

    /**
     * @param nomResidence the nomResidence to set
     */
    public void setNomResidence(String nomResidence) {
        this.nomResidence = nomResidence;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Adresse{" + "numeroRue=" + numeroRue + ", nomRue=" + nomRue + ", typeResidence=" + typeResidence + ", nomResidence=" + nomResidence + ", numeroEtage=" + numeroEtage + ", porte=" + porte + ", codePostal=" + codePostal + ", ville=" + ville + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adresse other = (Adresse) obj;

        if ((this.codePostal == null) ? (other.codePostal != null) : !this.codePostal.equals(other.codePostal)) {
            return false;
        }
        if ((this.ville == null) ? (other.ville != null) : !this.ville.equals(other.ville)) {
            return false;
        }
        return true;
    }

	
}

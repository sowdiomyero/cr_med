package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="client", schema="esecure")
public class Client extends AbstractDateEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_CLIENT")
	private Long idClient;
	@Column(name="NOM_CLIENT")
	private String nomClient;
	@Column(name="PRENOM_CLIENT")
	private String prenomClient;

	@Column(name="NUMERO_COMPTE")
	private String numeroCompte;
	@Column(name="EMAIL")
	private String email;
	
//	@JoinTable(name="client_adresse", joinColumns=@JoinColumn(name="ID_CLIENT"),
//                    inverseJoinColumns=@JoinColumn(name="ID_ADRESSE"))
        @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="client")
	private List<Adresse> adresses=new ArrayList<Adresse>();



	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nomClient, String prenomClient, String numeroCompte, String email,List<Adresse> adresses) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.numeroCompte = numeroCompte;
		this.email = email;
		this.adresses = adresses;
		
	}
        
        public Client(String nomClient, String prenomClient,
			CarteBancaire carteBancaire, String numeroCompte, String email) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.numeroCompte = numeroCompte;
		this.email = email;
		this.adresses = new ArrayList<Adresse>();
		
	}
	
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	/*public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}
	public void setCarteBancaire(CarteBancaire carteBancaire) {
		this.carteBancaire = carteBancaire;
	}*/
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	public void addAdresse(Adresse adresse){
            if(adresses != null){
                adresses.add(adresse);
            }else{
                adresses = new ArrayList<Adresse>();
                adresses.add(adresse);
            }
        }

    @Override
    public String toString() {
        return "Client [" +
                "Nom='" + nomClient + '\'' +
                ", Prenom='" + prenomClient + '\'' +
                ", Numero Compte='" + numeroCompte + '\'' +
                ", Email='" + email + '\'' +
                ']';
    }
}

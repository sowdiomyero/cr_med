package fr.esecure.banking.modele.client.entities;

import java.io.Serializable;
import java.util.List;

public class Conseiller extends AbstractDateEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idConseiller;

	private String nomConseiller;

	private String prenomConseiller;

	private List<Client> clients ;

	private Banque banque;

	public Conseiller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Conseiller(String nomConseiller, String prenomConseiller,
			List<Client> clients , Banque banque) {
		super();
		this.nomConseiller = nomConseiller;
		this.prenomConseiller = prenomConseiller;
		this.clients = clients;
		this.banque = banque;
	}

	public Long getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(Long idConseiller) {
		this.idConseiller = idConseiller;
	}

	public String getNomConseiller() {
		return nomConseiller;
	}

	public void setNomConseiller(String nomConseiller) {
		this.nomConseiller = nomConseiller;
	}

	public String getPrenomConseiller() {
		return prenomConseiller;
	}

	public void setPrenomConseiller(String prenomConseiller) {
		this.prenomConseiller = prenomConseiller;
	}


	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

    @Override
    public String toString() {
        return "Conseiller{" + "nomConseiller=" + nomConseiller + ", prenomConseiller=" + prenomConseiller + '}';
    }



	
	

}

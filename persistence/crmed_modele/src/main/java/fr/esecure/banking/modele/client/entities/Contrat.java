package fr.esecure.banking.modele.client.entities;

import java.io.Serializable;
import java.util.List;



public class Contrat extends AbstractDateEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idContrat;

	private String typeContrat;
	
	private Client client;

	private List<Avenant> avenants;

	private List<Clause> clauses;
	
	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contrat(String typeContrat, Client client, List<Avenant> avenants,
			List<Clause> clauses) {
		super();
		this.typeContrat = typeContrat;
		this.client = client;
		this.avenants = avenants;
		this.clauses = clauses;
	}

	public Long getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(Long idContrat) {
		this.idContrat = idContrat;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Avenant> getAvenants() {
		return avenants;
	}

	public void setAvenants(List<Avenant> avenants) {
		this.avenants = avenants;
	}

	public List<Clause> getClauses() {
		return clauses;
	}

	public void setClauses(List<Clause> clauses) {
		this.clauses = clauses;
	}

}

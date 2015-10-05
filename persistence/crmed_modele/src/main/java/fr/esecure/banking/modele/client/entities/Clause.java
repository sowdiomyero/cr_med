package fr.esecure.banking.modele.client.entities;

import java.io.Serializable;
import java.util.List;


public class Clause extends AbstractDateEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idClause;

	private List<Contrat> contrats;
	
	private List<Avenant> avenants;

	public Clause() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clause(List<Contrat> contrats, List<Avenant> avenants) {
		super();
		this.contrats = contrats;
		this.avenants = avenants;
	}

	public Long getIdClause() {
		return idClause;
	}

	public void setIdClause(Long idClause) {
		this.idClause = idClause;
	}

	public List<Contrat> getContrats() {
		return contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public List<Avenant> getAvenants() {
		return avenants;
	}

	public void setAvenants(List<Avenant> avenants) {
		this.avenants = avenants;
	}

}

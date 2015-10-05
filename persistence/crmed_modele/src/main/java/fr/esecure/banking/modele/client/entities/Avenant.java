package fr.esecure.banking.modele.client.entities;

import java.io.Serializable;
import java.util.List;


public class Avenant extends AbstractDateEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idAvenent;

	private Contrat  contrat;

	private List<Clause> clauses;
	public Avenant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Avenant(Contrat contrat, List<Clause> clauses) {
		super();
		this.contrat = contrat;
		this.clauses = clauses;
	}

	
	
	public Long getIdAvenent() {
		return idAvenent;
	}
	public void setIdAvenent(Long idAvenent) {
		this.idAvenent = idAvenent;
	}
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public List<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(List<Clause> clauses) {
		this.clauses = clauses;
	}
	
}

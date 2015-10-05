package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 23/03/15
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "job_execution_hist", schema = "esecure")
public class JobExecutorHistory extends AbstractDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_JOB")
    private Long idJob;

    @Column(name="RAPPORT_NAME")
    private String rapportName;

    @Column(name="RAPPORT_PATH")
    private String rapportPath;

    @Column(name="RAPPORT_GENERATION_PARAMS")
    private String rapportGenerationParams;


    public JobExecutorHistory() {
    }

    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    public String getRapportName() {
        return rapportName;
    }

    public void setRapportName(String rapportName) {
        this.rapportName = rapportName;
    }

    public String getRapportPath() {
        return rapportPath;
    }

    public void setRapportPath(String rapportPath) {
        this.rapportPath = rapportPath;
    }

    public String getRapportGenerationParams() {
        return rapportGenerationParams;
    }

    public void setRapportGenerationParams(String rapportGenerationParams) {
        this.rapportGenerationParams = rapportGenerationParams;
    }
}

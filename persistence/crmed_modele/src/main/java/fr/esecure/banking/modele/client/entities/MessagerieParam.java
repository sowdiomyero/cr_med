package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 10/03/15
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "messagerie_param", schema = "esecure")
public class MessagerieParam extends AbstractDateEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_PARAM")
    private Long idParamSmtp;

    @Column(name="PARAM_SMTP_USERNAME")
    private String paramSmtpUsername;
    @Column(name="PARAM_SMTP_PASSWORD")
    private String paramSmtpPassword;
    @Column(name="PARAM_SMTP_TRANSPORT")
    private String paramSmtpTransport;
    @Column(name="PARAM_SMTP_EMAIL")
    private String paramSmtpEmail;
    @Column(name="PARAM_SMTP_SSL")
    private boolean paramSmtpSsl;
    @Column(name="PARAM_SMTP_HOST")
    private String paramSmtpHost;
    @Column(name="PARAM_SMTP_PORT")
    private Integer paramSmtpPort;

    @OneToOne(mappedBy = "messagerieParam")
    private ParamAcces params;

    public MessagerieParam() {
    }

    public String getParamSmtpUsername() {
        return paramSmtpUsername;
    }

    public void setParamSmtpUsername(String paramSmtpUsername) {
        this.paramSmtpUsername = paramSmtpUsername;
    }

    public String getParamSmtpPassword() {
        return paramSmtpPassword;
    }

    public void setParamSmtpPassword(String paramSmtpPassword) {
        this.paramSmtpPassword = paramSmtpPassword;
    }

    public String getParamSmtpTransport() {
        return paramSmtpTransport;
    }

    public void setParamSmtpTransport(String paramSmtpTransport) {
        this.paramSmtpTransport = paramSmtpTransport;
    }

    public String getParamSmtpEmail() {
        return paramSmtpEmail;
    }

    public void setParamSmtpEmail(String paramSmtpEmail) {
        this.paramSmtpEmail = paramSmtpEmail;
    }

    public boolean isParamSmtpSsl() {
        return paramSmtpSsl;
    }

    public void setParamSmtpSsl(boolean paramSmtpSsl) {
        this.paramSmtpSsl = paramSmtpSsl;
    }

    public String getParamSmtpHost() {
        return paramSmtpHost;
    }

    public void setParamSmtpHost(String paramSmtpHost) {
        this.paramSmtpHost = paramSmtpHost;
    }

    public Integer getParamSmtpPort() {
        return paramSmtpPort;
    }

    public void setParamSmtpPort(Integer paramSmtpPort) {
        this.paramSmtpPort = paramSmtpPort;
    }

    public Long getIdParamSmtp() {
        return idParamSmtp;
    }



    public ParamAcces getParams() {
        return params;
    }

    public void setParams(ParamAcces params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "MessagerieParam {" +
                "idParamSmtp=" + idParamSmtp +
                ", paramSmtpUsername='" + paramSmtpUsername + '\'' +
                ", paramSmtpPassword='" + paramSmtpPassword + '\'' +
                ", paramSmtpTransport='" + paramSmtpTransport + '\'' +
                ", paramSmtpEmail='" + paramSmtpEmail + '\'' +
                ", paramSmtpSsl=" + paramSmtpSsl +
                ", paramSmtpHost='" + paramSmtpHost + '\'' +
                ", paramSmtpPort=" + paramSmtpPort +
                ", params=" + params +
                '}';
    }
}

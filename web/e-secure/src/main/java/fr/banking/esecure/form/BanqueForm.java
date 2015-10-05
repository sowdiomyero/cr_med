package fr.banking.esecure.form;

import fr.banking.esecure.dto.BasicResponse;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class BanqueForm extends BasicResponse {

    private String idBanque;
    @NotNull @NotEmpty
    private String codeBanque;
    @NotNull @NotEmpty
    private String wsUsername;
    @NotNull @NotEmpty
    private String wsPassword;
    @NotNull @NotEmpty
    private String cigle;
    private String contact;
    private String validationFormUrl;
    private String frontEndUrl;
    private String wsEndpointUrl;



    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }




    public String getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(String idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
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

    public String getCigle() {
        return cigle;
    }

    public void setCigle(String cigle) {
        this.cigle = cigle;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

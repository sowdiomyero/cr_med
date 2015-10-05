package fr.esecure.banking.mail.sender;

import fr.esecure.banking.modele.client.entities.Client;
import fr.esecure.banking.modele.client.entities.ParamAcces;
import fr.esecure.banking.modele.client.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 23/02/15
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public interface IEmailSender {
    public void send(User user, String subject, String passwordGenerated, ParamAcces params);
    public void sendAccountCreated(User user, String subject, String passwordGenerated, ParamAcces params);
    public void sendAccountUpdated(User user, String subject, ParamAcces params);
    public void notifyCustomer(Client client, ParamAcces params);
    public void sendRapport(ParamAcces params, String filePath);

}

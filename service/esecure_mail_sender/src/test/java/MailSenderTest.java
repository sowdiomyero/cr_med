import fr.esecure.banking.mail.sender.IEmailSender;
import fr.esecure.banking.modele.client.entities.Compte;
import fr.esecure.banking.modele.client.entities.Role;
import fr.esecure.banking.modele.client.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 23/02/15
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/mail-sender-config.xml"})
public class MailSenderTest {

    @Autowired
    IEmailSender sender;
    Logger LOG = Logger.getLogger(MailSenderTest.class.getName());

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }



    @Test
    public void send_email_async() {
        User newUser = new User("sniang@groupeidyal.com", 1, "SOW", "Diom Yero", "33669932494");
        newUser.addRole(new Role("ROLE_USER"));
        Compte cp= new Compte();
        cp.setLogin("esecure");
        cp.setPassword("1dfr236Qp@25");
        newUser.setCompte(cp);
        LOG.log(Level.INFO, "send_email_async ::: DEBUT Appel au service");
        sender.send(newUser, "Création de compte utilisateur 01", "LvfBt2b!#", null);

        LOG.log(Level.INFO, "send_email_async ::: FIN Appel au service");

    }
}

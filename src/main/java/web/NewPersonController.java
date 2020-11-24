package web;

import dao.PersonStore;
import lombok.Data;
import model.Person;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.flow.FlowScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


// todo add automatic refresh every 5 sec
// todo connect the refreshing of the webpage in browser with refreshing data and not resending the previous request

@Data
@Named
//@RequestScoped - because after "post redirect get" the object value is null)
//@SessionScoped
@ConversationScoped
//@FlowScoped()
public class NewPersonController implements Serializable {

    @Inject
    private PersonStore personStore;

    @Inject
    private Conversation conversation;

    private Person newPerson = new Person();


    public String confirmNewPerson() {
        if (null == newPerson.getLogin()) {
            // after adding the Person and redirecting to the main try to send request try to open this http://localhost:8080/pas/faces/NewPersonConfirm.xhtml
            throw new IllegalArgumentException("Proba zatwirdzenia Person bez danych.");
        }
        personStore.addPerson(newPerson);
        conversation.end(); // and here we finish it
        return "main";
    }

    public String processNewPerson() {
        conversation.begin(); // here we start our scope
        return "NewPersonConfirm";
    }


    public String deletePerson(Person p) {
        personStore.deletePerson(p);
//        initCurrentPersons();
        return "PersonList"; // force the refreshing of the view
    }


}

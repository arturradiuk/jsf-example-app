package model;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;


// todo add automatic refresh every 5 sec
// todo connect the refreshing of the webpage in browser with refreshing data and not resending the previous request

@Data
@Named
//@RequestScoped - because after "post redirect get" the object value is null)
//@SessionScoped
@ViewScoped  // open person list delete person open index.xhtml and open person list you will see the changes or
                        // make deletePerson return PersonList
public class PersonController implements Serializable {

    @Inject
    private PersonStore personStore;

    private List<Person> currentPersons;

    private Person newPerson = new Person();


    public String confirmNewPerson() {
        if (null == newPerson.getLogin()) {
            // after adding the Person and redirecting to the main try to send request try to open this http://localhost:8080/pas/faces/NewPersonConfirm.xhtml
            throw new IllegalArgumentException("Proba zatwirdzenia Person bez danych.");
        }
        personStore.addPerson(newPerson);
        this.newPerson = new Person(); // to avoid buffering data
        return "main";
    }

    public String processNewPerson() {
        return "NewPersonConfirm";
    }

    public List<Person> getAllPersons() {
//        return personStore.findAll(); // to avoid "przeplot"
        return this.currentPersons;
    }

    public String deletePerson(Person p) {
        personStore.deletePerson(p);
//        initCurrentPersons();
        return "PersonList"; // force the refreshing of the view
    }

    @PostConstruct // after injection
    public void initCurrentPersons(){
        currentPersons = personStore.findAll();
    }


}

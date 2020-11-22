package web;

import lombok.Data;
import model.Person;
import dao.PersonStore;

import javax.annotation.PostConstruct;
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
@ViewScoped  // open person list delete person open index.xhtml and open person list you will see the changes or
// make deletePerson return PersonList
public class PersonListController implements Serializable {

    @Inject
    private PersonStore personStore;

    private List<Person> currentPersons;


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
    public void initCurrentPersons() {
        currentPersons = personStore.findAll();
    }


}

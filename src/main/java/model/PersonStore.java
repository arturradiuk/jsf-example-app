package model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class PersonStore {
    // there is no need  to implement Serializable interface because this object will exist forever
    // but only in one copy so there is no reasons
    private List<Person> persons = new ArrayList<>();

    public List<Person> findAll() {
        return persons.stream().collect(Collectors.toList());
    }

    public void addPerson(Person p) {
        this.persons.add(p);
        printPersons();
    }

    public void deletePerson(Person p){
        this.persons.remove(p);
        printPersons();
    }

    private void printPersons() {
        System.out.println(Arrays.toString(this.persons.toArray()));
    }

    @PostConstruct
    private void initStore(){
        persons.add(new Person("lolek","1","1"));
        persons.add(new Person("bolek","2","2"));
        persons.add(new Person("tola","3","3"));
        this.printPersons();
    }

}

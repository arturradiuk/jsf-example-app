package model;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Data
public class Person {
    private String login;
    private String password;
    private String phoneNumber;

    public Person(String login, String password, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }
}

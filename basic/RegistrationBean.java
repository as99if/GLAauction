/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.PersonManager;
import entities.Person;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hadiajalil
 */
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {

    @Inject 
    private PersonManager personManager;
    
    Long id; 
    private String firstname; 
    private String lastname;
    private String nickname;
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
   
    
    private void setFields(Person person) {
        this.id = -1L;
        this.firstname = "Not";
        this.lastname = "Found";
        this.nickname = "Dummy";
        
        if (person != null) {
            this.id = person.getId();
            this.firstname = person.getFirstName();
            this.lastname = person.getLastName();
            this.nickname = person.getNickName();
        }
    }
        
    public String addUser() {
        String res = "registrationKO";
        this.id = personManager.addPerson(new Person(firstname, lastname, nickname));
        if (this.id > 0) {
            res = "registrationKO";
            
        }
        return "listPersons";
    }
    
    public String find() {
//        Person person = personManager.findPerson(id);
//        this.setFields(person);
        return "registrationOK";
    }
}

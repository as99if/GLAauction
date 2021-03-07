/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.PersonManager;
import entities.Person;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author HP
 */
@Named(value = "personsBean")
@RequestScoped
public class PersonsBean {

    @Inject 
    private PersonManager personManager;

    /**
     * Creates a new instance of PersonsBean
     */
    public PersonsBean() {
    }
    
    public List<Person> allPersons() {
        List<Person> persons = personManager.getPersons();
        return persons;
    }
    
}

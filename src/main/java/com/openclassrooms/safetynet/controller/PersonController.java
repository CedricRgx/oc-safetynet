package com.openclassrooms.safetynet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


    @GetMapping("/persons")
    public String getPerson() {
        return "une liste de personne";
    }
/*

    @GetMapping("/persons")

    public Iterable<Person> getPersons() {
        persons.forEach(p -> System.out.println(p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)));
    }


    @GetMapping("/person/{phone}")
    public Person getPersonByPhone(@PathVariable String phone){
        Person person = new Person(phone);
        return person;
    }


    @GetMapping("/person")
    public String getPerson(){
        return "une personne";
    }

    @GetMapping("/person/{phone}")
    public Person getPersonByPhone(@PathVariable String phone){
        Person person = new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", phone, "soph@email.com");
        return person;
    }




    http://localhost:8080/person
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
        ajouter une nouvelle personne ;
        mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
        supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique).
    */

}

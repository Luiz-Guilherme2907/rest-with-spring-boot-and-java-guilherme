package br.com.guilherme.services;

import br.com.guilherme.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    //Mock de pessoa, para simular uma busca no banco de dados
    private Person mockPerson(int i){
        logger.info("Finding All persons");
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setName("PersonName: " + i);

        person.setAdress("Endereço: " + i);
        person.setCpf("Cpf " + i);

        return person;
    }
    public Person create(Person person){
        logger.info("Creating One person");
        return person;


    }
    public Person update(Person person){
        logger.info("Creating One person");
        return person;


    }
    public void delete(String id){
        logger.info("Deleting One person");
    }
    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++){
        Person person = mockPerson(i);
        persons.add(person);
        }
        return persons;

    }

    public Person findById(String id){
        logger.info("Finding One person");
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setName("Gui");

        person.setAdress("Rua olimpia");
        person.setCpf("190.190.190-90");

        return person;
    }

}

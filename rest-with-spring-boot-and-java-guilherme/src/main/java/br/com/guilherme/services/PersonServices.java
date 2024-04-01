package br.com.guilherme.services;

import br.com.guilherme.exception.ResourceNotFoundException;
import br.com.guilherme.model.Person;
import br.com.guilherme.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    //Mock de pessoa, para simular uma busca no banco de dados
    public Person create(Person person) {
        logger.info("Creating One person");
        return repository.save(person);


    }

    public Person update(Person person) {
        logger.info("Creating One person");
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setName(person.getName());
        entity.setAdress(person.getAdress());
        entity.setCpf(person.getCpf());

        return repository.save(person);


    }

    public void delete(Long id) {
        logger.info("Deleting One person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return repository.findAll();

    }

    public Person findById(Long id) {
        logger.info("Finding One person");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

}

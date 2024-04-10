package br.com.guilherme.services;

import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.exception.ResourceNotFoundException;
import br.com.guilherme.mapper.DozerMapper;
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
    public PersonVO create(PersonVO person) {
        logger.info("Creating One person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;

    }

    public PersonVO update(PersonVO person) {
        logger.info("Creating One person");
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setName(person.getName());
        entity.setAdress(person.getAdress());
        entity.setCpf(person.getCpf());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;


    }

    public void delete(Long id) {
        logger.info("Deleting One person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
       return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);

    }

    public PersonVO findById(Long id) {
        logger.info("Finding One person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);

    }

}

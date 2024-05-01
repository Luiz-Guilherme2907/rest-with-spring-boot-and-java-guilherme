package br.com.guilherme.services;

import br.com.guilherme.controller.PersonController;
import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.data.vo.v2.PersonVOV2;
import br.com.guilherme.exception.ResourceNotFoundException;
import br.com.guilherme.mapper.DozerMapper;
import br.com.guilherme.mapper.custom.PersonMapper;
import br.com.guilherme.model.Person;
import br.com.guilherme.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
    @Autowired
    PersonMapper mapper;

    //Mock de pessoa, para simular uma busca no banco de dados
    public PersonVO create(PersonVO person) {
        logger.info("Creating One person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }
    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating One person");
        var entity = mapper.convertVOToEntity(person);
        var vo = mapper.convertEntityToVO(repository.save(entity));
        return vo;

    }

    public PersonVO update(PersonVO person) {
        logger.info("Creating One person");
        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setName(person.getName());
        entity.setAdress(person.getAdress());
        entity.setCpf(person.getCpf());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;


    }

    public void delete(Long id) {
        logger.info("Deleting One person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
       var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
       persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
       return persons;

    }

    public PersonVO findById(Long id) {
        logger.info("Finding One person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

}

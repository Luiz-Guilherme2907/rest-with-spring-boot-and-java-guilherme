package br.com.guilherme.mapper.custom;

import br.com.guilherme.data.vo.v2.PersonVOV2;
import br.com.guilherme.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person){

        PersonVOV2 personVOV2 = new PersonVOV2();
        personVOV2.setId(person.getId());
        personVOV2.setName(person.getName());
        personVOV2.setAdress(person.getAdress());
        personVOV2.setCpf(person.getCpf());
        personVOV2.setBirthDay(new Date());

        return personVOV2;
    }
    public Person convertVOToEntity(PersonVOV2 person){

        Person personEntity = new Person();
        personEntity.setId(person.getId());
        personEntity.setName(person.getName());
        personEntity.setAdress(person.getAdress());
        personEntity.setCpf(person.getCpf());
       // person.setBirthDay(new Date());

        return personEntity;
    }
}

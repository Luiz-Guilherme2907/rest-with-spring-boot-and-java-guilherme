package br.com.guilherme.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.model.Person;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonVO mockVO() {
        return mockVO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAdress("Addres Test" + number);
        person.setName("Name Test" + number);
        person.setId(number.longValue());
        person.setCpf("CPF" + number);
        return person;
    }

    public PersonVO mockVO(Integer number) {
        PersonVO person = new PersonVO();
        person.setAdress("Addres Test" + number);
        person.setName("Name Test" + number);
        person.setKey(number.longValue());
        person.setCpf("CPF Test" + number);
        return person;
    }

}

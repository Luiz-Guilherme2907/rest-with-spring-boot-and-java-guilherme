package br.com.guilherme.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.mapper.DozerMapper;
import br.com.guilherme.model.Person;
import br.com.guilherme.unittests.mapper.mocks.MockPerson;

public class DozerConverterTest {
    
    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Name Test0", output.getName());
        assertEquals("CPF0", output.getCpf());
        assertEquals("Addres Test0", output.getAdress());
    }
    @Test
    public void parseEntityToVOTestComent() {
        PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Name Test0", output.getName());
        assertEquals("CPF0", output.getCpf());
        assertEquals("Addres Test0", output.getAdress());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerMapper.parseListObject(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Name Test0", outputZero.getName());
        assertEquals("CPF0", outputZero.getCpf());
        assertEquals("Addres Test0", outputZero.getAdress());
        PersonVO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Name Test7", outputSeven.getName());
        assertEquals("CPF7", outputSeven.getCpf());
        assertEquals("Addres Test7", outputSeven.getAdress());

        PersonVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Name Test12", outputTwelve.getName());
        assertEquals("CPF12", outputTwelve.getCpf());
        assertEquals("Addres Test12", outputTwelve.getAdress());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Name Test0", output.getName());
        assertEquals("CPF Test0", output.getCpf());
        assertEquals("Addres Test0", output.getAdress());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObject(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Name Test0", outputZero.getName());
        assertEquals("CPF Test0", outputZero.getCpf());
        assertEquals("Addres Test0", outputZero.getAdress());

        Person outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Name Test7", outputSeven.getName());
        assertEquals("CPF Test7", outputSeven.getCpf());
        assertEquals("Addres Test7", outputSeven.getAdress());

        Person outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Name Test12", outputTwelve.getName());
        assertEquals("CPF Test12", outputTwelve.getCpf());
        assertEquals("Addres Test12", outputTwelve.getAdress());
    }
}

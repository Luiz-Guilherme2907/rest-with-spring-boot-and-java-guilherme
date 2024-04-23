package br.com.guilherme.controller;


import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.data.vo.v2.PersonVOV2;
import br.com.guilherme.services.PersonServices;
import br.com.guilherme.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {
    @Autowired
    private PersonServices services;

    @PostMapping(value =  "/v1", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person){
        return services.create(person);
    }
    @PostMapping(value = "/v2", consumes ={ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return services.createV2(person);
    }

    @GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO findById(@PathVariable(value = "id")Long id){
            return services.findById(id);
    }

    @GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<PersonVO> findAll(){
        return services.findAll();
    }

    @PutMapping(value = "/v1", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML} )
    public PersonVO update(@RequestBody PersonVO person){
        return services.update(person);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
           services.delete(id);
           return ResponseEntity.noContent().build();
    }

}

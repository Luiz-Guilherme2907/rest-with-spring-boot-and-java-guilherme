package br.com.guilherme.services;

import br.com.guilherme.controller.PersonController;
import br.com.guilherme.data.vo.v1.PersonVO;
import br.com.guilherme.data.vo.v2.PersonVOV2;
import br.com.guilherme.exception.RequiredObjectIsNullException;
import br.com.guilherme.exception.ResourceNotFoundException;
import br.com.guilherme.mapper.DozerMapper;
import br.com.guilherme.mapper.custom.PersonMapper;
import br.com.guilherme.model.Person;
import br.com.guilherme.repositories.PersonRepository;
import br.com.guilherme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding One User by name " + username + "!");
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}

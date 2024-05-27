package br.com.guilherme.services;

import br.com.guilherme.controller.BookController;
import br.com.guilherme.data.vo.v1.BookVO;
import br.com.guilherme.exception.RequiredObjectIsNullException;
import br.com.guilherme.exception.ResourceNotFoundException;
import br.com.guilherme.mapper.DozerMapper;
import br.com.guilherme.mapper.custom.PersonMapper;
import br.com.guilherme.model.Book;
import br.com.guilherme.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;
    @Autowired
    PersonMapper mapper;

    public BookVO create(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating One book");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }
   /* public BookVOV2 createV2(BookVOV2 book) {
        logger.info("Creating One book");
        var entity = mapper.convertVOToEntity(book);
        var vo = mapper.convertEntityToVO(repository.save(entity));
        return vo;

    }*/

    public BookVO update(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating One book");
        var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;


    }

    public void delete(Long id) {
        logger.info("Deleting One book");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public List<BookVO> findAll() {
        logger.info("Finding all people");
       var books = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
       books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
       return books;

    }

    public BookVO findById(Long id) {
        logger.info("Finding One book");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

}

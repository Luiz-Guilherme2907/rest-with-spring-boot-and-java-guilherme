package br.com.guilherme.controller;


import br.com.guilherme.data.vo.v1.BookVO;
import br.com.guilherme.services.BookServices;
import br.com.guilherme.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
@Tag(name = "Book", description = "Endpoints for managing book")
public class BookController {
    @Autowired
    private BookServices services;

    @PostMapping(value = "/v1", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Add a book", description = "add a book by passing a JSON, YAML OR XML", tags = {"Book"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content =
            @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
    )
    public BookVO create(@RequestBody BookVO book) {
        return services.create(book);
    }


    /*@PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BookVOV2 createV2(@RequestBody BookVOV2 book) {
        return services.createV2(book);
    }*/

    @GetMapping(value = "/v1/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find a book", description = "Find a book by passing a JSON, YAML OR XML", tags = {"Book"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content =
            @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
    )
    public BookVO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @GetMapping(value = "/v1", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find All Book", description = "Find All Book by passing a JSON, YAML OR XML", tags = {"Book"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public List<BookVO> findAll() {
        return services.findAll();
    }

    @PutMapping(value = "/v1", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update a book", description = "Update a book by passing a JSON, YAML OR XML", tags = {"Book"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content =
            @Content(schema = @Schema(implementation = BookVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
    )
    public BookVO update(@RequestBody BookVO book) {
        return services.update(book);
    }

    @DeleteMapping(value = "/v1/{id}")
    @Operation(summary = "Deletes a book", description = "Delete a book by passing a JSON, YAML OR XML", tags = {"Book"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}

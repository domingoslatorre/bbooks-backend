package br.edu.ifsp.spo.bulls.competition.api.controller;

import br.edu.ifsp.spo.bulls.competition.api.domain.Competition;
import br.edu.ifsp.spo.bulls.competition.api.dto.CompetitionTO;
import br.edu.ifsp.spo.bulls.competition.api.service.CompetitionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/competitions", produces="application/json")
@CrossOrigin(origins = "*")
public class CompetitionController {

    private final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionService service;

    @ApiOperation(value = "Retorna uma competicação por Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Competicação encontrada"),
            @ApiResponse(code = 404, message = "Competicação não encontrada")
    })
    @GetMapping
    public Page<Competition> search(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        logger.info("Requisitando competições");
        return service.search(name, page, size);
    }

    @ApiOperation(value = "Retorna uma competicação por Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Competicação encontrada"),
            @ApiResponse(code = 404, message = "Competicação não encontrada")
    })
    @PostMapping
    public CompetitionTO save(@RequestBody CompetitionTO competition) {
        logger.info("Requisitando competições");
        return service.save(competition);
    }

    @ApiOperation(value = "Retorna uma competicação por Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Competicação encontrada"),
            @ApiResponse(code = 404, message = "Competicação não encontrada")
    })
    @PutMapping("/{id}")
    public CompetitionTO update(@RequestHeader("AUTHORIZATION") String token, @RequestBody CompetitionTO competition, @PathVariable UUID id) {
        logger.info("Requisitando competições");
        return service.update(token, competition, id);
    }

    @ApiOperation(value = "Retorna uma competicação por Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Competicação encontrada"),
            @ApiResponse(code = 404, message = "Competicação não encontrada")
    })
    @DeleteMapping("/{id}")
    public void delete(@RequestHeader("AUTHORIZATION") String token, @PathVariable UUID id) {
        logger.info("Requisitando competições");
        service.delete(token, id);
    }

    @ApiOperation(value = "Retorna uma competicação por Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Competicação encontrada"),
            @ApiResponse(code = 404, message = "Competicação não encontrada")
    })
    @GetMapping("/{id}")
    public CompetitionTO getById(@PathVariable UUID id) {
        logger.info("Requisitando competições");
        return service.getById(id);
    }

}
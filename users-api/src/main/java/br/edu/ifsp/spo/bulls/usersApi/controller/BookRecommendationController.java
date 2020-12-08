package br.edu.ifsp.spo.bulls.usersApi.controller;

import br.edu.ifsp.spo.bulls.usersApi.dto.BookRecommendationTO;
import br.edu.ifsp.spo.bulls.usersApi.service.BookRecommendationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/book-recommendation", produces="application/json")
@CrossOrigin(origins = "*")
public class BookRecommendationController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookRecommendationService service;

    @ApiOperation(value = "Enviar uma recomendação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a recomendação feita"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public BookRecommendationTO save(@RequestBody BookRecommendationTO recommendationTO){
        logger.info("Enviando nova reocmendação " + recommendationTO);
        return service.save(recommendationTO);
    }

    @ApiOperation(value = "Enviar uma recomendação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a recomendação atualizada"),
            @ApiResponse(code = 404, message = "Recomendação não encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{recommendationId}")
    public BookRecommendationTO update(@RequestBody BookRecommendationTO recommendationTO, @PathVariable UUID recommendationId){
        logger.info("Editando recomendação " + recommendationTO);
        return service.update(recommendationTO, recommendationId);
    }

    @ApiOperation(value = "Enviar uma recomendação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recomendação excluída"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{recommendationId}")
    public void delete(@PathVariable UUID recommendationId){
        logger.info("Excluindo recomendação " + recommendationId);
        service.delete(recommendationId);
    }

    @ApiOperation(value = "Pesquisar recomendações enviadas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recomendações encontradas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{profileSubmitter}")
    public List<BookRecommendationTO> getRecommentionsSent(@PathVariable int profileSubmitter){
        logger.info("Pesquisando recomendações enviadas pelo profile " + profileSubmitter);
        return service.getRecommentionsSent(profileSubmitter);
    }

    @ApiOperation(value = "Pesquisar recomendações enviadas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recomendações encontradas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/received/{profileReceived}")
    public List<BookRecommendationTO> getRecommentionsReceived(@PathVariable int profileReceived){
        logger.info("Pesquisando recomendações enviadas pelo profile " + profileReceived);
        return service.getRecommentionsReceived(profileReceived);
    }

}

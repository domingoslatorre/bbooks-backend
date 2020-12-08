package br.edu.ifsp.spo.bulls.usersApi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@ApiModel(value = "Objeto de domínio: Recomendação de livros")
public class BookRecommendation {

    @Id
    @ApiModelProperty(value = "Identificador")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ApiModelProperty(value = "Identificador do perfil que fez a recomendação")
    private int profileSubmitter;

    @ApiModelProperty(value = "Identificador do perfil que recebeu a recomendação")
    private int profileReceived;

    @ApiModelProperty(value = "Número identificador do livro da Api do google")
    private String idBookGoogle;

    @ApiModelProperty(value = "Livro")
    @ManyToOne
    private Book book;

    @ApiModelProperty(value = "Comentario")
    private String comentario;
}

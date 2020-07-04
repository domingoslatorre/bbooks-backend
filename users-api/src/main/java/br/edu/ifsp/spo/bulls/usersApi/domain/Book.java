package br.edu.ifsp.spo.bulls.usersApi.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String isbn10;

    @NotBlank(message = "Title is mandatory")
    private String title;


    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Author> authors;

    private int numberPage;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    private String category;

    private LocalDateTime publishedDate;

    private String edition;

    private String printType;

    @NotBlank(message = "Description is mandatory")
    private String description;

    public Book(@NotBlank(message = "Title is mandatory") String title,
                @NotBlank(message = "Author is mandatory") int numberPage,
                @NotBlank(message = "Language is mandatory") String language,
                @NotBlank(message = "Publisher is mandatory") String publisher,
                @NotBlank(message = "Published Date is mandatory") LocalDateTime publishedDate,
                @NotBlank(message = "Description is mandatory") String description) {
        this.title = title;
        this.numberPage = numberPage;
        this.language = language;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
    }

    public Book() {
    }

    public Book(@NotBlank(message = "Title is mandatory") String title,
                Set<Author> authors, int numberPage, @NotBlank(message = "Language is mandatory") String language,
                @NotBlank(message = "Publisher is mandatory") String publisher,
                String category, LocalDateTime publishedDate, String edition, String printType,
                @NotBlank(message = "Description is mandatory") String description) {
        this.title = title;
        //this.authors = authors;
        this.numberPage = numberPage;
        this.language = language;
        this.publisher = publisher;
        this.category = category;
        this.publishedDate = publishedDate;
        this.edition = edition;
        this.printType = printType;
        this.description = description;
    }


//    public Book( @NotBlank(message = "Title is mandatory") String title, List<Author> authors,
//                   int numberPage, @NotBlank(message = "Language is mandatory") String language,
//                   @NotBlank(message = "Publisher is mandatory") String publisher, LocalDateTime publishedDate,
//                   @NotBlank(message = "Description is mandatory") String description) {
//        this.title = title;
//        //this.authors = authors;
//        this.numberPage = numberPage;
//        this.language = language;
//        this.publisher = publisher;
//        this.publishedDate = publishedDate;
//        this.description = description;
//    }
}

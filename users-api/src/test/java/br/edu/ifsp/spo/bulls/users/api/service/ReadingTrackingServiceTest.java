package br.edu.ifsp.spo.bulls.users.api.service;

import br.edu.ifsp.spo.bulls.users.api.domain.Author;
import br.edu.ifsp.spo.bulls.users.api.dto.*;
import br.edu.ifsp.spo.bulls.users.api.enums.CodeException;
import br.edu.ifsp.spo.bulls.users.api.enums.Status;
import br.edu.ifsp.spo.bulls.users.api.exception.ResourceNotFoundException;
import br.edu.ifsp.spo.bulls.users.api.bean.BookBeanUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReadingTrackingServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public ProfileService profileService;

    @Autowired
    public UserBooksService userBooksService;

    @Autowired
    public ReadingTrackingService readingTrackingService;

    @Autowired
    public BookService bookService;

    @Autowired
    public BookBeanUtil bookBeanUtil;

    @Test
    void shouldSave() throws Exception {
//        int idProfile = this.umProfile("testeSaveAcOkService", "teste@testeSaveAcOkService");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.LENDO, "1234teste", 50);
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking( 30);
//
//        assertEquals(60.0, readingTrackingTO1.getPercentage());
    }

    @Test
    void shouldSaveAndStatusShouldBeLendo() throws Exception {
//        int idProfile = this.umProfile("testeSaveAcOkTrocaSatatus", "teste@testeSaveAcOkTrocaSatatus");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.QUERO_LER, "1234teste", 50);
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking( 30);
//        UserBooks.Status status = userBooksService.getById(userBookId).getStatus();
//
//        assertEquals(60.0, readingTrackingTO1.getPercentage());
//        assertEquals(UserBooks.Status.LENDO, status);
    }

    @Test
    void shouldSaveAndStatusShouldBeLido() throws Exception {
//        int idProfile = this.umProfile("testeSaveAcOkTrocaSatatusLido", "teste@testeSaveAcOkTrocaSatatusLido");
//        BookTO book = this.umBook("wejwqoie","livro teste", 50);
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.QUERO_LER, book);
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking( 50);
//        UserBooks.Status status = userBooksService.getById(userBookId).getStatus();
//
//        assertEquals(100.0F, readingTrackingTO1.getPercentage());
//        assertEquals(UserBooks.Status.LIDO, status);
    }

    @Test
    void shouldNotSaveIfStatusLido() throws Exception {
//        int idProfile = this.umProfile("testeSaveAcOkStatusLido", "teste@testeSaveAcOkStatusLido");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.LIDO, "1234teste", 50);
//
//        ResourceConflictException e = assertThrows(ResourceConflictException.class, () -> this.umReadingTracking(30));
//        assertEquals(CodeException.RT003.getText(), e.getMessage());
    }

    @Test
    void shouldNotSaveIfPageIsMoreThanBook() throws Exception {
//        int idProfile = this.umProfile("testeSaveAcOkPagina", "teste@testeSaveAcOkPagina");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.LENDO, "1234teste", 50);
//
//        ResourceConflictException e = assertThrows(ResourceConflictException.class, () -> this.umReadingTracking( 51));
//        assertEquals(CodeException.RT002.getText(), e.getMessage());
    }

    @Test
    void update() throws Exception {
//        int idProfile = this.umProfile("testeUpdateACOk", "teste@testeUpdateACOk");
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking(30);
//        readingTrackingTO1.setNumPag(35);
//        readingTrackingTO1 = readingTrackingService.update(readingTrackingTO1, readingTrackingTO1.getId());
//
//
//        assertEquals(35, readingTrackingTO1.getNumPag());
//        assertEquals(70.00F, readingTrackingTO1.getPercentage());
    }

    @Test
    void updateTrackingNotFound(){
        ReadingTrackingTO readingTrackingTO = new ReadingTrackingTO();
        readingTrackingTO.setId(UUID.randomUUID());

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
                () -> readingTrackingService.update(readingTrackingTO, readingTrackingTO.getId()));
        assertEquals(CodeException.RT001.getText(), e.getMessage());
    }

    @Test
    void updatePercentage100() throws Exception {
//        int idProfile = this.umProfile("testeUpdateACMudaLendo", "teste@testeUpdateACMudaLendo");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.QUERO_LER, "1234teste", 50);
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking( 50);
//
//        readingTrackingTO1.setNumPag(35);
//        readingTrackingTO1 = readingTrackingService.update(readingTrackingTO1, readingTrackingTO1.getId());
//
//        UserBooks.Status status = userBooksService.getById(userBookId).getStatus();
//
//        assertEquals(35, readingTrackingTO1.getNumPag());
//        assertEquals(70.00F, readingTrackingTO1.getPercentage());
//        assertEquals(UserBooks.Status.LENDO, status);
    }

    @Test
    void getOneTrackingNotFound(){
        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
                () -> readingTrackingService.get(UUID.randomUUID()));
        assertEquals(CodeException.RT001.getText(), e.getMessage());
    }

    @Test
    void getOneTrackingOk() throws Exception {
//        int idProfile = this.umProfile("testeAcOkGetOne", "teste@testeAcOkGetOne");
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking(30);
//
//        assertEquals(30, readingTrackingTO1.getNumPag());
//        assertEquals(100.00F, readingTrackingTO1.getPercentage());
    }

    @Test
    void delete() throws Exception {
//        int idProfile = this.umProfile("testeDeleteAcOkService", "teste@testeDeleteAcOkService");
//        long userBookId = this.umUserBook(idProfile, UserBooks.Status.QUERO_LER, "1234teste", 50);
//
//        ReadingTrackingTO readingTrackingTO1 = this.umReadingTracking( 30);
//
//        readingTrackingService.delete(readingTrackingTO1.getId());
//
//        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
//                () -> readingTrackingService.get(readingTrackingTO1.getId()));
//        assertEquals(CodeException.RT001.getText(), e.getMessage());
    }

    @Test
    void deleteTrackingNotFound() {
        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
                () -> readingTrackingService.delete(UUID.randomUUID()));
        assertEquals(CodeException.RT001.getText(), e.getMessage());
    }

    private Long umUserBook(int profileID, Status status, String idBookGoogle, int page){
        UserBooksTO userBooksTO = new UserBooksTO();
        userBooksTO.setProfileId(profileID);
        userBooksTO.setStatus(status);
        userBooksTO.setIdBookGoogle(idBookGoogle);
        userBooksTO.setPage(page);
        userBooksTO.setTags(new ArrayList<>());

        UserBooksTO userBooksTO1 = userBooksService.save(userBooksTO);
        return userBooksTO1.getId();
    }

    private Long umUserBook(int profileID, Status status, BookTO book){
        UserBooksTO userBooksTO = new UserBooksTO();
        userBooksTO.setProfileId(profileID);
        userBooksTO.setStatus(status);
        userBooksTO.setIdBook(book.getId());
        userBooksTO.setTags(new ArrayList<>());

        UserBooksTO userBooksTO1 = userBooksService.save(userBooksTO);
        return userBooksTO1.getId();
    }

    private ReadingTrackingTO umReadingTracking( int numberPage) {
        ReadingTrackingTO readingTrackingTO = new ReadingTrackingTO();
        readingTrackingTO.setNumPag(numberPage);
        return readingTrackingService.save(readingTrackingTO);
    }

    private int umProfile(String userName, String email) throws Exception {
        CadastroUserTO cadastroUserTO = new CadastroUserTO(userName, email, "senhate", "nome", "sobrenome");
        UserTO userTO = userService.save(cadastroUserTO);

        return profileService.getByUser(userTO.getUserName()).getId();
    }

    private BookTO umBook(String isbn, String title, int pages){
        BookTO book = new BookTO();

        book.setAuthors(new ArrayList<Author>(){
            {
                add(new Author("a"));
            }
        });
        book.setIsbn10(isbn);
        book.setTitle(title);
        book.setNumberPage(pages);
        book.setDescription("aaaaaa");
        book.setLanguage("portugues");
        book.setPublisher("teste");

        return bookService.save(book);
    }

}
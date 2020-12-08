package br.edu.ifsp.spo.bulls.users.api.service;

import br.edu.ifsp.spo.bulls.users.api.domain.Book;
import br.edu.ifsp.spo.bulls.users.api.domain.Profile;
import br.edu.ifsp.spo.bulls.users.api.domain.UserBooks;
import br.edu.ifsp.spo.bulls.users.api.dto.TagTO;
import br.edu.ifsp.spo.bulls.users.api.dto.UserBookUpdateStatusTO;
import br.edu.ifsp.spo.bulls.users.api.dto.UserBooksTO;
import br.edu.ifsp.spo.bulls.users.api.enums.CodeException;
import br.edu.ifsp.spo.bulls.users.api.enums.Status;
import br.edu.ifsp.spo.bulls.users.api.exception.ResourceConflictException;
import br.edu.ifsp.spo.bulls.users.api.exception.ResourceNotFoundException;
import br.edu.ifsp.spo.bulls.users.api.bean.UserBooksBeanUtil;
import br.edu.ifsp.spo.bulls.users.api.domain.Tag;
import br.edu.ifsp.spo.bulls.users.api.dto.BookCaseTO;
import br.edu.ifsp.spo.bulls.users.api.repository.BookRepository;
import br.edu.ifsp.spo.bulls.users.api.repository.ProfileRepository;
import br.edu.ifsp.spo.bulls.users.api.repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserBooksService {
    @Autowired
    private UserBooksRepository repository;
    @Autowired
    private UserBooksBeanUtil util;
    @Autowired
    private TagService tagService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public UserBooksTO save(UserBooksTO dto) {
        UserBooks userBooks = convertToUserBooks(dto);
        verifyIfUserbookIsAlreadyOnDatabase(userBooks);
        userBooks = repository.save(userBooks);

        saveTags(dto, userBooks);

        return util.toDto(userBooks);
    }

    private void verifyIfUserbookIsAlreadyOnDatabase(UserBooks userBooks) {
        Set<UserBooks> userBooksList = repository.findByProfile(userBooks.getProfile());

        for(UserBooks userBooks1 : userBooksList){
            if (userBooks1.getBook() == userBooks.getBook() || userBooks1.getIdBookGoogle() == userBooks.getIdBookGoogle()){
                throw new ResourceConflictException(CodeException.UB003.getText(), CodeException.UB003);
            }
        }
    }

    public UserBooksTO updateStatus(UserBookUpdateStatusTO dto) {
        UserBooks userBooks = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException(CodeException.UB001.getText(), CodeException.UB001));
        userBooks.setStatus(Status.getByString(dto.getStatus()));
        if(userBooks.getStatus() == null)
            throw new ResourceConflictException(CodeException.UB002.getText(), CodeException.UB002);

        UserBooks userBooks1 = repository.save(userBooks);
        System.out.println(userBooks1.toString());
        return util.toDto(userBooks1);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public BookCaseTO getByProfileId(int profileId) {
        BookCaseTO bookCase = new BookCaseTO();
        Optional<Profile> profile = profileRepository.findById(profileId);
        Set<UserBooks> userBooks = repository.findByProfile(profile.get());

        bookCase.setProfileId(profileId);

        Set<UserBooksTO> userBooksTO = util.toDtoSet(userBooks);
        bookCase.setBooks(userBooksTO);
        return bookCase;
    }

    public UserBooksTO getById(Long bookId) {
        UserBooks userBooks = repository.findById(bookId)
                .orElseThrow( () -> new ResourceNotFoundException(CodeException.UB001.getText(), CodeException.UB001));
        return util.toDto(userBooks);
    }

    public UserBooksTO update(UserBooksTO dto) {
        if(dto.getStatus() == null)
            throw new ResourceConflictException(CodeException.UB002.getText(), CodeException.UB002);

        List<Tag> tagsRemove = tagService.getByIdBook(dto.getId());
        for(Tag tag: tagsRemove){
            tagService.untagBook(tag.getId(),dto.getId());
        }

        return this.save(dto);
    }

    public UserBooks convertToUserBooks(UserBooksTO dto) {
        UserBooks userBooks = util.toDomain(dto);
        if(dto.getIdBook() != 0){
            userBooks.setBook(getBook(dto));
        }
        Profile profile = getProfile(dto);
        userBooks.setProfile(profile);
        return userBooks;
    }

    public Book getBook(UserBooksTO dto) {
        return bookRepository.findById(dto.getIdBook()).orElseThrow( () -> new ResourceNotFoundException(CodeException.BK002.getText(), CodeException.BK002));
    }

    public Profile getProfile(UserBooksTO dto) {
        return profileRepository.findById(dto.getProfileId()).orElseThrow( () -> new ResourceNotFoundException(CodeException.PF001.getText(), CodeException.PF001));
    }

    private void saveTags(UserBooksTO dto, UserBooks userBooks) {
        for(TagTO t : dto.getTags()){
            tagService.tagBook(t.getId(), userBooks.getId());
        }
    }
}
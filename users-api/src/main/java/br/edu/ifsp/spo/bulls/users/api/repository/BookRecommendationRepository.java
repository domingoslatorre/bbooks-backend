package br.edu.ifsp.spo.bulls.users.api.repository;

import br.edu.ifsp.spo.bulls.users.api.domain.BookRecommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRecommendationRepository extends CrudRepository <BookRecommendation, UUID> {

    List<BookRecommendation> findByProfileSubmitter (int profileSubmitter);
    List<BookRecommendation> findByProfileReceived  (int profileReceived);
}

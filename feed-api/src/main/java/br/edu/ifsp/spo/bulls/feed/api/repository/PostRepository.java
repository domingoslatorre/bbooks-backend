package br.edu.ifsp.spo.bulls.feed.api.repository;

import br.edu.ifsp.spo.bulls.feed.api.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

    List<Post> findByProfileId (int profileId);
}
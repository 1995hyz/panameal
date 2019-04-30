package Application.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findAllByPostId(Integer post_id);
}
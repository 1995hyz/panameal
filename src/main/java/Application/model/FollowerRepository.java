package Application.model;

import org.springframework.data.repository.CrudRepository;

public interface FollowerRepository extends CrudRepository<Follower, Integer> {
    Iterable<Follower> findAllByUserId(Iterable<Integer> iterable);
}

package Application.model;


import org.springframework.data.repository.CrudRepository;

public interface FollowingRepository extends CrudRepository<Following, Integer> {
    Iterable<Following> findAllByUserId(Iterable<Integer> iterable);
}

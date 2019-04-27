package Application.model;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FollowingRepository extends CrudRepository<Following, Integer> {
    Optional<Following> findByUserIdAndAndFollowto(Integer userId, Integer followto);
    Iterable<Following> findAllByUserId(Iterable<Integer> iterable);
}

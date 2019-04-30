package Application.model;


import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface FollowingRepository extends CrudRepository<Following, Integer> {
    Optional<Following> findByUseridAndAndFollowto(Integer userid, Integer followto);
    Iterable<Following> findAllByUserid(ArrayList<Integer> iterable);
    Iterable<Following> findAllByFollowto(ArrayList<Integer> iterable);
    Optional<Following> findByUserid(Integer userid);
}

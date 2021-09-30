package mn.isolvers.temp.service.internal.repository;

import mn.isolvers.temp.service.internal.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
    @Query("select * from users where age >= $1")
    Flux<User> findByAge(int age);

    @Query("exec getUser :id")
    Mono<User> getUser(int id);



}

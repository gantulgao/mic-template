package mn.isolvers.temp.service.internal.repository;

import mn.isolvers.temp.service.internal.model.dbInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InfoRepository extends ReactiveCrudRepository<dbInfo,Integer> {
   Mono<dbInfo> findById(Integer id);
}

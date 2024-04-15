package technical.test.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import technical.test.api.record.FlightRecord;

@Repository
public interface FlightRepository extends ReactiveMongoRepository<FlightRecord, UUID> {
	Flux<FlightRecord> findAllBy(Pageable pageable);
}

package technical.test.api.facade;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.representation.AirportRepresentation;
import technical.test.api.services.AirportService;

@Component
@RequiredArgsConstructor
public class AirportFacade {
	private final AirportService airportService;
	private final AirportMapper airportMapper;
	
	public Flux<AirportRepresentation> getAllAirports() {
		return airportService.getAllAirports()
				.flatMap(airportRecord -> {
					return Mono.just(this.airportMapper.convert(airportRecord));
				});
	}
}

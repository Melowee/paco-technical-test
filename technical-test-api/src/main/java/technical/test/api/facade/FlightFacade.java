package technical.test.api.facade;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.mapper.FlightMapper;
import technical.test.api.record.AirportRecord;
import technical.test.api.record.FlightRecord;
import technical.test.api.representation.FlightRepresentation;
import technical.test.api.services.AirportService;
import technical.test.api.services.FlightService;

@Component
@RequiredArgsConstructor
public class FlightFacade {
	private final FlightService flightService;
	private final AirportService airportService;
	private final FlightMapper flightMapper;
	private final AirportMapper airportMapper;

	public Mono<Page<FlightRepresentation>> getAllFlights(Pageable pageable) {
		return flightService.getAllFlights(pageable)
				.flatMap(flightRecord -> airportService.findByIataCode(flightRecord.getOrigin())
						.zipWith(airportService.findByIataCode(flightRecord.getDestination())).flatMap(tuple -> {
							AirportRecord origin = tuple.getT1();
							AirportRecord destination = tuple.getT2();
							FlightRepresentation flightRepresentation = this.flightMapper.convert(flightRecord);
							flightRepresentation.setOrigin(this.airportMapper.convert(origin));
							flightRepresentation.setDestination(this.airportMapper.convert(destination));
							return Mono.just(flightRepresentation);
						}))
				.collectList()
				.zipWith(this.flightService.getCount())
				.map(flights -> new PageImpl<FlightRepresentation>(flights.getT1(), pageable, flights.getT2()));
	}

	public Mono<FlightRepresentation> createFlight(Mono<FlightRepresentation> flight) {
    	return flight.flatMap(flightRepresentation -> {
    		flightRepresentation.setId(UUID.randomUUID());
    		FlightRecord flightRecord = this.flightMapper.convert(flightRepresentation);
    		return this.flightService.createFlight(flightRecord).flatMap(insertedFlightRecord -> {
    			FlightRepresentation insertedFlightRepresentation = this.flightMapper.convert(insertedFlightRecord);
    			return Mono.just(insertedFlightRepresentation);
    		});    		
    	});
    }
}

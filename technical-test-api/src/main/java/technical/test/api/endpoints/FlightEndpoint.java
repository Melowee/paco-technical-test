package technical.test.api.endpoints;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import technical.test.api.facade.FlightFacade;
import technical.test.api.representation.FlightRepresentation;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightEndpoint {
    private final FlightFacade flightFacade;

    @GetMapping
    public Mono<Page<FlightRepresentation>> getAllFlights(@PageableDefault(page = 0, size = 6, sort = { "price" }) Pageable pageable) {
        return flightFacade.getAllFlights(pageable);
    }
    
    @PostMapping
    public Mono<FlightRepresentation> createFlight(@RequestBody FlightRepresentation flight) {
    	return flightFacade.createFlight(Mono.just(flight));
    }
}

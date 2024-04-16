package technical.test.renderer.facades;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import technical.test.renderer.services.FlightService;
import technical.test.renderer.viewmodels.PageViewModel;

@Component
public class FlightFacade {

    private final FlightService flightService;

    public FlightFacade(FlightService flightService) {
        this.flightService = flightService;
    }

    public Mono<PageViewModel> getFlights() {
        return this.flightService.getFlights();
    }
}

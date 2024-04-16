package technical.test.renderer.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import technical.test.renderer.clients.TechnicalApiClient;
import technical.test.renderer.viewmodels.PageViewModel;

@Service
public class FlightService {
    private final TechnicalApiClient technicalApiClient;

    public FlightService(TechnicalApiClient technicalApiClient) {
        this.technicalApiClient = technicalApiClient;
    }

    public Mono<PageViewModel> getFlights() {
        return this.technicalApiClient.getFlights();
    }
}

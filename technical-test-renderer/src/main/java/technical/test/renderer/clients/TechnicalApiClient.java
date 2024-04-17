package technical.test.renderer.clients;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.renderer.properties.TechnicalApiProperties;
import technical.test.renderer.viewmodels.AirportViewModel;
import technical.test.renderer.viewmodels.FlightViewModel;
import technical.test.renderer.viewmodels.PageViewModel;

@Component
@Slf4j
public class TechnicalApiClient {

    private final TechnicalApiProperties technicalApiProperties;
    private final WebClient webClient;

    public TechnicalApiClient(TechnicalApiProperties technicalApiProperties, final WebClient.Builder webClientBuilder) {
        this.technicalApiProperties = technicalApiProperties;
        this.webClient = webClientBuilder.build();
    }
    
    public Mono<PageViewModel> getFlights(int page, String sort) {
        return webClient
                .get()
                .uri(technicalApiProperties.getUrl() + technicalApiProperties.getFlightPath()
                + "?page=" + page + "&sort=" + sort)
                .retrieve()
                .bodyToMono(PageViewModel.class);
    }
    
    public Flux<AirportViewModel> getAirports() {
    	return webClient
    			.get()
    			.uri(technicalApiProperties.getUrl() + technicalApiProperties.getAirportPath())
    			.retrieve()
    			.bodyToFlux(AirportViewModel.class);
    }
    
    public Mono<FlightViewModel> createFlight(Mono<FlightViewModel> flight) {
    	 return webClient
    			.post()
    			.uri(technicalApiProperties.getUrl() + technicalApiProperties.getFlightPath())
    			.contentType(MediaType.APPLICATION_JSON)
    			.body(BodyInserters.fromPublisher(flight, FlightViewModel.class))
    			.retrieve()
    			.bodyToMono(FlightViewModel.class);
    }
}

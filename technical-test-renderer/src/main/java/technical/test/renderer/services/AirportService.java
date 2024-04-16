package technical.test.renderer.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import technical.test.renderer.clients.TechnicalApiClient;
import technical.test.renderer.viewmodels.AirportViewModel;

@Service
@RequiredArgsConstructor
public class AirportService {
	private final TechnicalApiClient technicalApiClient;
	
	public Flux<AirportViewModel> getAirports() {
		return this.technicalApiClient.getAirports();
	}
}

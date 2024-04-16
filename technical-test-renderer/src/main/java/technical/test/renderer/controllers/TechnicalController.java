package technical.test.renderer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import technical.test.renderer.facades.AirportFacade;
import technical.test.renderer.facades.FlightFacade;
import technical.test.renderer.viewmodels.FlightViewModel;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class TechnicalController {

    @Autowired
    private FlightFacade flightFacade;
    
    @Autowired
    private AirportFacade airportFacade;

    @GetMapping
    public Mono<String> getMarketPlaceReturnCouponPage(final Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        model.addAttribute("page", this.flightFacade.getFlights(page));
        return Mono.just("pages/index");
    }
    
    @GetMapping("/create")
    public Mono<String> createFlightForm(Model model) {
    	model.addAttribute("flight", new FlightViewModel());
    	model.addAttribute("airports", this.airportFacade.getAirports());
    	return Mono.just("pages/newFlight");
    }
    
    @PostMapping("/create")
    public Mono<String> submitFlight(@ModelAttribute FlightViewModel flight, Model model) {
    	this.flightFacade.createFlight(Mono.just(flight)).subscribe();
    	return getMarketPlaceReturnCouponPage(model, 0);
    }
}

package technical.test.renderer.viewmodels;

import java.util.List;

import lombok.Data;

@Data
public class PageViewModel {
	private List<FlightViewModel> content;
	private int number;
	private int size;
	private int totalElements;
	private int totalPages;
	private boolean first;
	private boolean last;
}

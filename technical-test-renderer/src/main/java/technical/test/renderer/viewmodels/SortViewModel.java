package technical.test.renderer.viewmodels;

import lombok.Data;

@Data
public class SortViewModel {
	private String direction;
	private String property;
	private boolean ignoreCase;
	private String nullHandling;
	private boolean descending;
	private boolean ascending;
}

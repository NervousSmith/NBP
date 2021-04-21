package kowalski.pawel.nbp;

import java.math.BigDecimal;

public class AverageExchangeRate {
	
	private BigDecimal mid;
	
	public AverageExchangeRate(BigDecimal mid) {
		this.mid = mid;
	}

	public BigDecimal getMid() {
		return mid;
	}

	public String midToString() {
		return mid.toString();
	}
}

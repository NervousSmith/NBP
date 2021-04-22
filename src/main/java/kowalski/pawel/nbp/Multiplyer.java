package kowalski.pawel.nbp;

import java.math.BigDecimal;

public class Multiplyer implements Calculator{


	@Override
	public BigDecimal calculate(BigDecimal multiplier1, BigDecimal multiplier2) {
		return multiplier1.multiply(multiplier2);
	}
}

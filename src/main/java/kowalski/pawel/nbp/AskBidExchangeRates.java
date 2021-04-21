package kowalski.pawel.nbp;

import java.math.BigDecimal;

public class AskBidExchangeRates {

	private BigDecimal ask;
	private BigDecimal bid;

	public AskBidExchangeRates(BigDecimal ask, BigDecimal bid) {
		this.ask = ask;
		this.bid = bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public String askToString() {
		return ask.toString();
	}

	public String bidToString() {
		return bid.toString();
	}
}
package kowalski.pawel.nbp.ForFileApi;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.interfaces.Api;

public class FileExchangeRate implements Api {

	private File fileWithExchangeRate;
	
	public FileExchangeRate(File fileWithExchangeRate) {
		this.fileWithExchangeRate = fileWithExchangeRate;
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		return null;
			}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, BigDecimal ammount) {
		// TODO Auto-generated method stub
		return null;
	}

}

package kowalski.pawel.nbp.FileApi;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.ReceiveRateHandler;
import kowalski.pawel.nbp.apiInterfaces.Api;

public class FileApi implements Api {

	private File fileWithExchangeRate;
	private FileDataProvider provider;
	private FileDataParser parser;
	private ReceiveRateHandler handler;
	
	public FileApi(File fileWithExchangeRate) {
		this.fileWithExchangeRate = fileWithExchangeRate;
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		provider = new FileDataProvider();
		parser = new FileDataParser();
		handler = new ReceiveRateHandler(provider, parser);
		return null;
			}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, BigDecimal ammount) {
		// TODO Auto-generated method stub
		return null;
	}

}

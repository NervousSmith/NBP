package kowalski.pawel.nbp;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.ForFileApi.FileReader;
import kowalski.pawel.nbp.ForFileApi.FromFileDataGetter;
import kowalski.pawel.nbp.ForFileApi.FromFileDataParser;
import kowalski.pawel.nbp.NbpApi.NbpDataGetter;
import kowalski.pawel.nbp.NbpApi.NbpDataParser;
import kowalski.pawel.nbp.NbpApi.NbpDataReader;
import kowalski.pawel.nbp.NbpApi.NbpDataRequester;
import kowalski.pawel.nbp.interfaces.Calculator;
import kowalski.pawel.nbp.interfaces.Currency;
import kowalski.pawel.nbp.interfaces.DataGetter;
import kowalski.pawel.nbp.interfaces.DataParser;
import kowalski.pawel.nbp.interfaces.DataReader;
import kowalski.pawel.nbp.interfaces.DataRequester;

public class ExchangeRatesApi {
	
	private DataParser parser;
	private DataReader reader;
	private DataRequester requester;
	private Calculator calculator;
	private DataGetter getter;

	public Optional<BigDecimal> calculateExchange(Currency currencyCode,
			BigDecimal ammount, LocalDate date) {
		date = verifyDate(date);
		parser = new NbpDataParser();
		reader = new NbpDataReader();
		requester = new NbpDataRequester();
		calculator = new Multiplyer();
		getter = new NbpDataGetter(calculator, requester, reader, parser);
		return getter.receiveData(currencyCode, ammount, date);
	}

	public Optional<BigDecimal> calculateExchangeFromFile(Currency currencyCode, BigDecimal ammount, LocalDate date, File fileToRead) {
		date = verifyDate(date);
		reader = new FileReader();
		parser = new FromFileDataParser();
		calculator = new Multiplyer();
		getter = new FromFileDataGetter(calculator, reader, parser, fileToRead);
		return getter.receiveData(currencyCode, ammount, date);
	}
	
	private LocalDate verifyDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		} else {
			return date;
		}
		
	}
}
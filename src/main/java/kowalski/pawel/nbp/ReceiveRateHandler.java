package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataProvider;

public class ReceiveRateHandler{

	private DataProvider provider;
	private DataParser parser;
	
	public ReceiveRateHandler(DataProvider provider, DataParser parser) {
		this.provider = provider;
		this.parser = parser;
	}

	public Optional<BigDecimal> receiveData(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		date = verifyDate(date);
		Optional<BigDecimal> result = checkForDate(currencyCode.name(), date);
		if(result.isEmpty()) {
			result = lookForEffect(currencyCode.name(), date);
		}
		result.ifPresent(result2 -> result2.multiply(ammount));
		return result;
	}


	private LocalDate verifyDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		} else {
			return date;
		}
	}

	private Optional<BigDecimal> lookForEffect(String currencyCode, 
			LocalDate dateToCheck){
		Optional<BigDecimal> result = null;
		for (int i = 1; i < 11; i++) {
			result = checkForDate(currencyCode, dateToCheck.minusDays(i));
			if (result.isPresent()) {
				break;
			} else if (result.isEmpty()) {
				result = Optional.empty();
			}
		}
		return result;
	}

	private Optional<BigDecimal> checkForDate(String currencyCode, LocalDate dateToCheck) {
		try {
			String receivedResponse = 
					provider.requestData(currencyCode, dateToCheck);
			BigDecimal result = parser.parseReadData(receivedResponse);
			return Optional.ofNullable(result);
		} catch (NullPointerException e) {
			return Optional.empty();
		}
	}

}

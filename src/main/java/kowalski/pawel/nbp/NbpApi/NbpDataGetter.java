package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataRequester;

public class NbpDataGetter{

	private DataRequester requester;
	private DataParser parser;
	
	public NbpDataGetter(ContextProvider context) {
		this.requester = new NbpDataRequester();
		this.parser = new NbpDataParser();
	}

	public Optional<BigDecimal> receiveData(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		date = verifyDate(date);
		Optional<BigDecimal> result = checkDate(currencyCode, date);
		if(result.isPresent()) {
			return Optional.ofNullable(result.get().multiply(ammount));
		}
		return lookForEffect(date);
	}


	private LocalDate verifyDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		} else {
			return date;
		}
	}

	private Optional<BigDecimal> lookForEffect(LocalDate dateToCheck){
		Optional<BigDecimal> result = null;
		for (int i = 1; i < 11; i++) {
			result = checkDate(dateToCheck.minusDays(i));
			if (result.isPresent()) {
				break;
			} else if (result.isEmpty()) {
				result = Optional.ofNullable(null);
			}
		}
		return result;
	}

	private Optional<BigDecimal> checkDate(Currency currencyCode, LocalDate dateToCheck) {
		try {
			String tempString = requester.requestData(currencyCode.name(), dateToCheck);
			BigDecimal result = parser.getParsedReadData(tempString);
			return Optional.ofNullable(result);
		} catch (NullPointerException e) {
			return Optional.empty();
		}
	}

}

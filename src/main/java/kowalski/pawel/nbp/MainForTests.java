package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.NbpApi.NbpApi;

public class MainForTests {

	public static void main(String[] args) {

		NbpApi api = new NbpApi();
		Optional<BigDecimal> result = api.calculateExchange(Currency.USD, new BigDecimal("23"),
				LocalDate.now().plusDays(2));
		if(result.isPresent()) {
			System.out.println(result.get().toString());
		}else {
			System.out.println("Brak danych");
		}
		
	}
	


}

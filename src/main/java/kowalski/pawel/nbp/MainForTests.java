package kowalski.pawel.nbp;

import java.time.LocalDate;

public class MainForTests {

	public static void main(String[] args) {

		DataRequester requester = new NbpDataRequester(CurrencyCodesForTableA.USD.name(), LocalDate.now());
		DataReader reader = new NbpDataReader(requester.requestData());
		DataParser parser = new NbpReadDataParser(reader.readReceivedData());
		
		System.out.println(parser.getParsedReadData());
		
		}
	
	
	
	
}

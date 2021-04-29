package kowalski.pawel.nbp.NbpApi;

import kowalski.pawel.nbp.apiInterfaces.ConnectionCreator;
import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataProvider;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class NbpJsonStrategy implements Strategy{

	ConnectionCreator creator = new NbpConnectionCreatorForJson();
	
	@Override
	public DataParser getParser(){
		return new NbpJsonParser();
	}
	@Override
	public DataProvider getProvider() {
		return new NbpDataProvider(creator);
	}
	
}

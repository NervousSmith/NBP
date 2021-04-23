package kowalski.pawel.nbp.ForFileApi;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;

import kowalski.pawel.nbp.apiInterfaces.Calculator;
import kowalski.pawel.nbp.apiInterfaces.DataGetter;
import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataReader;
import kowalski.pawel.nbp.apiInterfaces.DataRequester;

public class FromFileDataGetter implements DataGetter{

	
	private Calculator calculator;
	private DataRequester requester;
	private DataReader reader;
	private DataParser parser;
	private File fileToRead;
	
	

	public FromFileDataGetter(Calculator calculator, File fileToRead) {
		super();
		this.calculator = calculator;
		this.requester = new FromFileDataRequester();
		this.reader = new FromFileDataReader();
		this.parser = new FromFileDataParser();
		this.fileToRead = fileToRead;
	}
	
	@Override	
	public Optional<BigDecimal> receiveData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

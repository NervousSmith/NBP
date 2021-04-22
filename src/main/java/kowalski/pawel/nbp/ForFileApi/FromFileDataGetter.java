package kowalski.pawel.nbp.ForFileApi;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.interfaces.Calculator;
import kowalski.pawel.nbp.interfaces.Currency;
import kowalski.pawel.nbp.interfaces.DataGetter;
import kowalski.pawel.nbp.interfaces.DataParser;
import kowalski.pawel.nbp.interfaces.DataReader;

public class FromFileDataGetter implements DataGetter{

	
	private Calculator calculator;
	private DataReader reader;
	private DataParser parser;
	private File fileToRead;
	
	public FromFileDataGetter(Calculator calculator, DataReader reader, DataParser parser, File file) {
		super();
		this.calculator = calculator;
		this.reader = reader;
		this.parser = parser;
		this.fileToRead = file;
	}

	@Override
	public Optional<BigDecimal> receiveData(Currency currencyCode, BigDecimal ammount, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

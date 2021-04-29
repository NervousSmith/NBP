package kowalski.pawel.nbp.FileApi;

import java.io.File;

import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataProvider;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class FileJsonStrategy implements Strategy{

	File fileToRead;
	
	public FileJsonStrategy(File fileToRead) {
		this.fileToRead = fileToRead;
	}

	@Override
	public DataParser getParser() {
		return new FileJsonDataParser();
	}

	@Override
	public DataProvider getProvider() {
		return new FileDataProvider(fileToRead);
	}

}

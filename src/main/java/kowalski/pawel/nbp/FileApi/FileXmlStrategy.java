package kowalski.pawel.nbp.FileApi;

import java.io.File;

import kowalski.pawel.nbp.apiInterfaces.DataParser;
import kowalski.pawel.nbp.apiInterfaces.DataProvider;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class FileXmlStrategy implements Strategy {

File fileToRead;
	
	public FileXmlStrategy(File fileToRead) {
		this.fileToRead = fileToRead;
	}

	@Override
	public DataParser getParser() {
		return new FileXmlParser();
	}

	@Override
	public DataProvider getProvider() {
		return new FileDataProvider(fileToRead);
	}

}

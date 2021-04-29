package kowalski.pawel.nbp.FileApi;

import java.io.File;
import java.time.LocalDate;

import kowalski.pawel.nbp.apiInterfaces.DataProvider;

public class FileDataProvider implements DataProvider {

	File fileToRead;
	
	public FileDataProvider(File fileToRead) {
		this.fileToRead = fileToRead;
	}



	@Override
	public String requestData(String currencyCode, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}

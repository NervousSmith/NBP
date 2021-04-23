package kowalski.pawel.nbp.NbpApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kowalski.pawel.nbp.apiInterfaces.DataReader;

public class NbpDataReader implements DataReader{

	private InputStreamReader receivedDataStream;

	@Override
	public String readReceivedData(InputStreamReader receivedDataStream){
		this.receivedDataStream = receivedDataStream;
		return readStream();
	}
	
	private String readStream() {
		BufferedReader bufferedReader;
		String output;
		try {
			bufferedReader = new BufferedReader(receivedDataStream);
			output = bufferedReader.readLine();
		} catch (IOException e) {
			output = "";
		}
		return output;
	}
	
	
	
	
}

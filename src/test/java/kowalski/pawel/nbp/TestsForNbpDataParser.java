package kowalski.pawel.nbp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import kowalski.pawel.nbp.NbpApi.NbpJsonParser;

public class TestsForNbpDataParser {

	
	@Test
	public void shouldParseProperlyForNbpJson() {
		//given
		String JsonFromNbp = "{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"064/A/NBP/2016\",\"effectiveDate\":\"2016-04-04\",\"mid\":3.7254}]}";
		NbpJsonParser parser = new NbpJsonParser();
		//when
		BigDecimal result = parser.parseReadData(JsonFromNbp);
		//then
		assertThat(result).isEqualByComparingTo("3.7254");
	}
	
	@Test
	public void shouldThrowJSONException() {
		//given 
		JSONObject invalidJson = new JSONObject();
		invalidJson.put("table", "A");
		invalidJson.put("rate", "3.7254");
		NbpJsonParser parser = new NbpJsonParser();
		//when
		try {
		BigDecimal result = parser.parseReadData(invalidJson.toString());
		//then
		fail("Should throw JSONException");
		}
		catch(Throwable e) {
			assertThat(e).isInstanceOf(JSONException.class);
		}
	}
}

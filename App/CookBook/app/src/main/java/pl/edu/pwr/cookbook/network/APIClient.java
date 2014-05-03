package pl.edu.pwr.cookbook.network;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pwr.cookbook.model.Results;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class APIClient {
	private static APIClient instance = null;

	private APIClient() {
	}

	public static APIClient getInstance() {
		if (instance == null) {
			instance = new APIClient();
		}
		return instance;
	}

	public ArrayList<Results> getResults(Context _context) throws APIErrorException {
		HttpClient client = new HttpClient(_context);
		String json = "";
		try {
			json = client.request("http://isweb.eu/web/jd/fakeAPI/list.json");
		} catch (HttpClientException e) {
			throw new APIErrorException("no internet connection");
		}

		// parse
		return new Gson().fromJson(json, new TypeToken<List<Results>>() {}.getType());
	}
}

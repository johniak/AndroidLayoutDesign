package pl.edu.pwr.cookbook.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpClient {
	
	private Context _context;
	
	public HttpClient(Context context) {
		this._context = context;
	}

	public String request(String URL) throws HttpClientException {
		try {
			URL url = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			return readStream(con.getInputStream());
		} catch (Exception e) {
			throw new HttpClientException(e.getMessage());
		}
	}
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();

	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    
	    return false;
	} 

	private String readStream(InputStream in) {
		BufferedReader reader = null;
		String result = "";
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

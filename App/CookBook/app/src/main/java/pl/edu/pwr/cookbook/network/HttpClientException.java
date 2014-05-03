package pl.edu.pwr.cookbook.network;

public class HttpClientException extends Exception {
	public HttpClientException(String message) {
		super(message);
	}

	public HttpClientException(String message, Throwable throwable) {
		super(message, throwable);
	}

}

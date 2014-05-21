package pl.edu.pwr.cookbook.network;

public class APIErrorException extends Exception {
	public APIErrorException(String message) {
		super(message);
	}

	public APIErrorException(String message, Throwable throwable) {
		super(message, throwable);
	}

}

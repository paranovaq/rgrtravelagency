package travelagency.exception;

public class TravelAgencyResourceNotFoundException extends RuntimeException {
    public TravelAgencyResourceNotFoundException(String message) {
        super(message);
    }
}
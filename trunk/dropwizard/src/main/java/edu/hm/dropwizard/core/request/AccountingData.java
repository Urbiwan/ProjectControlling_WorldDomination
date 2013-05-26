package edu.hm.dropwizard.core.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountingData {
	@JsonProperty("BookingData")
	private List<Entry> booking;
	
	/**
	 * Return an immutable List.
	 * @return immutable List.
	 */
	public List<Entry> getBookings() {
		return Collections.unmodifiableList(booking);
	}
	
	public AccountingData(List<String> args) {
		
	}
	
}

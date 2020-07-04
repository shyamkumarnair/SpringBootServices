package com.handson.user.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocodeResponse {

	@JsonProperty("status")
	private String status;
	@JsonProperty("results")
	private Result[] results;

	/**
	 * Default constructor
	 */
	public GeocodeResponse() {
		super();
	}

	/**
	 * @param status
	 * @param results
	 */
	public GeocodeResponse(String status, Result[] results) {
		super();
		this.status = status;
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(results);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeocodeResponse other = (GeocodeResponse) obj;
		if (!Arrays.equals(results, other.results))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeocodeResponse [status=" + status + ", results=" + Arrays.toString(results) + "]";
	}

}

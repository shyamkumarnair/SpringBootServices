package com.handson.user.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bound {

	@JsonProperty("northeast")
	private Location northeast;
	@JsonProperty("southwest")
	private Location southwest;

	/**
	 * @param northeast
	 * @param southwest
	 */
	public Bound(Location northeast, Location southwest) {
		super();
		this.northeast = northeast;
		this.southwest = southwest;
	}

	/**
	 * Default constructor
	 */
	public Bound() {
		super();
	}

	public Location getNortheast() {
		return northeast;
	}

	public void setNortheast(Location northeast) {
		this.northeast = northeast;
	}

	public Location getSouthwest() {
		return southwest;
	}

	public void setSouthwest(Location southwest) {
		this.southwest = southwest;
	}

	@Override
	public String toString() {
		return "Bound [northeast=" + northeast + ", southwest=" + southwest + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((northeast == null) ? 0 : northeast.hashCode());
		result = prime * result + ((southwest == null) ? 0 : southwest.hashCode());
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
		Bound other = (Bound) obj;
		if (northeast == null) {
			if (other.northeast != null)
				return false;
		} else if (!northeast.equals(other.northeast))
			return false;
		if (southwest == null) {
			if (other.southwest != null)
				return false;
		} else if (!southwest.equals(other.southwest))
			return false;
		return true;
	}

}

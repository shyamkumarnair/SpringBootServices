package com.handson.user.json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	
	@JsonProperty("address_components")
	private AddressComponent[] addressComponents;
	@JsonProperty("formatted_address")
	private String formattedAddress;
	@JsonProperty("geometry")
	private Geometry geometry;
	@JsonProperty("place_id")
	private String place_id;
	@JsonProperty("types")
	private String[] types;

	/**
	 * Default constructor
	 */
	public Result() {
		super();
	}

	/**
	 * @param addressComponents
	 * @param formattedAddress
	 * @param geometry
	 * @param place_id
	 * @param types
	 */
	public Result(AddressComponent[] addressComponents, String formattedAddress, Geometry geometry, String place_id,
			String[] types) {
		super();
		this.addressComponents = addressComponents;
		this.formattedAddress = formattedAddress;
		this.geometry = geometry;
		this.place_id = place_id;
		this.types = types;
	}

	public AddressComponent[] getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(AddressComponent[] addressComponents) {
		this.addressComponents = addressComponents;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(addressComponents);
		result = prime * result + ((formattedAddress == null) ? 0 : formattedAddress.hashCode());
		result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
		result = prime * result + ((place_id == null) ? 0 : place_id.hashCode());
		result = prime * result + Arrays.hashCode(types);
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
		Result other = (Result) obj;
		if (!Arrays.equals(addressComponents, other.addressComponents))
			return false;
		if (formattedAddress == null) {
			if (other.formattedAddress != null)
				return false;
		} else if (!formattedAddress.equals(other.formattedAddress))
			return false;
		if (geometry == null) {
			if (other.geometry != null)
				return false;
		} else if (!geometry.equals(other.geometry))
			return false;
		if (place_id == null) {
			if (other.place_id != null)
				return false;
		} else if (!place_id.equals(other.place_id))
			return false;
		if (!Arrays.equals(types, other.types))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Result [addressComponents=" + Arrays.toString(addressComponents) + ", formattedAddress="
				+ formattedAddress + ", geometry=" + geometry + ", place_id=" + place_id + ", types="
				+ Arrays.toString(types) + "]";
	}
}

/**
 * 
 */
package com.handson.user.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author PattathilS
 *
 */
public class Geometry {

	@JsonProperty("bounds")
	private Bound bounds;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("location_type")
	private String locationType;
	@JsonProperty("viewport")
	private Bound viewport;

	/**
	 * Default constructor
	 */
	public Geometry() {
		super();
	}

	/**
	 * @param bounds
	 * @param location
	 * @param locationType
	 * @param viewport
	 */
	public Geometry(Bound bounds, Location location, String locationType, Bound viewport) {
		super();
		this.bounds = bounds;
		this.location = location;
		this.locationType = locationType;
		this.viewport = viewport;
	}

	public Bound getBounds() {
		return bounds;
	}

	public void setBounds(Bound bounds) {
		this.bounds = bounds;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Bound getViewport() {
		return viewport;
	}

	public void setViewport(Bound viewport) {
		this.viewport = viewport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((locationType == null) ? 0 : locationType.hashCode());
		result = prime * result + ((viewport == null) ? 0 : viewport.hashCode());
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
		Geometry other = (Geometry) obj;
		if (bounds == null) {
			if (other.bounds != null)
				return false;
		} else if (!bounds.equals(other.bounds))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (locationType == null) {
			if (other.locationType != null)
				return false;
		} else if (!locationType.equals(other.locationType))
			return false;
		if (viewport == null) {
			if (other.viewport != null)
				return false;
		} else if (!viewport.equals(other.viewport))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Geometry [bounds=" + bounds + ", location=" + location + ", locationType=" + locationType
				+ ", viewport=" + viewport + "]";
	}

}

package com.notes.app.utils;

public class City {
	private String name;
	private boolean seaCity;
	private boolean chiefTown;

	public City(String name, boolean chiefTown, boolean seaCity) {
		super();
		this.name = name;
		this.seaCity = seaCity;
		this.chiefTown = chiefTown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSeaCity() {
		return seaCity;
	}

	public void setSeaCity(boolean seaCity) {
		this.seaCity = seaCity;
	}

	public boolean isChiefTown() {
		return chiefTown;
	}

	public void setChiefTown(boolean chiefTown) {
		this.chiefTown = chiefTown;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", seaCity=" + seaCity + ", chiefTown=" + chiefTown + "]";
	}

}

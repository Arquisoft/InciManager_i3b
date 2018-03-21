package hello.entities;

import java.util.Random;

public class Coordinates {

	private double longitude;
	private double latitude;
	private Random r;
	
	public Coordinates() {
		this.longitude = r.nextDouble()*100;
		this.latitude = r.nextDouble()*100;
	}

	public double getLongitud() {
		return longitude;
	}

	public double getLatitud() {
		return latitude;
	}

	public String getCoordinates()
	{
		return "Lat : " +latitude + " | Long : " +longitude;
	}

}
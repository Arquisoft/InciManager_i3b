package uniovi.es.entities;

import java.text.DecimalFormat;
import java.util.Random;

public class Coordinates {

	private double longitude;
	private double latitude;
	private Random r = new Random();
	
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
		DecimalFormat df = new DecimalFormat("#.##");  
		return "Lat : " + df.format(getLatitud()) + " | Long : " + df.format(getLongitud());
	}

}
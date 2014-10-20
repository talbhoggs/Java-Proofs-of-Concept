package cdi2;

@SportsCarQualifier
public class SportsCar implements Car {
	
	private String color = "Red";
	private String make = "Jaguar";
	
	public String getColor() {
		return color;
	}
	
	public String getMake() {
		return make;
	}

	@Override
	public String toString() {
		return "SportsCar [color=" + color + ", make=" + make + "]";
	}
	
	

}

package cdi2;

@FamilyCarQualifier
public class FamilyCar implements Car {
	
	private String color = "green";
	private String make = "Honda";
	
	public String getColor() {
		return color;
	}
	
	public String getMake() {
		return make;
	}
	
	@Override
	public String toString() {
		return "FamilyCar [color=" + color + ", make=" + make + "]";
	}

}

package com.daniel;

public class Aquarium {
	
	private Fish tunafish;
	private Fish catfish;
	
	public Aquarium(Tuna t, Catfish c) {
		this.tunafish = t;
		this.catfish = c;
	}
	
	public Fish getTunafish() {
		return tunafish;
	}
	public void setTunafish(Fish tunafish) {
		this.tunafish = tunafish;
	}
	public Fish getCatfish() {
		return catfish;
	}
	public void setCatfish(Fish catfish) {
		this.catfish = catfish;
	}
	
	public void tunaSwim(String text) {
		this.tunafish.swim(text);
	}
	
	public void catfishSwim(String text) {
		this.catfish.swim(text);
	}
	
}

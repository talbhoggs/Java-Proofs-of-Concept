package cdi2;

import javax.ejb.Local;

@Local
public interface GreeterLocal {
	public void greet();
	public String getName();
	public void setName(String name);
}

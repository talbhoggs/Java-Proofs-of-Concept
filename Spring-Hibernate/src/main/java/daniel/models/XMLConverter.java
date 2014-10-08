package daniel.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
 
	public Marshaller getMarshaller() {
		return marshaller;
	}
 
	@Autowired
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
 
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}
 
	@Autowired
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
  
	public Object convertFromXMLToObject(InputStream is) throws IOException {
 
		try {
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
 
}
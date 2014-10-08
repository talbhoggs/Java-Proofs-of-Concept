package daniel.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import daniel.models.*;

import daniel.models.Offer;
import daniel.models.OffersDAO;
import daniel.service.OffersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;


@Controller
public class OffersController {
	
	private OffersDAO od;
	private OffersService sod;
	public ApplicationContext appContext;
	public XMLConverter xmlc;
	
	@Autowired
	public void setXMLConverter(XMLConverter xmlcon) {
		this.xmlc = xmlcon;
	}
	
	@Autowired
	public void setApplicationContext(ApplicationContext app) {
		this.appContext = app;
	}
	
	@Autowired
	public void setOffersService(OffersService sod) {
		this.sod = sod;
	}
	
	@Autowired
	public void setOd(OffersDAO od) {
		this.od = od;
		System.out.println("GOT THE DAO IN THE CONTROLLER!");
		List<Offer> offersFromDB = od.getOffers();
		for(Offer offer:offersFromDB) {
			System.out.println(offer.toString());
		}
		
		List<Offer> hlist = od.getOffersHibernate();
		
		System.out.println("!HIBERNATE!");
		for(Offer offer:hlist) {
			System.out.println(offer.toString());
		}
		System.out.println("!HIBERNATE!");
		
	}

	@RequestMapping("/")
	public ModelAndView showHome(Model mymodel, HttpSession session) {
		
		int i = 0;
		
		session.setAttribute("sessionKey", "This comes from the session");
		
		String jsonPrettyPrintString = "";
		
		ModelAndView mv = new ModelAndView("offers");
		
		Map<String, Object> model = mv.getModel();
		
		model.put("name", "Good");
		
		System.out.println("******");
		System.out.println("url invoked");
		System.out.println("******");
		
		mymodel.addAttribute("name2", "Love");
		
		List<Offer> myResult = sod.getCurrent();
		
		System.out.println(myResult.size());
		
		System.out.println(this.appContext);
		
		Resource resource = appContext.getResource("classpath:daniel/config/test.xml");
		 
		    try{
		     	  InputStream is = resource.getInputStream();
		   
		          BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		          String line;
		          String xmlObj = "";
		          while ((line = br.readLine()) != null) {
		            // System.out.println(line);
		        	  xmlObj = xmlObj + line;
		       	  } 
		          br.close();
		          
		          InputStream is2 = resource.getInputStream();
		     	  
		     	 Customer customer = (Customer)this.xmlc.convertFromXMLToObject(is2);
		     	 
		 		System.out.println(customer.name);
		 		System.out.println(customer.age);
		 		System.out.println(customer.flag);
		 		System.out.println(customer.address);
		 		System.out.println("Zoom: " + customer.zoom);
		 		
		 			while( i < customer.purchases.purchaseList.size()) {
		 				System.out.println(customer.purchases.purchaseList.get(i).description);
		 				i++;
		 			}
		 			
		 			JSONObject xmlJSONObj = XML.toJSONObject(xmlObj);
		 			jsonPrettyPrintString = xmlJSONObj.toString(0);
		 			System.out.println(jsonPrettyPrintString);

		    	}catch(IOException e){
		    		e.printStackTrace();
		    	}
		
		mymodel.addAttribute("ores", myResult);
		mymodel.addAttribute("json", jsonPrettyPrintString);
		
		//return "offers";
		return mv;
		
	}
	
	@RequestMapping("/home")
	public Object showHomePage(Model mymodel, HttpSession session, HttpServletResponse response) {
		
		session.setAttribute("sessionKey", "This comes from the session");
		
		//ModelAndView mv = new ModelAndView("home");
		
		//return "home";
		//return mv;
		response.setContentType("application/json");
		
		try {
			PrintWriter writer = response.getWriter();
			writer.println("Hello Daniel, please be good.");
		}
		catch(Exception e) {
			System.out.println("Unable to get writer");
		}
		
		
		  //MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();

	        //MediaType jsonMimeType = MediaType.APPLICATION_JSON;

	        //try {
	          //  jsonConverter.write(mymodel, jsonMimeType, new ServletServerHttpResponse(response));
	        //} catch (HttpMessageNotWritableException e) {
	          //  e.printStackTrace();
	        //} catch (IOException e) {
	          //  e.printStackTrace();
	        //}

	        return null;
		
	}
	
}
	

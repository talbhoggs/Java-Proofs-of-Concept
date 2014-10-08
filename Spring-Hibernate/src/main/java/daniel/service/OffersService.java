package daniel.service;

import daniel.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("offersService")
public class OffersService {
	
	private OffersDAO oDao;

	@Autowired
	public void setoDao(OffersDAO oDao) {
		this.oDao = oDao;
	}
	
	public List<Offer> getCurrent() {
		return oDao.getOffers();
	}
	

}

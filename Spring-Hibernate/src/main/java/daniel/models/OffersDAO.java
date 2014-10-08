package daniel.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("offersDao")
public class OffersDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public OffersDAO() {
		System.out.println("*************** OfferDao intialized *************");
	}
	
	@Autowired
	public void setJdbc(DataSource jdbcDataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbcDataSource);
	}



	public List<Offer> getOffers() {
		
		RowMapper<Offer> rm = new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer rowOffer = new Offer();
				
				rowOffer.setId(rs.getInt("id"));
				rowOffer.setName(rs.getString("name"));
				rowOffer.setEmail(rs.getString("email"));
				rowOffer.setContent(rs.getString("content"));
				
				return rowOffer;

			}
			
		};
		
		List<Offer> results = jdbc.query("select * from offers", rm);
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Offer> getOffersHibernate() {
		
		return this.getSession().createQuery("from Offer").list();
		
	}

}


package com.sightlyinc.ratecred.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sightlyinc.ratecred.model.PlaceCityState;
import com.sightlyinc.ratecred.model.Rater;
import com.sightlyinc.ratecred.model.RaterMetrics;

public class HibernateRaterMetricsDao 
	extends HibernateDaoSupport 
	implements RaterMetricsDao {

	static Logger logger = Logger.getLogger(HibernateRaterMetricsDao.class);
    
    public HibernateTemplate getHibernateTemplateOverride() {
        HibernateTemplate template = getHibernateTemplate();
        template.setFlushMode(HibernateTemplate.FLUSH_AUTO);
        return template;
    }

	@Override
	public RaterMetrics findByRater(final Rater t) {
		RaterMetrics result = (RaterMetrics)getHibernateTemplateOverride().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
				
				//native
				
				String sql = "SELECT " +
					"rater.id as id, " +
					"rater.username as username, " +
					"rater.status as status, " +
					"count(distinct(rating.id)) as ratings, " +
					"count(distinct(ca.rating_id)) as given, " +
					"count(distinct(cb.rating_id)) as received " +
				"FROM rater " +
				"LEFT JOIN  compliment ca on (rater.id = ca.rater_id) " +
				"LEFT JOIN  rating on (rater.id = rating.rater_id) " +
				"LEFT JOIN  compliment cb on (rating.id = cb.rating_id) " +
				"where rater.id=:id";
		
				
				Query query =  
					session.createSQLQuery(sql); 
				query.setLong("id", t.getId());
			    
				List oTatList = query.list();
				RaterMetrics tm = null;
				if(oTatList != null && oTatList.size()>0)
				{
					Object[] oTat = (Object[])oTatList.get(0);
					tm = new RaterMetrics();					
					tm.setId(Long.parseLong(oTat[0].toString()));
					tm.setUsername((String)oTat[1]);
					tm.setStatus((String)oTat[2]);
					tm.setRatings(Integer.parseInt(oTat[3].toString()));
					tm.setGiven(Integer.parseInt(oTat[4].toString()));
					tm.setReceived(Integer.parseInt(oTat[5].toString()));
				}
				
				return tm;
	
			}
		});
		return result;
	}

    
    
	/*@Override
	public List<PlaceCityState> findByRater(final Rater t) {
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
				
				
					Query query = 
						session.createSQLQuery("SELECT round(rand()*10000) as id, " +
								"place.city, place.state FROM rating,place,rater " +
								"where rating.place_id = place.id " +
								"and rating.rater_id = rater.id and rater.id = :id " +
								"group by place.city, place.state").addEntity(PlaceCityState.class);

					query.setLong("id", t.getId());
					List list = query.list();
						
					return list;
	
				}
		});
		
		return result;
	}

	public List<PlaceCityState> findAll() {
		
		
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
				//native
				String sql = "select t.ID as {tcs.id}, t.CITY as {tcs.city}," 
			           + " t.STATE as {tcs.state}" 
			           + " from place t group by {tcs.city}, {tcs.state} order by {tcs.state} asc, {tcs.city} asc"; 

				Query query =  
					session.createSQLQuery(sql)
					.addEntity("tcs", PlaceCityState.class); 
			    
			    return query.list();
	
			}
		});
		return result;
		
   }

	@Override
	*//**
	 * SELECT count(rating.id) as total_ratings, place.city, place.state FROM place,rating where place.id = rating.place_id 
group by place.city, place.state order by total_ratings desc

	 *//*
	public List<PlaceCityState> findMostRatedOrdered(final int pageNum, final int pageSize) {
		
		
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
				//native
				String sql = "select pE.ID as {tcs.id}, pE.CITY as {tcs.city}," +
			            " pE.STATE as {tcs.state}, count(tE.id) as tcount" + 
			            " from place pE, rating tE where pE.id = tE.place_id group " +
			           		"by {tcs.city}, {tcs.state} order by tcount desc"; 

				Query query =  
					session.createSQLQuery(sql)
					.addEntity("tcs", PlaceCityState.class); 
				
				query.setFirstResult(pageNum);
				query.setMaxResults(pageSize);
				return query.list();
			    
	
			}
		});
		return result;
	}
	*/
	
	
	
	
	
	
	
	  
}

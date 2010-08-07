
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

import com.sightlyinc.ratecred.model.AwardOffer;
import com.sightlyinc.ratecred.model.Business;

public class HibernateAwardOfferDao 
	extends HibernateDaoSupport 
	implements AwardOfferDao {

	static Logger logger = Logger.getLogger(HibernateAwardOfferDao.class);
    
  
	public HibernateTemplate getHibernateTemplateOverride() {
        HibernateTemplate template = getHibernateTemplate();
        template.setFlushMode(HibernateTemplate.FLUSH_AUTO);
        return template;
    }

	
	@Override
	public List<AwardOffer> findByBusiness(final Business b) {
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
	
				Query query = session.createQuery(
					"select entityimpl from "+AwardOffer.class.getName()+" as entityimpl " +
							"where entityimpl.business.id = :businessId");
				
				query.setLong("businessId", b.getId());
				
				List list = query.list();
				
	
				return list;
	
			}
		});
		return result;
	}
	
	
	
	@Override
	public void delete(AwardOffer entity) {
		getHibernateTemplateOverride().delete(entity);
		
	}
		

	@Override
	public List<AwardOffer> findByType(final String type) {
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
	
				Query query = session.createQuery(
					"select entityimpl from "+AwardOffer.class.getName()+" as entityimpl " +
							"where entityimpl.type = :type");
				
				query.setString("type", type);
				
				List list = query.list();
				
	
				return list;
	
			}
		});
		return result;
	}

	@Override
	public List<AwardOffer> findAll() {
		List result = getHibernateTemplateOverride().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException 
				{
	
				Query query = session.createQuery(
					"select entityimpl from "+AwardOffer.class.getName()+" as entityimpl");
				List list = query.list();
	
				return list;
	
			}
		});
		return result;
	}

	@Override
	public AwardOffer findByPrimaryKey(final Long id) {
		return (AwardOffer)getHibernateTemplateOverride().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException 
			{

				Query query = session.createQuery(
					"select distinct entityimpl from "+AwardOffer.class.getName()+
					" as entityimpl where entityimpl.id = :id");
				
				query.setLong("id", id);
				
				AwardOffer t = (AwardOffer)query.uniqueResult();
				
			
				return t;
	
			}
		});		
	}

	@Override
	public AwardOffer findByKeyname(final String kn) {
		return (AwardOffer)getHibernateTemplateOverride().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException 
			{

				Query query = session.createQuery(
					"select distinct entityimpl from "+AwardOffer.class.getName()+
					" as entityimpl where entityimpl.keyname = :kn");
				
				query.setString("kn", kn);
				
				AwardOffer t = (AwardOffer)query.uniqueResult();
				
			
				return t;
	
			}
		});		
	}

	@Override
	public void save(AwardOffer entity) {
		getHibernateTemplateOverride().save(entity);		
	}
    

	
	
	
	  
}

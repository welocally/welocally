package com.sightlyinc.ratecred.admin.mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noi.utility.spring.service.BLServiceException;
import com.sightlyinc.ratecred.authentication.UserPrincipalService;
import com.sightlyinc.ratecred.dao.SimpleGeoJsonTokenDao;
import com.sightlyinc.ratecred.model.Order;
import com.sightlyinc.ratecred.model.Publisher;
import com.sightlyinc.ratecred.model.SimpleGeoJsonToken;
import com.sightlyinc.ratecred.service.NetworkMemberService;
import com.sightlyinc.ratecred.service.OrderManagerService;
import com.sightlyinc.ratecred.service.PublisherService;

/**
 * @author sam
 * @version $Id$
 */
@Controller
@RequestMapping("/paypal")
public class PaypalNotificationController {

	static Logger logger = Logger.getLogger(PaypalNotificationController.class);
	
    @Autowired
    private PublisherService publisherService;
    
    @Autowired
    private SimpleGeoJsonTokenDao simpleGeoJsonTokenDao;

	@Autowired
	OrderManagerService orderManagerService;
	
	@Value("${paypal.merchant.email:clay@ratecred.com}")
	private String merchantEmail;
	
	@Value("${paypal.subscription.beta.sku:3237bd9b244e}")
	private String betaSKU;

	//lame way to do this
	@Value("${paypal.subscription:5.99}")
	private String subscriptionAmount;
	
	/**
	 * Handles requests sent by the Wordpress plugin when users publish a post.
	 * Sends blog, post, place and SimpleGeo feature info in a JSON object.
	 * 
	 */
	@RequestMapping(value = "/notify")
	@ResponseBody
	public ModelAndView notify(Model m, HttpServletRequest request)
			throws JSONException {
		ModelAndView modelAndView = new ModelAndView("paypal/response");

		logger.debug("got notification");
		try {
			// read post from PayPal system and add 'cmd'
			Enumeration en = request.getParameterNames();
			String str = "cmd=_notify-validate";
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				String paramValue = request.getParameter(paramName);
				str = str + "&" + paramName + "="
						+ URLEncoder.encode(paramValue, "utf-8");
			}

			// post back to PayPal system to validate
			// NOTE: change http: to https: in the following URL to verify using
			// SSL (for increased security).
			// using HTTPS requires either Java 1.4 or greater, or Java Secure
			// Socket Extension (JSSE)
			// and configured for older versions.
			URL u = new URL("https://www.paypal.com/cgi-bin/webscr");
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			PrintWriter pw = new PrintWriter(uc.getOutputStream());
			pw.println(str);
			pw.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(uc
					.getInputStream()));
			String res = in.readLine();
			in.close();

			// assign posted variables to local variables
			String itemName = request.getParameter("item_name");
			String itemNumber = request.getParameter("item_number");
			String paymentStatus = request.getParameter("payment_status");
			String paymentAmount = request.getParameter("mc_gross");
			String paymentCurrency = request.getParameter("mc_currency");
			String txnId = request.getParameter("txn_id");
			String receiverEmail = request.getParameter("receiver_email");
			String payerEmail = request.getParameter("payer_email");
			String publisherKey = request.getParameter("custom");
			// check notification validation
			
			logger.debug("res:"+res);
			
			if (res.equals("VERIFIED")) {
								
				// check that paymentStatus=Completed
				// check that txnId has not been previously processed
				Order  o = this.orderManagerService.findOrderByTxId(txnId);
				if(o == null 
						&& merchantEmail.equalsIgnoreCase(receiverEmail)
						&& Float.valueOf(paymentAmount).equals(Float.valueOf(subscriptionAmount)))
				{
									
					// process payment
					 logger.debug("new order:"+o.getExternalTxId());
					 o = new Order();
					 o.setExternalTxId(txnId);
					 o.setStatus(paymentStatus);
					 o.setPrice(Float.valueOf(paymentAmount));
					 o.setExternalOrderItemCode(itemNumber);
					 o.setSku(itemNumber);
					 o.setBuyerEmail(payerEmail);
					 o.setQuantity(1);
					 o.setBuyerName(publisherKey);
					 				 
					 //complete subscription
					 if(paymentStatus.equalsIgnoreCase("Completed")){
						logger.debug("processing order:"+o.getExternalTxId());
						//find the publisher by custom field
						Publisher publisher = 
							publisherService.findByNetworkKeyAndPublisherKey("welocally", publisherKey);
						
						if(publisher != null)
							processPublisherOrder(publisher, o);
						 
					 }
					 
					 orderManagerService.saveOrder(o);
				}
								
				
			} else if (res.equals("INVALID")) {
				logger.error("INVALID TRANSACTION!");
			} else {
				logger.error("NO ACTION TAKEN!");
			}
		} catch (MalformedURLException e) {
			logger.error("problem", e);
		} catch (IOException e) {
			logger.error("problem", e);
		} catch (BLServiceException e) {
			logger.error("problem", e);
		}

		return modelAndView;
	}
	
	
	private void processPublisherOrder(Publisher publisher, Order o){
		
		logger.debug("processPublisherOrder order:"+o.getExternalTxId()+" key:"+publisher.getKey());
		      
        long serviceEndDateMillis = new Date().getTime();
        if(o.getSku().equals(betaSKU)) {
	        //BETA gets a 6 months trail
	        serviceEndDateMillis += (2592000000L*6);
        } else {
        	serviceEndDateMillis += (2592000000L*1);
        }
        
        publisher.setServiceEndDateMillis(serviceEndDateMillis);
        SimpleGeoJsonToken simpleGeoJsonToken = simpleGeoJsonTokenDao.getCurrentToken();
        if (simpleGeoJsonToken != null) {
            publisher.setSimpleGeoJsonToken(simpleGeoJsonToken.getJsonToken());
        } else {
            //errors.add("Unable to assign a SimpleGeo JSON token, please try again");
        }
        
        //enable the user
        publisher.getUserPrincipal().setEnabled(true);
        
        
        publisherService.save(publisher);
	}
	

}

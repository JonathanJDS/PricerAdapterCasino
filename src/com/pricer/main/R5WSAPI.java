package com.pricer.main;


import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import se.pricer._interface.public_5_0.PricerPublicAPI50;
import se.pricer._interface.public_5_0.PricerPublicAPI50_Service;


public class R5WSAPI  {
	

	PricerPublicAPI50 pricerInterfaceR5 = null;
	
	
	
	/*http://localhost:11097/pricer_5_0?wsdl */
	

	public PricerPublicAPI50 getPricerInterfaceR5() {
		return pricerInterfaceR5;
	}

	public void setPricerInterfaceR5(PricerPublicAPI50 pricerInterfaceR5) {
		this.pricerInterfaceR5 = pricerInterfaceR5;
	}

	public R5WSAPI(String API_USER, String API_KEY, String API_HOST, String API_PORT) {
		super();

		try {
			try {
				this.pricerInterfaceR5 = getR5WSAPI(API_USER, API_KEY, API_HOST, API_PORT);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {

			System.out.println("API Connection Exception ==> cause :" + e.getMessage() + "(" + e.getCause() + ")" );
				
				
			}
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("API Connection Exception ==> cause :" + e1.getMessage() + "(" + e1.getCause() + ")" );
			
			
		}

		// TODO Auto-generated constructor stub
	}

	private PricerPublicAPI50 getR5WSAPI(final String user, final String key, final String host, final String port)
			throws NotBoundException, MalformedURLException, RemoteException, NoSuchAlgorithmException {
		
		 String WS_URL = "http://"+host+":"+port+"/pricer_5_0?wsdl";
		 String AUTHENTICATION_TOKEN_HEADER = "authentication-token";
		 String USERNAME_HEADER = "username";

		 PricerPublicAPI50_Service service = new PricerPublicAPI50_Service(new URL(WS_URL), new QName("http://public_5_0.interface.pricer.se/", "PricerPublicAPI_5_0"));
		   PricerPublicAPI50 api = service.getPricerPublicAPI50Port();
		   
		   
	      String challenge = api.getLoginChallenge(user);
	      //challenge = "Km1cbztc0qCd9Na7G9q9UM5nQNA9arby";
	      System.err.println("challenge = " + challenge);

	      MessageDigest digest = MessageDigest.getInstance("SHA-256");
	      digest.update(challenge.getBytes(StandardCharsets.UTF_8));
	      digest.update(key.getBytes(StandardCharsets.UTF_8));
	      String hash = Base64.getEncoder().encodeToString(digest.digest());

	      System.err.println("hash=" + hash);
	      
	      Map<String, Object> req_ctx = ((BindingProvider)api).getRequestContext();
	      req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

	      Map<String, List<String>> headers = new HashMap<>();
	      headers.put(USERNAME_HEADER, Collections.singletonList(user));
	      System.out.println("toto : " +Collections.singletonList(user));
	      headers.put(AUTHENTICATION_TOKEN_HEADER, Collections.singletonList(hash));
	      System.out.println("password is : "+Collections.singletonList(hash));
	      req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

	     
	    
		
		return api;

	}

}

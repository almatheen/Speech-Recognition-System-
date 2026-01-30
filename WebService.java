package com.example.speech_rec;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebService {

	private static String NAMESPACE = "http://web.com/";
	private static String URL = "http://192.168.43.114:8084/Speech_Recognization/NewWebService?wsdl";
	private static String SOAP_ACTION = "http://web.com/";

	public static String register(String name, String pass, String email,
			String mobile, String age, String webMethName) {
		 String resTxt = null;

		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		request.addProperty("name", name);
		request.addProperty("pass", pass);
		request.addProperty("email", email);
		request.addProperty("mobile", mobile);
		request.addProperty("age",age);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		System.out.println(webMethName);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return resTxt;
	}
	
	public static String login(String email, String pass, String webMethName) {
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		request.addProperty("email", email);
		request.addProperty("pass", pass);

		System.out.println(webMethName);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			System.out.println(resTxt+":Login");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resTxt;

	}
	public static String find_doctors(String lat,String lang,String webMethName)
	{
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE , webMethName);
		request.addProperty("lat",lat);
		request.addProperty("lang",lang);
		System.out.println(webMethName);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(resTxt);
		return resTxt;
	
	}
	
	public static String doc_details(String id,String webMethName)
	{
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE , webMethName);
		request.addProperty("id",id);
	
		System.out.println(webMethName);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(resTxt);
		return resTxt;
	
	}
	public static String description(String des, String timee,String did,String uid, String name,String webMethName) {
		 String resTxt = null;

		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		request.addProperty("des", des);
		request.addProperty("timee", timee);
		request.addProperty("did", did);
		request.addProperty("uid", uid);
		request.addProperty("name",name);
		


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		System.out.println(webMethName);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			System.out.println(resTxt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return resTxt;
	}
	public static String apt_details(String id,String uid,String webMethName)
	{
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE , webMethName);
		request.addProperty("id",id);
		
		request.addProperty("uid", uid);
	
		System.out.println(webMethName);
		System.out.println(id+":did in web");
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(resTxt);
		return resTxt;
	
	}
	public static String find_doctors1(String uid,String webMethName)
	{
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE , webMethName);
		request.addProperty("uid",uid);
		
		System.out.println(webMethName);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(resTxt);
		return resTxt;
	
	}
	public static String cancel_apt(String id,String uid,String webMethName)
	{String resTxt = null;
	SoapObject request = new SoapObject(NAMESPACE , webMethName);
	request.addProperty("uid",uid);
	request.addProperty("id",id);
	
	System.out.println(webMethName);
	
	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(request);
	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	
	try {
		androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
		SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
		resTxt = response.toString();
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println(resTxt);
	return resTxt;
		
		
	}
	public static String prescription(String id,String uid,String webMethName)
	{
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE , webMethName);
		request.addProperty("id",id);
		
		request.addProperty("uid", uid);
	
		System.out.println(webMethName);
		System.out.println(id+":did in web");
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(resTxt);
		return resTxt;
	
	}
	public static String comment(String cmt, String did,String uid, String name,String webMethName) {
		 String resTxt = null;

		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		request.addProperty("cmt", cmt);
		
		request.addProperty("did", did);
		request.addProperty("uid", uid);
		request.addProperty("name",name);
		


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		System.out.println(webMethName);
		
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
			System.out.println(resTxt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resTxt;
	}

	
}

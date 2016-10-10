package com.hukuk.mobileapp.webservice;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.hukuk.mobileapp.entity.GetMattersResponse;
import com.hukuk.mobileapp.entity.IpModel;
import com.hukuk.mobileapp.entity.Location;
import com.hukuk.mobileapp.entity.Matter;


public class WebService {
	private static final String SOAP_ACTIONHSCLIENT = "http://tempuri.org/IHsClient/";
	private static final String SOAP_ACTIONIPCLIENT = "http://tempuri.org/IIpManager/";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String HsClient = "http://192.168.1.39/HukukWsApp/HsClient.svc/";
	private static final String IpClient ="http://192.168.1.39/IpManagerApp/IpManagerWs.svc/";
	private boolean isResultVector = false;
	private static WebService _wb=null;
	
	public static WebService getInstance()
	{
		if(_wb==null)
			return new WebService();
		return _wb;
	}

	public WebService() {
		super();
	}

	public void Test() throws Exception {
		final String sSetValue = "GetSumOfTwoInts";
		// Create the outgoing message
		final SoapObject requestObject =	new SoapObject(NAMESPACE, sSetValue);
		// Set Parameter type String
		//requestObject.addProperty(svalue, param);
		// Create soap envelop .use version 1.1 of soap
		PropertyInfo pi = new PropertyInfo();
		pi.setName("Operand1");
		pi.setValue(2);
		pi.setType(int.class);
		requestObject.addProperty(pi);
		PropertyInfo pi2 = new PropertyInfo();
		pi2.setName("Operand2");
		pi2.setValue(5);
		pi2.setType(int.class);
		requestObject.addProperty(pi2);
		final SoapSerializationEnvelope envelope =
				new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
		envelope.dotNet = true;
		// add the outgoing object as the request
		envelope.setOutputSoapObject(requestObject);
		//call and Parse Result.
		final Object response = this.call(SOAP_ACTIONHSCLIENT + sSetValue,HsClient, envelope);

	}
	protected Object call(String soapAction,String URL, SoapSerializationEnvelope envelope) throws Exception {
		Object result = null;

		final HttpTransportSE transportSE = new HttpTransportSE(URL);

		transportSE.debug = false;

		// call and Parse Result.
		try {
			transportSE.call(soapAction, envelope);
			if (!isResultVector) {
				result = envelope.getResponse();
			} else {
				result = envelope.bodyIn;
			}
		} catch (final Exception e) {
			throw e;
		}
		return result;
	}
	
	public IpModel Login(String sirketkodu,String userId,String password) throws Exception
	{
		final String sSetValue = "GetIpContract";
		// Create the outgoing message
		final SoapObject requestObject = 
			new SoapObject(NAMESPACE, sSetValue);
		// Set Parameter type String
		//requestObject.addProperty(svalue, param);
		 PropertyInfo pi = new PropertyInfo();
	     pi.setName("userId");
	     pi.setValue(userId);
	     pi.setType(String.class);
	     requestObject.addProperty(pi);
	     pi = new PropertyInfo();
	     pi.setName("sirketkodu");
	     pi.setValue(sirketkodu);
	     pi.setType(String.class);
	     requestObject.addProperty(pi);
	     pi = new PropertyInfo();
	     pi.setName("password");
	     pi.setValue(password);
	     pi.setType(String.class);
	     requestObject.addProperty(pi);
		// Create soap envelop .use version 1.1 of soap
		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		// add the outgoing object as the request
		envelope.setOutputSoapObject(requestObject);

		IpModel result = null;
		 //call and Parse Result.
		final Object response = this.call(SOAP_ACTIONIPCLIENT + sSetValue,IpClient, envelope);
		
		if (response != null)
		{
			try
			{
				if (response != null)
				{
				result = new IpModel((SoapObject)response);
				}
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	public GetMattersResponse GetMatters(String dbName,int reporttype) throws Exception {

		final String sSetValue = "GetMatters";
		// Create the outgoing message
		final SoapObject requestObject =new SoapObject(NAMESPACE, sSetValue);
		// Set Parameter type String
		//requestObject.addProperty(svalue, param);
		PropertyInfo pi = new PropertyInfo();
		pi.setName("dbname");
		pi.setValue(dbName);
		pi.setType(String.class);
		requestObject.addProperty(pi);
		pi = new PropertyInfo();
		pi.setName("reporttype");
		pi.setValue(reporttype);
		pi.setType(int.class);
		requestObject.addProperty(pi);
		// Create soap envelop .use version 1.1 of soap
		final SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		// add the outgoing object as the request
		envelope.setOutputSoapObject(requestObject);

		List<Matter> result = new ArrayList<>();
		GetMattersResponse responsemodel= new GetMattersResponse();
		//call and Parse Result.
		final Object response = this.call(SOAP_ACTIONHSCLIENT + sSetValue,HsClient, envelope);


			try
			{
				if (response != null)
				{
					responsemodel= new GetMattersResponse((SoapObject)response);
					SoapObject matterlist = (SoapObject)((SoapObject)response).getProperty(0);


					for(int i = 0; i < matterlist.getPropertyCount(); i++)
					{
						if(((SoapObject)matterlist).getProperty(i) != null)
							result.add(new Matter((SoapObject)((SoapObject)matterlist).getProperty(i)));
					}
					responsemodel.setMatters(result);
				}
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}

		return responsemodel;
	}
	public void SendUserLocationInfo(Location location) throws Exception
	{
		final String sSetValue = "SendUserLocationInfo";
		// Create the outgoing message
		final SoapObject requestObject = 
			new SoapObject(NAMESPACE, sSetValue);
		// Set Parameter type String
		//requestObject.addProperty(svalue, param);
		 PropertyInfo pi = new PropertyInfo();
	     pi.setName("location");
	     pi.setValue(location);
	     pi.setType(location.getClass());
	     requestObject.addProperty(pi);
		// Create soap envelop .use version 1.1 of soap
		final SoapSerializationEnvelope envelope = 
			new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// add the outgoing object as the request
		envelope.setOutputSoapObject(requestObject);
		envelope.addMapping(NAMESPACE, 
				Location.USER_CLASS.getSimpleName(),
				Location.USER_CLASS);
		// Register Marshaler
		// For date marshaling
		Marshal dateMarshal = new MarshalDate();
		dateMarshal.register(envelope);
		// For float marshaling
		Marshal floatMarshal = new MarshalFloat();
		floatMarshal.register(envelope);
		//Response result = null;
		 //call and Parse Result.
		final Object response = this.call(SOAP_ACTIONHSCLIENT + sSetValue,HsClient, envelope);
		
		if (response != null)
		{
			try
			{
				if (response != null)
				{
			//		result = new Response((SoapObject) response);
				}
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//return result;
	}

}


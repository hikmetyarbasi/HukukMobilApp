package com.hukuk.mobileapp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class Location implements KvmSerializable
{
	public static Class USER_CLASS = Location.class;
	private String _imeiNo; 
	private double _latitude;
	private double _longitude;
	private Date _date;
	private String _description;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public Location(){}
	public Location(String imeiNo,double longitude,double latitude,Date date,String description)
	{
		this._imeiNo=imeiNo;
		this._longitude=longitude;
		this._latitude=latitude;
		this._date=date;
		this._description=description;
	}
	public Location(SoapObject _soapObject) throws ParseException
	{
		this._imeiNo=GetValue(_soapObject,"ImeiNo");
		this._longitude=Double.parseDouble(GetValue(_soapObject,"Longitude"));
		this._latitude=Double.parseDouble(GetValue(_soapObject,"Latitude"));
		this._date=formatter.parse(GetValue(_soapObject,"Date").replace("T", " "));
		this._description=GetValue(_soapObject,"Description");
	}
	public String GetValue(SoapObject object, String Field) 
	{
		try 
		{
			return object.getProperty(Field).toString();
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	
	public String GetImeiNo()
	{
		return _imeiNo;
	}
	
	public void SetImeiNo(String _imeiNo)
	{
		this._imeiNo=_imeiNo;
	}
	public double GetLongitude()
	{
		return _longitude;
	}
	public void SetLongitude(int _longitude)
	{
		this._longitude=_longitude;
	}
	public double GetLatitude()
	{
		return _latitude;
	}
	public void SetLatitude(int _latitude)
	{
		this._latitude=_latitude;
	}
	public Date GetDate()
	{
		return _date;
	}
	public void SetDate(Date _date)
	{
		this._date=_date;
	}
	public String GetDescription()
	{
		return _description;
	}
	public void SetDescription(String _description)
	{
		this._description=_description;
	}
	
	@Override
	public Object getProperty(int index) {
		// TODO Auto-generated method stub
		Object object = null;
		switch (index)
		{
		case 0:
		{
			object = this._imeiNo;
			break;
		}
		case 1:
		{
			object = this._latitude;
			break;
		}
		case 2:
		{
			object = this._longitude;
			break;
		}
		case 3:
		{
			object = this._date;
			break;
		}
		case 4:
		{
			object = this._description;
			break;
		}
		}
		return object;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo propertyInfo) {
		// TODO Auto-generated method stub
		switch (index)
		{
		case 0:
		{
			propertyInfo.name = "ImeiNo";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		case 1:
		{
			propertyInfo.name = "Latitude";
			propertyInfo.type = PropertyInfo.OBJECT_CLASS;
			break;
		}
		case 2:
		{
			propertyInfo.name = "Longitude";
			propertyInfo.type = PropertyInfo.OBJECT_CLASS;
			break;
		}
		case 3:
		{
			propertyInfo.name = "Date";
			propertyInfo.type = PropertyInfo.OBJECT_CLASS;
			break;
		}
		case 4:
		{
			propertyInfo.name = "Description";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		}
	}
	@Override
	public void setProperty(int index, Object obj) {
		// TODO Auto-generated method stub
		switch (index)
		{
		case 0:
		{
			this._imeiNo = obj.toString();
			break;
		}
		case 1:
		{
			this._latitude = Double.parseDouble(obj.toString());
			break;
		}
		case 2:
		{
			this._longitude = Double.parseDouble(obj.toString());
			break;
		}
		case 3:
		{
			try {
				this._date = formatter.parse(obj.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 4:
		{
			this._description = obj.toString();
			break;
		}
	}
}
	}


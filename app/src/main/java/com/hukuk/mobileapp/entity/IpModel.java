package com.hukuk.mobileapp.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class IpModel implements KvmSerializable 
{
	public static Class IPMODEL_CLASS = IpModel.class;
	private String _ip ;
	private String _dbName;
	private String _sirketKodu ;
	private String _statu ;
	private String _operation;
     private String _message;
     
	public IpModel() {
	}

	public IpModel(SoapObject _response) 
	{
		this._ip=GetValue(_response,"_x003C_Ip_x003E_k__BackingField");
		this._dbName=GetValue(_response,"_x003C_DbName_x003E_k__BackingField");
		this._sirketKodu=GetValue(_response,"_x003C_SirketKodu_x003E_k__BackingField");
		this._statu=GetValue(_response,"_x003C_Statu_x003E_k__BackingField");
		this._operation=GetValue(_response,"_x003C_Operation_x003E_k__BackingField");
		this._message=GetValue(_response,"_x003C_Message_x003E_k__BackingField");
	}
	public IpModel(String ip,String dbname,String sirketkodu,String statu,String operation,String message)
	{
		this._ip=ip;
		this._dbName=dbname;
		this._sirketKodu=sirketkodu;
		this._statu=statu;
		this._operation=operation;
		this._message=message;
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

	public String GetIp() {
		return _ip;
	}

	public String GetDbName() {
		return _dbName;
	}

	public String GetSirketKodu() {
		return _sirketKodu;
	}

	public String GetStatu() {
		return _statu;
	}

	public String GetOperation() {
		return _operation;
	}
	public String GetMessage() {
		return _message;
	}

	public Object getProperty(int index) {
		// TODO Auto-generated method stub
		Object object = null;
		switch (index)
		{
		case 0:
		{
			object = this._ip;
			break;
		}
		case 1:
		{
			object = this._dbName;
			break;
		}
		case 2:
		{
			object = this._sirketKodu;
			break;
		}
		case 3:
		{
			object = this._statu;
			break;
		}
		case 4:
		{
			object = this._operation;
			break;
		}
		case 5:
		{
			object = this._message;
			break;
		}
		}
		return object;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo propertyInfo) {
		// TODO Auto-generated method stub
		switch (index)
		{
		case 0:
		{
			propertyInfo.name = "Ip";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		case 1:
		{
			propertyInfo.name = "DbName";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		case 2:
		{
			propertyInfo.name = "SirketKodu";
			propertyInfo.type = PropertyInfo.INTEGER_CLASS;
			break;
		}
		case 3:
		{
			propertyInfo.name = "Statu";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		case 4:
		{
			propertyInfo.name = "Operation";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		case 5:
		{
			propertyInfo.name = "Message";
			propertyInfo.type = PropertyInfo.STRING_CLASS;
			break;
		}
		}
	}

	public void setProperty(int index, Object obj) {
		// TODO Auto-generated method stub
		switch (index)
		{
		case 0:
		{
			this._ip = obj.toString();
			break;
		}
		case 1:
		{
			this._dbName = obj.toString();
			break;
		}
		case 2:
		{
			this._sirketKodu = obj.toString();
			break;
		}
		case 3:
		{
			this._statu = obj.toString();
			break;
		}
		case 4:
		{
			this._operation = obj.toString();
			break;
		}
		case 5:
		{
			this._message = obj.toString();
			break;
		}
		}
	}//Window->Preferences->Team->SVN->SVN Connector.

}

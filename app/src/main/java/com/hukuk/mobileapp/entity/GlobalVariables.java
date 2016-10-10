package com.hukuk.mobileapp.entity;

import java.sql.Time;

public class GlobalVariables
{
	private static GlobalVariables _instance=null;
	
	public static GlobalVariables GetInstance()
	{
		if(_instance==null)
			return _instance=new GlobalVariables();
		return _instance;
	}
	public IpModel ipmodel;
	public String DbName;
	public TimeEntry timeEntry;
	public String StartDate;
	public String EndDate;
	public String MatterId;
	public int ReportType;
	
}

package com.xioq.dasacumen.web.systemadmin;

import java.util.List;

import com.xioq.dasacumen.model.systemadmin.UserData;

public class UserDataForm
{
	private List<UserData> fullList;

	public List<UserData> getFullList()
	{
		return fullList;
	}

	public void setFullList(List<UserData> fullList)
	{
		this.fullList = fullList;
	}
}

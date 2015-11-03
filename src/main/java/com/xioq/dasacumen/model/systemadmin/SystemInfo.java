package com.xioq.dasacumen.model.systemadmin;

// Generated 06-May-2014 10:03:55 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_info")
public class SystemInfo implements java.io.Serializable {
	
	@Id
	@Column(name="property", nullable=false)
	private String property;
	
	private String valStr;
	private Date valDate;
	private Integer valNum;

	public SystemInfo() {
	}

	public SystemInfo(String property) {
		this.property = property;
	}

	public SystemInfo(String property, String valStr, Date valDate,
			Integer valNum) {
		this.property = property;
		this.valStr = valStr;
		this.valDate = valDate;
		this.valNum = valNum;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValStr() {
		return this.valStr;
	}

	public void setValStr(String valStr) {
		this.valStr = valStr;
	}

	public Date getValDate() {
		return this.valDate;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}

	public Integer getValNum() {
		return this.valNum;
	}

	public void setValNum(Integer valNum) {
		this.valNum = valNum;
	}

}

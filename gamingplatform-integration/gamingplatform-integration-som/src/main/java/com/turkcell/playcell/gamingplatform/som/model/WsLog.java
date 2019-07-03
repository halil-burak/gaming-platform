package com.turkcell.playcell.gamingplatform.som.model;

import javax.xml.bind.annotation.XmlTransient;

import lombok.ToString;

@ToString
public abstract class WsLog {
	
	private Long auditId;

	public abstract String getLoggable();

	public abstract String getTransactionId();

	public Boolean isError(){
		return null;
	}

	@XmlTransient
	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

}

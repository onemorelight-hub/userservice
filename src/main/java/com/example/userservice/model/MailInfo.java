package com.example.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
public class MailInfo {
	
	private String subject;
	private String[] receiverList;
	private String[] receiverInCc;
	private String[] receiverInBcc;
	private String receiverName;
	private String message;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getReceiverInCc() {
		return receiverInCc;
	}
	public void setReceiverInCc(String[] receiverInCc) {
		this.receiverInCc = receiverInCc;
	}
	public String[] getReceiverInBcc() {
		return receiverInBcc;
	}
	public void setReceiverInBcc(String[] receiverInBcc) {
		this.receiverInBcc = receiverInBcc;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String[] receiverList) {
		this.receiverList = receiverList;
	}
}

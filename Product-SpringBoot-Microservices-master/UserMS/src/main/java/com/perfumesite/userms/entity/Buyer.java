package com.perfumesite.userms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="buyers")
public class Buyer
{
	@Id
	@Column(name="`buyer id`")
	int buyerId;
	String name;
	@Column(name="email")
	String emailId;
	@Column(name="`phone number`")
	long phoneNumber;
	String password;
	@Column(name="`creed member` ")
	boolean creedMember;
	@Column(name="`reward points`")
	int rewardPoints;
	int credit;
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setPhoneNumber(long l) {
		this.phoneNumber = l;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*------TRY IsCreedMember() method--------*/
	
	public boolean getCreedMember() {
		return creedMember;
	}
	public void setCreedMember(boolean creedMember) {
		this.creedMember = creedMember;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}

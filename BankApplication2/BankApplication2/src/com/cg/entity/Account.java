package com.cg.entity;

public class Account {

	private int accNum;
	private int amount;
	private int balance;
	
	public int getAccNum() {
		return accNum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accNum;
		result = prime * result + amount;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accNum != other.accNum)
			return false;
		if (amount != other.amount)
			return false;
		return true;
	}
	
	
}

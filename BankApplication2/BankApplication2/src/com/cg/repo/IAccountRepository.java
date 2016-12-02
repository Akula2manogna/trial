package com.cg.repo;

import com.cg.entity.Account;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InvalidDepositedAmount;

public interface IAccountRepository {
	
     boolean save(Account account);
	Account searchAccount(int accountNumber);
	 public int withDrawAmt(int amt)throws InsufficientBalanceException;
     public int depositAmt(int amt) throws InvalidDepositedAmount;
}

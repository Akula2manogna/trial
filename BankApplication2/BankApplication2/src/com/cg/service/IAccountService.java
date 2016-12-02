package com.cg.service;

import com.cg.entity.Account;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InsufficientInitialBalanceException;
import com.cg.exception.InvalidAccountNumber;
import com.cg.exception.InvalidDepositedAmount;

public interface IAccountService {
	Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException;
	 public int withDrawAmt(int amt, int accNum) throws InsufficientBalanceException, InvalidAccountNumber;
     public int depositAmt(int amt, int accNum) throws InvalidAccountNumber, Exception;
     public boolean transfer_fund(int acc1, int acc2, int amt)
 			throws InsufficientBalanceException, InvalidAccountNumber;
     public int show_balance(int accno) throws InvalidAccountNumber ;
}

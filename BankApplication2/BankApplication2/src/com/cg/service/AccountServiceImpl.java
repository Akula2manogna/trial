package com.cg.service;

import com.cg.entity.Account;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InsufficientInitialBalanceException;
import com.cg.exception.InvalidAccountNumber;
import com.cg.repo.IAccountRepository;

public class AccountServiceImpl implements IAccountService{

	IAccountRepository  acctrepo;
	public AccountServiceImpl (IAccountRepository  acctrepo){
		this.acctrepo= acctrepo;
	}
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	@Override
	public Account createAccount(int accountNumber, int amount)
			throws InsufficientInitialBalanceException {
		if(amount<500){
			throw new InsufficientInitialBalanceException();
		}
		Account account = new Account();
		account.setAccNum(accountNumber);
		account.setAmount(amount);
		if(acctrepo.save(account))
		{
			return account;
		}
		return null;
	}
	@Override
	public int withDrawAmt(int amt, int accNum) throws InsufficientBalanceException, InvalidAccountNumber{
		
		if(Integer.toString(accNum).length()<4)
			throw new InvalidAccountNumber();
		  Account acct = acctrepo.searchAccount(accNum);
		if(acct !=null){
			int balance = acct.getBalance();
			if (amt>balance)
				throw new InsufficientBalanceException();
		
		acct.setBalance(balance-=amt);
		System.out.println("Amount withdrawn is" +amt);
		 System.out.println("Available balance is" +balance);
			return balance;
		}
		return 0;
	
	}
	@Override
	public int depositAmt(int amt, int accNum) throws InvalidAccountNumber, Exception{
		
	if(amt<=0)
	throw new Exception("Amount should be greater than zero");
	
	if(Integer.toString(accNum).length()<4)
	      throw new InvalidAccountNumber();
	Account acct = acctrepo.searchAccount(accNum);
		if(acct !=null){
	 int balance=acct.getBalance();		
	 acct.setBalance(balance +=amt);	
	System.out.println("Amount Deposited Successfully");
	System.out.println("Available balance is" +balance);
    return balance;
	}
		return 0;
}
	@Override
	public boolean transfer_fund(int acc1, int acc2, int amt)
			throws InsufficientBalanceException, InvalidAccountNumber {
		if(Integer.toString(acc1).length()<4)
			throw new InvalidAccountNumber();
		
		if(Integer.toString(acc2).length()<4)
			throw new InvalidAccountNumber();
		Account acct1 =acctrepo.searchAccount(acc1);
		Account acct2 =acctrepo.searchAccount(acc2);
		
		if((acct1!=null)&&(acct2!=null)){
			int balance1 = acct1.getBalance();
			int balance2 = acct2.getBalance();
			if(amt>balance1)
				throw new InsufficientBalanceException();
			
			acct1.setBalance(balance1-amt);
			acct2.setBalance(balance2+amt);
			
			if(balance1+balance2==acct1.getBalance()+acct2.getBalance())
				return true;
		}	
			
		return false;
	}
	@Override
	public int show_balance(int accno) throws InvalidAccountNumber {
		if(Integer.toString(accno).length()<4)
			throw new InvalidAccountNumber();
		
		Account acct = acctrepo.searchAccount(accno);
		
		if(acct!=null){
			int bal = acct.getBalance();
			return bal;
		}
		return 0;
	}
	
	
}


	
	
	
	



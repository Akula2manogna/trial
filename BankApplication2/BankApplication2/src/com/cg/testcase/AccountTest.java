package com.cg.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;




import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.entity.Account;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InsufficientInitialBalanceException;
import com.cg.exception.InvalidAccountNumber;
import com.cg.exception.InvalidDepositedAmount;
import com.cg.repo.IAccountRepository;
import com.cg.service.AccountServiceImpl;
import com.cg.service.IAccountService;

import static org.mockito.Mockito.when;

public class AccountTest {

	@Mock
	IAccountRepository acctrepo;
	IAccountService acctserv;
	Account acct;
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		acctserv = new AccountServiceImpl(acctrepo);
		acct = new Account();
		acct.setAccNum(1001);
		acct.setBalance(5000);
	}
	/* create account
	 * 1. when the amount is less than 500, system should throw exception
	 * 2. when the valid info is passed account should be created successfully.
	 * 
	 */
	
		@Test(expected=com.cg.exception.InsufficientInitialBalanceException.class)
		public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
		{
			acctserv.createAccount(101, 400);
		}
		
		@Test
		public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException
		{
			
			when(acctrepo.save(acct)).thenReturn(true);
			assertEquals(acct,acctserv.createAccount(101, 5000));
		}
		/*
		 * deposit
		 * 1. when amt is <= 0 then system should throw exception
		 * 2. when account number is invalid system should throw InvalidAccountNoException
		 * 3. when valid info is passed system should return new balance
		 */
		@Test(expected=com.cg.exception.InvalidDepositedAmount.class)
		public void whenAmountDepositedIsLessThenZeroSystemThrowException()throws InvalidDepositedAmount
		{
			acctserv.depositAmt(1004,0);
		}
		
		@Test(expected = com.cg.exception.InvalidAccountNumber.class)
		public void wheninvalidaccnoispassedsystemshouldthrowexception() throws InvalidAccountNumber,Exception {
			acctserv.depositAmt(52, 452);
		}
		
		@Test
		public void whenvalidinfoispassedsystemshouldreturnnewbalance() throws Exception {
			
			when(acctrepo.searchAccount((1004)).thenReturn(acct);
			assertEquals(7000, acctserv.depositAmt(1004, 2000));
			
		}
		/*
		 * withdraw
		 * 1. when amt > bal then system should throw InsufficientBalanceException
		 * 2. when account number is invalid system should throw InvalidAccountNoException
		 * 3. when valid info is passed system should return new balance
		 */
		@Test(expected=com.cg.exception.InsufficientBalanceException.class)
		public void whenAmountWithdrawnIsLessThenBalanceSystemThrowException()throws InsufficientBalanceException
		{
			when(acctrepo.searchAccount(1004)).thenReturn(acct);
			acctserv.withDrawAmt(1001,500)	;
		}
		
		@Test(expected = com.cg.exception.InvalidAccountNumber.class)
		public void wheninvalidaccnoispassedforwithdrawalsystemshouldthrowexception() throws InvalidAccountNumber,InsufficientBalanceException {
			acctserv.withDrawAmt(41, 2000);
		}
		
		@Test
		public void whenvalidinfoispassedforwithdrawsystemshouldreturnnewbalance() throws InsufficientBalanceException, InvalidAccountNumber  {
			
			when(acctrepo.searchAccount(1004)).thenReturn(acct);
			assertEquals(3000, acctserv.withDrawAmt(1004, 2000));
		}
		/*
		 * transfer fund
		 * 1. when amt > bal then system should throw InsufficientBalanceException
		 * 2. when account number is invalid system should throw InvalidAccountNoException
		 * 3. when valid info is passed system should return true
		 */
		
		@Test(expected = com.cg.exception.InsufficientBalanceException.class)
		public void whenamtislessthanbalanceincust_fromsystemshouldthrowexception() throws InsufficientBalanceException, InvalidAccountNumber{
			
			Account acct2 = new Account();
			acct2.setAccNum(1005);
			acct2.setBalance(3000);
			
			when(acctrepo.searchAccount(1004)).thenReturn(acct);
			when(acctrepo.searchAccount(1005)).thenReturn(acct2);
			
			acctserv.transfer_fund(1004, 1005, 1000000);
		}
		
		@Test(expected = com.cg.exception.InvalidAccountNumber.class)
		public void wheninvalidaccnoispassedforcustsystemshouldthrowexception() throws InvalidAccountNumber,InsufficientBalanceException {
			acctserv.transfer_fund(102, 15, 200);
		}
		
		@Test
		public void whenvalidinfoispassedsystemshouldreturntrue() throws InvalidAccountNumber,InsufficientBalanceException {
			
			Account acct2 = new Account();
			acct2.setAccNum(1005);
			acct2.setBalance(3000);
			
			
			when(acctrepo.searchAccount(1004)).thenReturn(acct);
			when(acctrepo.searchAccount(1005)).thenReturn(acct2);
			
			assertTrue(acctserv.transfer_fund(1004, 1005, 2000));
		}
		/*
		 * show balance
		 * 1. when invalid accno is passed system should throw InvalidAccountNoException
		 * 2. when valid info is passed system should return balance
		 */
		
		@Test(expected = com.cg.exception.InvalidAccountNumber.class)
		public void wheninvalidaccnumberispassedsystemshouldthrowexception() throws InvalidAccountNumber {
			acctserv.show_balance(10);
		}
		
		@Test
		public void whenvalidinfoispassedforshowbalsystemshouldreturnnewbalance() throws InvalidAccountNumber {
			
			when(acctrepo.searchAccount(1004)).thenReturn(acct);
			
			assertEquals(5000, acctserv.show_balance(1004));
			
		}
}

package com.capgemini.pl;

import java.sql.ResultSet;

import java.util.Scanner;

import com.capgemini.dao.AccountNotFoundException;
//import com.capgemini.dao.LowBalance;
import com.capgemini.service.ServiceClass;

public class MainUI {
	static ServiceClass accService = new ServiceClass();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String city = "";
		String type = "";
		String name = "";
		String phone = "";
		int balance = 0;
		int a = 0;
		Scanner s = new Scanner(System.in);
		while (true) 
		
		{
			System.out.println("1.Create an account");
			System.out.println("2.Fund transfer");
		    System.out.println("3.Show account id");
			System.out.println("4.exit");
			a = s.nextInt();
			String accountId;

			int amount;
			switch (a) {

			case 1:
				System.out.println("Name should be in characters");
				name = s.next();
				while (!(name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')) {
					System.out.println("First letter should be in capitals");
					name = s.next();
				}
				System.out.println("Enter BALANCE");
				balance = s.nextInt();
				System.out.println("ENTER TYPE");
				type = s.next();
				while (!(type.equalsIgnoreCase("current") || type.equalsIgnoreCase("savings"))) {
					System.out.println("Account should be current or savings");
					type = s.next();
				}

				System.out.println("ENTER phone number");
				phone = s.next();
				while (!(phone.length() == 10 )) {
					System.out.println("Phone number length should be 10");
					phone = s.next();
				}
				System.out.println("ENTER city");
				city = s.next();
				int updateCount = accService.addValues(name, phone, type, balance, city);
				System.out.println("Inserted " + updateCount + " record");
				break;
		
			case 2:
				System.out.println("Enter account id of sender");
				accountId = s.next();
				System.out.println("Enter account id of receiver");
				String raccountid = s.next();
				if(accountId.equals(raccountid))
				{
					System.out.println("Sender and Receiver cannot be same");
					System.out.println("Enter valid account id");
					raccountid=s.next();
					
				}
				
				System.out.println("Enter amount");
				amount = s.nextInt();
				accService.fundTransfer(accountId, raccountid, amount);
				accService.transcations(amount, raccountid, accountId);
				break;
				
			
			case 3:
				
				try 
				{
					System.out.println("enter phone no");
					phone = s.next();
					ResultSet accinfo = accService.searchAccount(phone);
					

					System.out.println("\nAccountId " + accinfo.getString(6));
				} catch 
				(AccountNotFoundException e) {
					System.out.println("phone no does not exist");
				}
				break;

			case 4:
				
				System.exit(0);
			}
		}
	}

}

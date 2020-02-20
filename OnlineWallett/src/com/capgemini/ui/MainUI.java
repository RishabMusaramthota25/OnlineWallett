package com.capgemini.ui;
import java.sql.ResultSet;
import java.util.Scanner;
import com.capgemini.dao.AccountNotFoundException;
import com.capgemini.dao.LowBalance;
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
			System.out.println("2.Show account ID");
			System.out.println("3.Show the balance");
			System.out.println("4.Withdraw the amount");
			System.out.println("5.Exit");
			System.out.println("Heyy");
			a = s.nextInt();
			String accountId;

			switch (a) {

			case 1:
				System.out.println("Name should be in characters");
				name = s.next();
				while (!(name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')) {
					System.out.println("First letter should be in capitals");
					name = s.next();
				}
				System.out.println("Enter the amount to be added");
				balance = s.nextInt();
				System.out.println("Enter the type of the account");
				type = s.next();
				while (!(type.equalsIgnoreCase("current") || type.equalsIgnoreCase("savings"))) {
					System.out.println("Account should be current or savings");
					type = s.next();
				}

				System.out.println("Enter phone number");
				phone = s.next();
				while (!(phone.length() == 10 && (phone.charAt(0) == '6') || (phone.charAt(0) == '7')
						|| (phone.charAt(0) == '8') || (phone.charAt(0) == '9'))) {
					System.out.println("Phone number length should be 10");
					phone = s.next();
				}
				System.out.println("Enter city");
				city = s.next();
				int updateCount = accService.addValues(name, phone, type, balance, city);
				System.out.println("Inserted " + updateCount + " record");
				break;
			case 2:
				try {
					System.out.println("enter phone number");
					phone = s.next();
					ResultSet accinfo = accService.searchAccount(phone);
					//System.out.println("search" + accinfo);

					System.out.println(" \nAccountId: " + accinfo.getString(6));
				} catch (AccountNotFoundException e) {
					System.out.println("Phone number does not exist");
				}
				break;
			
			
			  case 4:
				  try 
			  {
					  System.out.println("Enter accountid");
			  accountId = s.next();
			  System.out.println("Enter amount");
			  int amount = s.nextInt();
			  int updated =accService.withdraw(accountId, amount);
			  accService.transcations(amount,accountId);
			  if (updated > 0)
			  {
			  System.out.println("Withdraw completed successfully");
			  } 
			  else
			  System.out.println("Withdraw Cancelled"); 
			  } catch (LowBalance e)
				  {
			  System.out.println("Low Balance");
			  }
			  
			  break;
			 
		
			case 5:
				System.exit(0);
			
			case 3:
				try {
					System.out.println("Enter accountId");
					accountId = s.next();
					ResultSet accinfo = accService.searchAccount1(accountId);
					

					System.out.println("\nAccount holder's name: "+accinfo.getString(1)+"\nAccount ID: "+accinfo.getString(6)+"\nBalance: " + accinfo.getInt(3)+"\nAccount holder's phone number: "+accinfo.getString(4));
				} catch (AccountNotFoundException e) {
					System.out.println("Account ID does not exist");
				}
				break;
			}
		}
	}
	}



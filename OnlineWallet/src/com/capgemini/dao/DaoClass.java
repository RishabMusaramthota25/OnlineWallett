package com.capgemini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;


public class DaoClass {

	public ResultSet searchAccount(String phone) throws AccountNotFoundException,Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "select * from account where phone=?";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, phone);
		ResultSet accinfo = pstmt.executeQuery();
		if(accinfo.next())
		return accinfo;
		else
		{
			throw new AccountNotFoundException("phone no not valid");
		}

	}
	public ResultSet searchAccount1(String accountId) throws AccountNotFoundException,Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "select * from account where accountId=?";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, accountId);
		ResultSet accinfo = pstmt.executeQuery();
		if(accinfo.next())
		return accinfo;
		else{
			throw new AccountNotFoundException("Account not valid");
		}

	}

	static int a1 = 0;
	static int a2 = 0;


	static String aid = "9000";
	static int aid2;

	public int addValues(int balance, String name, String type, String phone, String city) throws Exception {
		Connection con = null;
		int x = Math.abs(new Random().nextInt());
		PreparedStatement pstmt = null;

		con = DbClass.getConnection1();
		String ins_str = "insert into account values(?,?,?,?,?,?)";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, name);
		pstmt.setString(2, type);
		pstmt.setDouble(3, balance);
		pstmt.setString(4, phone);
		pstmt.setString(5, city);
		pstmt.setString(6, Integer.toString(x));
		int updateCount = pstmt.executeUpdate();
		con.close();
		return updateCount;

	}

	public void fundTransfer(String accountId, String raccountid, int amount) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		pstmt = con.prepareStatement("select balance from account where accountId=?");
		pstmt.setString(1, raccountid);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
			a1 = amount + rs.getInt(1);
		pstmt = con.prepareStatement("update account set balance=? where accountId=?");
		pstmt.setInt(1, a1);
		pstmt.setString(2, raccountid);
		int update = pstmt.executeUpdate();
		if (update > 0) {
			pstmt = con.prepareStatement("select * from account where accountId=?");
			pstmt.setString(1, raccountid);
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next())
				System.out.println(rs1.getString(1) + "  " + /*rs1.getString(2) + */" " + rs1.getInt(3) + " "
						/*+ rs1.getString(4) + " " + rs1.getString(5) + " " + rs1.getString(6)*/);
		}
		pstmt = con.prepareStatement("select balance from account where accountId=?");
		pstmt.setString(1, accountId);
		ResultSet rs2 = pstmt.executeQuery();
		if (rs2.next())
			a2 = rs2.getInt(1) - amount;

		pstmt = con.prepareStatement("update account set balance=? where accountId=?");
		pstmt.setInt(1, a2);
		pstmt.setString(2, accountId);
		int update1 = pstmt.executeUpdate();
		if (update1 > 0) {

			pstmt = con.prepareStatement("select * from account where accountId=?");
			pstmt.setString(1, accountId);
			ResultSet rs3 = pstmt.executeQuery();
			while (rs3.next())
				System.out.println(rs3.getString(1) + "   " +/* rs3.getString(2) +*/ " " + rs3.getInt(3) + " "
						/*+ rs3.getString(4) + " " + rs3.getString(5) + " " + rs3.getString(6)*/);
		}
	}


	public void transactions(int amount, String raccountid, String accountId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "insert into transaction values(?,?,?,?)";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setInt(1, 0);
		pstmt.setInt(2, 0);
		pstmt.setInt(3, amount);
		pstmt.setString(4, accountId);
		pstmt.executeUpdate();
		con.close();

	}

	public ResultSet details(String accountId) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "select * from transaction where accountid=?";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, accountId);
		ResultSet accinfo = pstmt.executeQuery();
		return accinfo;
	}
}

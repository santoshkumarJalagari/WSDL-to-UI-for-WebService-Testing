package com.weblogin;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler 
{
	private static DatabaseHandler sInstance = null;
	private static Connection con=null;
	private static Statement stmt=null;
	private static PreparedStatement pstmt=null;
	
	public static DatabaseHandler instance() {

		if (sInstance == null) {
			sInstance = new DatabaseHandler();
		}
		return sInstance;
	}
	
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
//			Dbprop probob=new Dbprop();
//			probob.setProp();
//			String mysqlurl = "jdbc:mysql://"+probob.getIp()+":"+probob.getPort()
//					+"/"+probob.getDatbase_name();
//			System.out.println(mysqlurl+" "+probob.getUsername()+" "+probob.getPassword());
			con = DriverManager.getConnection("jdbc:mysql://10.31.211.56:3306/automation", "root", "505tulsa");            
//			con=DriverManager.getConnection("jdbc:mysql://10.31.211.56:3306/automation", "root", "505tulsa");
		}
		catch (Exception ex) 
		{
			System.out.println(ex+"--Connection ERROR--");
		}
		return con;
	}
	
	public static void openConnection() throws ClassNotFoundException, SQLException
	{
//			Class.forName("com.mysql.jdbc.Driver");
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ybdmenu?generateSimpleParameterMetadata=true","root","admin");	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
//			Dbprop probob=new Dbprop();
//			probob.setProp();
//			String mysqlurl = "jdbc:mysql://"+probob.getIp()+":"+probob.getPort()
//					+"/"+probob.getDatbase_name();
//			System.out.println(mysqlurl+" "+probob.getUsername()+" "+probob.getPassword());
			con = DriverManager.getConnection("jdbc:mysql://10.31.211.56:3306/automation", "root", "505tulsa");
//			con=DriverManager.getConnection("jdbc:mysql://10.31.211.56:3306/automation", "root", "505tulsa");
		}
		catch (Exception ex) 
		{
			System.out.println(ex+"--Connection ERROR--");
		}
	}
	
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public static Statement createStatement() throws SQLException
	{
		stmt=con.createStatement();
		return stmt;
	}
	public static void closeStatement() throws SQLException
	{
		if(stmt==null)
			stmt.close();
	}
	public static Statement createPreparedStatement() throws SQLException
	{
		pstmt=con.prepareStatement(null);
		return stmt;
	}
	public static void closePreparedStatement() throws SQLException
	{
		if(pstmt==null)
			pstmt.close();
	}
	public String authenticateUser(String name,String password) 
	{
		String authenticate_query="select roles from users3 where username=? and password=?";
		String flag="false"; 
		try
		{
			openConnection();
//			createPreparedStatement();
			pstmt=con.prepareStatement(authenticate_query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				flag=rs.getString(1);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return flag;
	}
	
	public String validate(String username, String password)
	{
		String flag="false";
		String validate_query="select roles from users3 where username=? and password=?";
		try
		{
			openConnection();
			PreparedStatement pstmt=con.prepareStatement(validate_query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				flag=rs.getString(1);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return flag;		
	}
	
	public ArrayList<ArrayList<String>> getScripts(String category)
	{
		ArrayList<ArrayList<String>> items =new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> items1 =new ArrayList<ArrayList<String>>();
		String get_all_values_query="";
		if(category.equals("sit"))
		{
//			get_all_values_query="select `TEST_SCRIPT`,`ITERATION_NO` FROM test_master_bkp1 where CATEGORY='SIT' AND (DOMAIN IS NOT NULL  AND DOMAIN!='')";			
			get_all_values_query="SELECT TC_ID,TEST_SCRIPT,ITERATION_NO FROM test_master_bkp1 where CATEGORY='SIT' AND (DOMAIN IS NOT NULL  AND DOMAIN!='')";
		
			try
			{
				openConnection();
				PreparedStatement pstmt=con.prepareStatement(get_all_values_query);
	
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					ArrayList<String> child=new ArrayList<String>();
					child.add(rs.getString(1));
					child.add(rs.getString(2));
					child.add(rs.getString(3));
//					child.add(rs.getString(4));
//					child.add(rs.getString(5));
//					child.add(rs.getString(6));
					items.add(child);
				}
				closeConnection();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(category.equals("tdev"))
		{
			get_all_values_query="SELECT * FROM test_master where CATEGORY='TDEV'";
			try
			{
				openConnection();
				PreparedStatement pstmt=con.prepareStatement(get_all_values_query);
	
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					ArrayList<String> child=new ArrayList<String>();
					child.add(rs.getString(1));
					child.add(rs.getString(2));
					child.add(rs.getString(3));
					child.add(rs.getString(4));
					child.add(rs.getString(5));
					child.add(rs.getString(6));
					items.add(child);
				}
				closeConnection();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		return items;
	}
	
	public ArrayList<ArrayList<String>> getScriptsResults(String[] scriptnames,String scriptdate)
	{
		ArrayList<ArrayList<String>> items =new ArrayList<ArrayList<String>>();
//		ArrayList<ArrayList<String>> items1 =new ArrayList<ArrayList<String>>();
		
		String get_all_values_query="";
		String var="";
		String query="";
//		System.out.println(get_all_values_query);

		for(int i=0;i<scriptnames.length;i++)
		{
			var+="'"+scriptnames[i]+"'"+",";
		}
//		System.out.println(var);	 
		var=var.substring(0,var.length()-1);
					
		get_all_values_query="SELECT TM.TC_ID,TM.TEST_SCRIPT, TR.TEST_DATA, TR.TR_STATUS, TR.TEST_TIME, TM.DOMAIN " +

                              "FROM test_master_bkp1 TM, TEST_RESULTS_BKP TR " +

//                                                  "WHERE TM.TC_ID = TR.TC_ID " +
								"WHERE TM.TC_ID = TR.TC_ID " +

                              "AND TM.TC_ID IN ("+var+") " +

                              "AND TR.TEST_TIME >= DATE('"+scriptdate+"' - INTERVAL 1 DAY) " +

                              "AND TR.TEST_TIME <= DATE('"+scriptdate+"' + INTERVAL 100 DAY) " +

                              "AND TR.TEST_DATA IS NOT NULL " +
                              "AND (TM.DOMAIN IS NOT NULL  AND TM.DOMAIN!='') " +

                              "ORDER BY TM.TC_ID ";
		
		 System.out.println(get_all_values_query);
		
		try
		{
			
			openConnection();
			PreparedStatement pstmt=con.prepareStatement(get_all_values_query);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				ArrayList<String> child=new ArrayList<String>();
				child.add(rs.getString(1));
				child.add(rs.getString(2));
				child.add(rs.getString(3));
				child.add(rs.getString(4));
				child.add(rs.getString(5));
				child.add(rs.getString(6));
				items.add(child);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return items;
	}	
		
	public ArrayList<ArrayList<String>> getScriptsResults1(String[] scriptnames,String scriptdate)
	{
		ArrayList<ArrayList<String>> items =new ArrayList<ArrayList<String>>();
//		ArrayList<ArrayList<String>> items1 =new ArrayList<ArrayList<String>>();
		
		String get_all_values_query="";
		String var="";
		String query="";
//		System.out.println(get_all_values_query);

		for(int i=0;i<scriptnames.length;i++)
		{
			var+="'"+scriptnames[i]+"'"+",";
						
		}
//		System.out.println(var);	 
		var=var.substring(0,var.length()-1);
					
		get_all_values_query="SELECT TR_ID,TEST_SCRIPT, TEST_DATA, TR_STATUS, TEST_TIME FROM ( " +

                                                  "SELECT TR.TR_ID,TM.TEST_SCRIPT, TR.TEST_DATA, TR.TR_STATUS, TR.TEST_TIME " +

                                                  "FROM test_master_bkp1 TM, TEST_RESULTS TR " +

//                                                  "WHERE TM.TC_ID = TR.TC_ID " +
													"WHERE TM.TEST_SCRIPT = TR.TEST_SCRIPT " +

                                                  "AND TM.TEST_SCRIPT IN ("+var+") " +

                                                  "AND TR.TEST_TIME >= DATE('"+scriptdate+"' - INTERVAL 1 DAY) " +

                                                  "AND TR.TEST_TIME <= DATE('"+scriptdate+"' + INTERVAL 1 DAY) " +

                                                  "AND TR.TEST_DATA IS NOT NULL " +

                                                  "ORDER BY  TR.TEST_SCRIPT " +

                                                  ")A ";
//		 System.out.println(get_all_values_query);
//		"WHERE TM.TC_ID = TR.TC_ID " +
		
		try
		{
			
			openConnection();
			PreparedStatement pstmt=con.prepareStatement(get_all_values_query);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				ArrayList<String> child=new ArrayList<String>();
				child.add(rs.getString(1));
				child.add(rs.getString(2));
				child.add(rs.getString(3));
				child.add(rs.getString(4));
//				child.add(rs.getString(5));
				items.add(child);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return items;
	}	
		
	
}


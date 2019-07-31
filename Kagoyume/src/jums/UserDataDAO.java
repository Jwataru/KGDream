package jums;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import base.DBManager;

public class UserDataDAO implements Serializable {

	//インスタンス化
	public static UserDataDAO getInstance(){
		return new UserDataDAO();
	}

	/**
	 * データの挿入処理を行う。現在時刻は挿入直前に生成
	 * @param ud 対応したデータを保持しているJavaBeans
	 * @throws SQLException 呼び出し元にcatchさせるためにスロー 
	 */
	public void insertUser(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try{
			con = (Connection) DBManager.getConnection();
			st =  (PreparedStatement) con.prepareStatement("INSERT INTO user_tt(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");       
			st.setString(1, udd.getName());      
			st.setString(2, udd.getPassword());     
			st.setString(3, udd.getMail());      
			st.setString(4, udd.getAddress());        
			st.setInt(5, udd.getTotal());       
			st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));        
			st.executeUpdate();
			System.out.println("insert completed");
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}



	public void insertProduct(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try{
			con = (Connection) DBManager.getConnection();
			st =  (PreparedStatement) con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
			st.setInt(1, udd.getUserID());
			st.setString(2, udd.getCode());
			st.setInt(3, udd.getType());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));            

			st.executeUpdate();
			System.out.println("insert completed");
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}

	public boolean LoginChecker(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;

		boolean flag;
		try{

			con = (Connection) DBManager.getConnection();
			st =  (PreparedStatement) con.prepareStatement("select * from user_tt where name = ? and password = ?");

			st.setString(1, udd.getName());
			st.setString(2, udd.getPassword());

			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				flag=true;
			}else {
				flag=false;
			}			
			System.out.println("searchlogin completed");

			return flag;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}


	public UserDataDTO UserInf(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		try{
			con = (Connection) DBManager.getConnection();

			st =  (PreparedStatement) con.prepareStatement("SELECT * FROM user_tt WHERE name = ?");
			st.setString(1, udd.getName());
			rs = st.executeQuery();
			UserDataDTO resultUd = new UserDataDTO();

			rs.next();
			resultUd.setUserID(rs.getInt(1));
			resultUd.setName(rs.getString(2));
			resultUd.setPassword(rs.getString(3));
			resultUd.setMail(rs.getString(4));
			resultUd.setAddress(rs.getString(5));
			resultUd.setTotal(rs.getInt(6));
			resultUd.setNewDate (rs.getTimestamp(7));
			resultUd.setDeleteFlg(rs.getInt(8));

			System.out.println("UserInf completed");

			return resultUd;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}

	public void UserUpdate(UserDataDTO udd) throws SQLException{
		Connection con = null;		
		PreparedStatement db_st=null;

		try {
			con = (Connection) DBManager.getConnection();
			db_st=(PreparedStatement) con.prepareStatement("update user_tt set name=?,password=?,mail=?,address=? where userID=?");


			db_st.setString(1,udd.getName());			
			db_st.setString(2, udd.getPassword());			
			db_st.setString(3,udd.getMail());			
			db_st.setString(4,udd.getAddress());		
			db_st.setInt(5,udd.getUserID());


			db_st.executeUpdate();

			db_st.close();

			System.out.println("update success!");


		}catch(SQLException e_sql) {
			System.out.println("接続時にエラーが発生しました(sql)"+e_sql.toString());
		}catch(Exception e) {
			System.out.println("接続時にエラーが発生しました"+e.toString());
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(Exception e_con) {
					System.out.println(e_con.getMessage());
				}
			}
		}

	}


	public void UserTotal(UserDataDTO udd) throws SQLException{
		Connection con = null;		
		PreparedStatement db_st=null;

		try {
			con = (Connection) DBManager.getConnection();
			db_st=(PreparedStatement) con.prepareStatement("update user_tt set total=? where userID=?");


			db_st.setInt(1,udd.getTotal());		
			
			db_st.setInt(2,udd.getUserID());

			db_st.executeUpdate();

			db_st.close();

			System.out.println("update success!");


		}catch(SQLException e_sql) {
			System.out.println("接続時にエラーが発生しました(sql)"+e_sql.toString());
		}catch(Exception e) {
			System.out.println("接続時にエラーが発生しました"+e.toString());
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(Exception e_con) {
					System.out.println(e_con.getMessage());
				}
			}
		}

	}
	

	

	public boolean RegistrationCheck(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;

		boolean flag;
		try{

			con = (Connection) DBManager.getConnection();
			st =  (PreparedStatement) con.prepareStatement("select * from user_tt where name = ?");
			st.setString(1, udd.getName());			
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				flag=false;
			}else {
				flag=true;
			}			
			System.out.println("RegistrationCheck completed");
			return flag;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}


	public boolean DeleteCheck(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;

		boolean flag;
		try{

			con = (Connection) DBManager.getConnection();
			st =  (PreparedStatement) con.prepareStatement("select * from user_tt where name = ? and deleteFlg=1");

			st.setString(1, udd.getName());			

			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				flag=true;
			}else {
				flag=false;
			}			
			System.out.println("DeleteCheck completed");

			return flag;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}

	public void UserDelete(UserDataDTO udd) throws SQLException{
		Connection con = null;		
		PreparedStatement db_st=null;

		try {
			con = (Connection) DBManager.getConnection();
			db_st=(PreparedStatement) con.prepareStatement("update user_tt set deleteFlg=1,newDate=? where userID=?");


			db_st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			db_st.setInt(2,udd.getUserID());

			db_st.executeUpdate();
			db_st.close();

			System.out.println("delete success!");

		}catch(SQLException e_sql) {
			System.out.println("接続時にエラーが発生しました(sql)"+e_sql.toString());
		}catch(Exception e) {
			System.out.println("接続時にエラーが発生しました"+e.toString());
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(Exception e_con) {
					System.out.println(e_con.getMessage());
				}
			}
		}

	}




	public ArrayList<UserDataDTO> BuyInf(UserDataDTO udd) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs=null;

		try{
			con = (Connection) DBManager.getConnection();



			st =  (PreparedStatement) con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
			st.setInt(1, udd.getUserID());
			rs = st.executeQuery();

			ArrayList<UserDataDTO> box=new ArrayList<UserDataDTO>();

			while(rs.next()) {		
				UserDataDTO resultUd = new UserDataDTO();
				resultUd.setBuyID(rs.getInt(1));
				resultUd.setUserID(rs.getInt(2));
				resultUd.setCode(rs.getString(3));
				resultUd.setType(rs.getInt(4));
				resultUd.setBuyDate(rs.getTimestamp(5));					
				box.add(resultUd);				
			}

			System.out.println("history completed");

			rs.close();
			st.close();

			return box;

		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			if(con != null){
				con.close();
			}
		}

	}


}



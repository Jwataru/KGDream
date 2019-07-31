package jums;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserDataDTO {
	private int userid;
	private String name;
	private String password;
	private String mail;
	private String address;
	private int total;
	private Timestamp newDate;
	private int deleteFlg;
	private int buyID;
	private String code;
	private int type;
	private Timestamp buyDate;

	public UserDataDTO(){
		this.userid=0;
		this.name = "";
		this.password = "";
		this.mail = "";
		this.address = "";
		this.total = 0;
		this.type = 0;
		this.deleteFlg= 0;
		this.buyID=0;
		this.code = "";       
	}

	
	public static UserDataDTO getInstance(){
		return new UserDataDTO();
	}

	public int getUserID() {
		return userid;
	}
	public void setUserID(int userid) {
		this.userid = userid;
	}

	public String getName(){
		return name;
	}

	public void setName(String name) {
		//空文字(未入力)の場合空文字をセット
		if(name.trim().length()==0){
			this.name = "";
		}else{
			this.name = name;
		}
	}

	public String getPassword(){
		return password;
	}


	public void setPassword(String password) {
		//空文字(未入力)の場合空文字をセット
		if(password.trim().length()==0){
			this.password = "";
		}else{
			this.password = password;
		}
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		//空文字(未入力)の場合空文字をセット
		if(mail.trim().length()==0){
			this.mail = "";
		}else{
			this.mail = mail;
		}
	}
	public String getAddress(){
		return address;
		
	}
	public void setAddress(String address) {
		//空文字(未入力)の場合空文字をセット
		if(address.trim().length()==0){
			this.address = "";
			
		}else{
			this.address = address;
		}
	}

	public int getTotal(){
		return total;
	}
	public void setTotal(int total){
		this.total = total;
	}

	public Timestamp getNewDate() {
		return newDate;
	}
	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}

	public int getDeleteFlg(){
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	public int getType(){
		return type;
	}
	public void setType(int type){
		this.type = type;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public int getBuyID() {
		return buyID;
	}
	public void setBuyID(int buyID) {
		this.buyID = buyID;
	}
	public Timestamp getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}

	public ArrayList<String> chkproperties(){
		ArrayList<String> chkList = new ArrayList<String>();
		if(this.name.equals("")){
			chkList.add("name");
		}
		if(this.password.equals("")) {
			chkList.add("password");
		}
		if(this.mail.equals("")) {
			chkList.add("mail");
		}
		if(this.address.equals("")) {
			chkList.add("address");
		}
		return chkList;
	}

	
	
	public  String trimWhitespace(String str) {
	    if (str == null || str.length() == 0) {
	        return str;
	    }
	    int st = 0;
	    int len = str.length();
	    char[] val = str.toCharArray();
	    while ((st < len) && ((val[st] <= '\u0020') || (val[st] == '\u00A0') || (val[st] == '\u3000'))) {
	        st++;
	    }
	    while ((st < len) && ((val[len - 1] <= '\u0020') || (val[len - 1] == '\u00A0') || (val[len - 1] == '\u3000'))) {
	        len--;
	    }
	    return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
	}
}

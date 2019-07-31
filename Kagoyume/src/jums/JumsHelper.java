package jums;

import java.util.ArrayList;

public class JumsHelper {
	 //トップへのリンクを定数として設定
    private final String topURL = "Top";
    private final String cartURL = "Cart";
    private final String mydata = "MyData";
    private final String loginURL = "Login";
    
    
    
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }
    
    //トップへのリンクを返却
    public String top(){
        return "<a href=\""+topURL+"\">トップへ戻る</a>";
    }
    public String login(){
        return "<a href=\""+loginURL+"\">ログイン</a>";
    }
    public String logout(){
        return "<a href=\""+loginURL+"\">ログアウト</a>";
    }
    public String cart() {
        return "<a href=\""+cartURL+"\">カートを見る</a>";
    }
    public String mydata() {
        return "<a href=\""+mydata+"\">会員情報</a>";
    }
    
    
    
    public String Type(int i){
        String type = "";
        switch(i){
            case 1:
            	type += "普通便";
                break;
            case 2:
            	type += "速達便";
                break;
            case 3:
            	type += "コンビニ配達";
                break;
        }
        return type;
    } 
    
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output += "パスワード";
                }
                if(val.equals("mail")){
                    output += "メールアドレス";
                }
                if(val.equals("address")){
                    output += "住所";
                }
                output +="が未記入です<br>";
            }
        return output;
    }
}

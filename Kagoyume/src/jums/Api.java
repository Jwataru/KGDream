package jums;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api {

	String result = "";
	JsonNode head = null;
	public  ArrayList<ProductDataBeans> getResult(ProductDataBeans pd){
		ArrayList<ProductDataBeans> pdbList = new ArrayList<ProductDataBeans>();
		final String yahooShoppingUrl = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
		final String applyID = "dj00aiZpPUhqUWRGZ01ldDg1WiZzPWNvbnN1bWVyc2VjcmV0Jng9MzE-";
		try{
			String urlString = URLEncoder.encode(pd.getSearchName(), "utf-8");
			URL url = new URL(yahooShoppingUrl + "?appid=" + applyID + "&query=" + urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			//レスポンスの読み出し(JASON文字列の取得)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				result += tmp;
			}

			JsonFactory jfactory = new JsonFactory();
			JsonParser parser = jfactory.createParser(result);
			ObjectMapper mapper = new ObjectMapper();

			head = mapper.readTree(parser);


			//JSONから20件分の要素を取り出し、String型に格納
			//それをBeansにセットし、BeansをArrayListに格納
			for(int i = 0; i < 20; i++) {
				String hitNum = String.valueOf(i);
				String imageURL = head.get("ResultSet").get("0").get("Result").get(hitNum).get("Image").get("Small").textValue();
				String productName = head.get("ResultSet").get("0").get("Result").get(hitNum).get("Name").textValue();
				String price = head.get("ResultSet").get("0").get("Result").get(hitNum).get("Price").get("_value").textValue();
				String code = head.get("ResultSet").get("0").get("Result").get(hitNum).get("Code").textValue();

				//商品検索結果を格納するBeansのインスタンスを生成
				ProductDataBeans pdb = new ProductDataBeans();
				pdb.setImageURL(imageURL);
				pdb.setProductName(productName);
				pdb.setPrice(price);  
				pdb.setCode(code); 


				pdbList.add(pdb);
			}


			br.close();
			con.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		return pdbList;
	}



	public static ProductDataBeans getDetail(ProductDataBeans pd){
		ProductDataBeans pdb = new ProductDataBeans();
		String result = "";
		JsonNode head = null;
		final String yahooShoppingUrl = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";
		final String applyID = "dj00aiZpPUhqUWRGZ01ldDg1WiZzPWNvbnN1bWVyc2VjcmV0Jng9MzE-";
		try{
			String parsedID = URLEncoder.encode(pd.getCode(), "utf-8");
			URL url = new URL(yahooShoppingUrl + "?appid=" + applyID + "&itemcode=" + parsedID + "&responsegroup=medium");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				result += tmp;
			}

			JsonFactory jfactory = new JsonFactory();
			JsonParser parser = jfactory.createParser(result);
			ObjectMapper mapper = new ObjectMapper();

			head = mapper.readTree(parser);

			pdb.setProductName(head.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue());
			pdb.setPrice(head.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").textValue());
			pdb.setImageURL(head.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").textValue());
			pdb.setCode(head.get("ResultSet").get("0").get("Result").get("0").get("Code").textValue());
			pdb.setDescription(head.get("ResultSet").get("0").get("Result").get("0").get("Description").textValue());
			pdb.setRate(head.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").textValue());


			br.close();
			con.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		return pdb;
	}



	public int getAmount(ProductDataBeans pd){
		
		final String yahooShoppingUrl = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
		final String applyID = "dj00aiZpPUhqUWRGZ01ldDg1WiZzPWNvbnN1bWVyc2VjcmV0Jng9MzE-";
		int amount=0;
		try{
			String urlString = URLEncoder.encode(pd.getSearchName(), "utf-8");
			URL url = new URL(yahooShoppingUrl + "?appid=" + applyID + "&query=" + urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			//レスポンスの読み出し(JASON文字列の取得)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				result += tmp;
			}

			JsonFactory jfactory = new JsonFactory();
			JsonParser parser = jfactory.createParser(result);
			ObjectMapper mapper = new ObjectMapper();

			head = mapper.readTree(parser);

			amount = head.get("ResultSet").get("totalResultsAvailable").asInt();

			br.close();
			con.disconnect();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return amount;
	}
}
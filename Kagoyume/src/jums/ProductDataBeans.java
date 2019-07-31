package jums;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductDataBeans implements Serializable{

	private String searchName;
	private String imageURL ;
	private String  productName;
	private String  price;
	private String code;
	private String description;
    private String rate;
    private Timestamp buyDate;
	
	public ProductDataBeans(){
		this.searchName = "";
		this.imageURL = "";
		this.productName = "";
		this.price = "";
		this.code = "";
		this.description = "";
        this.rate = "";	
    
	}

	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}



	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;  
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
    public String getDescrition() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public Timestamp getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }
	
}

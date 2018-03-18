package com.beans;

import java.util.HashMap;
import java.util.Map;

//购物车类
public class ShoppingCart {

	//书本的总数目private int totalBookNumber;
	//存放ShoppingCartItem的Map: 键是书名，值是ShoppingCartItem对象
	private Map<String, ShoppingCartItem> items= new HashMap<String,ShoppingCartItem>();
	private String bookName;
	public void addToCart(String bookName,int price) {
		//判断商品是否在购物车中，如果在，获取购物车中商品，然后数量增加；如果不存在，则新建商品对象，设置其属性，然后加入到购物车中
		this.bookName = bookName;
		if(items.containsKey(bookName)) {
			ShoppingCartItem item = items.get(bookName);
			item.setNumber(item.getNumber()+1);
		}else {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setBookName(bookName);
			item.setNumber(1);
			item.setPrice(price);
			items.put(bookName, item);
		}
	}
	
	public String getBookName() {
		return bookName;
	}

	public int getTotalBookNumber() {
		int total = 0;
		for(ShoppingCartItem item:items.values()) {
			total += item.getNumber() ;
		}
		return total;
	}
	public int getTotalPrice() {
		int money = 0;
		for(ShoppingCartItem item:items.values()) {
			money += item.getNumber() *item.getPrice();
		}
		
		return money;
	}

}

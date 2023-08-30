package com.mystudy.cafetest.vo;
public class MenuVO {
	int menuId;
	String name;
	int price;
	
	public MenuVO() {}
	
	public MenuVO(int menuId) {
		super();
		this.menuId = menuId;
	}
	public MenuVO(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	public MenuVO(int menuId, String name, int price) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.price = price;
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuVO [menuId=" + menuId + ", name=" + name + ", price=" + price + "]";
	}

	
	
	
}
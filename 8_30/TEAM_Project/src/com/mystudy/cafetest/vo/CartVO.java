package com.mystudy.cafetest.vo;

public class CartVO {
	private String menuname;
	private int drinkcount;
	private static int totcount;
	
	public CartVO() {}
	
	public CartVO(String menuname, int drinkcount) {
		super();
		this.menuname = menuname;
		this.drinkcount = drinkcount;
		computeTot ();
	}
	
	public void computeTot () {
		totcount += drinkcount;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public int getDrinkcount() {
		return drinkcount;
	}

	public void setDrinkcount(int drinkcount) {
		this.drinkcount = drinkcount;
	}

	public static int getTotcount() {
		return totcount;
	}

	public void setTotcount(int totcount) {
		this.totcount = totcount;
	}

	@Override
	public String toString() {
		return "CartVO [menuname=" + menuname + ", drinkcount=" + drinkcount + ", totcount=" + totcount + "]";
	}
	
	
	
	
}

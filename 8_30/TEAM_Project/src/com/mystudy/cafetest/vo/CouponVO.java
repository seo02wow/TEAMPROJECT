package com.mystudy.cafetest.vo;

public class CouponVO {
	private String couponId;
	private String couponCnt;
	private String stampId;
	
	public CouponVO() {}
	
	public CouponVO(String couponId, String couponCnt, String stampId) {
		super();
		this.couponId = couponId;
		this.couponCnt = couponCnt;
		this.stampId = stampId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponCnt() {
		return couponCnt;
	}

	public void setCouponCnt(String couponCnt) {
		this.couponCnt = couponCnt;
	}

	public String getStampId() {
		return stampId;
	}

	public void setStampId(String stampId) {
		this.stampId = stampId;
	}

	@Override
	public String toString() {
		return "CouponVO [couponId=" + couponId + ", couponCnt=" + couponCnt + ", stampId=" + stampId + "]";
	}
	
	
	
}
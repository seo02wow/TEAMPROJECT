package com.mystudy.cafetest.vo;

public class StampVO {
	private String stampId;
	private int stampCnt;
	private int custId;
	
	public StampVO() {}
	
	public StampVO(String stampId, int stampCnt, int string) {
		super();
		this.stampId = stampId;
		this.stampCnt = stampCnt;
		this.custId = string;
	}

	public String getStampId() {
		return stampId;
	}

	public void setStampId(String stampId) {
		this.stampId = stampId;
	}

	public int getStampCnt() {
		return stampCnt;
	}

	public void setStampCnt(int stampCnt) {
		this.stampCnt = stampCnt;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "StampVO [stampId=" + stampId + ", stampCnt=" + stampCnt + ", custId=" + custId + "]";
	}

	
}
	
	
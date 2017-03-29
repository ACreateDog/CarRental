package com.carRental.observer;

public interface Observer {
	public void addCallBack(String carID,String shapesize,float singlePrice);
	public void addCallBack();
	public void delete(String[] arr);
	
}

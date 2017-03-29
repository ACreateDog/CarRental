package com.carRental.observer;

public interface RentalCallback {
	public void rentalCallBack(String carID,String phone);
	public void returnCallBack(String carID,String phone);
}

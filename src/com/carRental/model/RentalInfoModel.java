package com.carRental.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RentalInfoModel extends BaseModel{
	
	private String phone;
	private long start;
	private long end;
	private String INSERT = "insert into rental(phone,carID,start) values(?,?,?)";
	private String SELECT_RENTALED_AND_UNRETURN = "select rental.carID ,rental.start,shapesize,singleprice from rental,car where rental.carID = car.carID and phone = ? and rental.state = 0";
	private String SELECT__UNRETURN = "select rental.carID ,rental.start,shapesize,singleprice from rental,car where rental.carID = car.carID and rental.state = 0";
	private String UPDATE = "update rental set state=1 where carID = ? ";
	private String UPDATE_car = "update car set state=0 where carID = ? ";
	
	public RentalInfoModel() {
		super();
	}
	public void returnCarWithRentalModel(String carID) {
		try {
			PreparedStatement statement =(PreparedStatement) (this.getConnection().prepareStatement(UPDATE));
			statement.setString(1, carID);
			int i = statement.executeUpdate();
			returnCarWithRentalModels(carID);
//			PreparedStatement statement1 =(PreparedStatement) (this.getConnection().prepareStatement(UPDATE_car));
//			statement1.setString(1, carID);
//			System.out.println(statement1.toString()+"huanche");
//			int j = statement1.executeUpdate();
//			System.out.println(j);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	public void returnCarWithRentalModels(String carID) {
		try {
			
			PreparedStatement statement1 =(PreparedStatement) (this.getConnection().prepareStatement(UPDATE_car));
			
			statement1.setString(1, carID);
			
			System.out.println(statement1.toString()+"huanche");
			int j = statement1.executeUpdate();
			System.out.println(j);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	/**
	 * 
	 * @param phone 电话号码
	 * @param carID 车牌号
	 * @param start 时间戳
	 * @return
	 */
	public boolean rentalCarBy(String phone,String carID,long start) {
		int flag = insertRental(phone,carID, start);
		if (flag > 0) {
			return true;
		}
		return false;
	}
	private int insertRental(String phone,String carID,long start) {
		int i = 0;
		try {
			PreparedStatement pStatement = this.getConnection().prepareStatement(INSERT);
			pStatement.setString(1, phone);
			pStatement.setString(2, carID);
			pStatement.setLong(3, start);

			i = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 
	 * @param phone  电话号码
	 * @return
	 */
	public Object[][] getRentalInfoByPhone(String phone) {
		
		ResultSet set = selectRentalInfos(phone);
		Object[][] objects =null;
		if (objects == null) {
			try {
				set.last();
				int rowCount = set.getRow();
				if (rowCount > 0) {
					objects = new Object[rowCount][4];
					set.first();
					System.out.println(rowCount);	
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		try {
			int count = 0;
			 do{
				
				objects[count][0] = set.getString(1);
				objects[count][1] = stampToDate(set.getLong(2));
				objects[count][2] = set.getString(3);
				objects[count][3] = set.getFloat(4);
				count++;
			}while (set.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
	 public static String stampToDate(long lt){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = new Date(lt);
	        res = simpleDateFormat.format(date);
	        return res;
	    }
	private ResultSet selectRentalInfos(String phone) {
		
		ResultSet set = null;
		try {
			if (!phone.equals("")) {
				PreparedStatement pStatement = this.getConnection().prepareStatement(SELECT_RENTALED_AND_UNRETURN);
				pStatement.setString(1, phone);
				
				set =  pStatement.executeQuery();

			}else {
				PreparedStatement pStatement = this.getConnection().prepareStatement(SELECT__UNRETURN);
				set =  pStatement.executeQuery();
				System.out.println(pStatement.toString()+set);

			}		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return set;
	}
	
	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

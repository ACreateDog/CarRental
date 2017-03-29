package com.carRental.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.carRental.view.AlertView;

public class VerifyController {
	public static AlertView alertView = null;

	//好维护

	static final String PHONE_NUM_IS_NULL_WARNING = "号码不能为空！";
	static final String PHONE_NUM_IS_UNCORRECT_WARNING = "号码输入不正确，请重新输入！";
	static final String Login_ACCOUNT_ISNOTChineseCharacter = "账户中含有汉字！";
	static final String Login_PASSWORD_ISNOTChineseCharacter="密码中含有汉字!";
	static final String Login_ACCOUNTORPASSWORD_ISNULL="密码或用户名为空";
	static final String Login_ACCOUNT_LESSThAN6="密码不足6位！";
	static final String Login_ACCOUNT_MOREThAN6="密码超过6位！";

	/**
	 * 用正则表达式来处理电话号
	 * @param mobiles
	 * @return
	 */
	private static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");    
		Matcher m = p.matcher(mobiles);  

		return m.matches();  	  
	} 
	public static boolean verifyPhoneNumber(String verifyText) { 
		if (!isMobileNO(verifyText)) {
			alertView = new AlertView(PHONE_NUM_IS_UNCORRECT_WARNING);
			return false;
		}

		return true;
	}
//	public static boolean charIsNum(String verify) {
//		char[] chs = verify.toCharArray();
//		for (int i = 0; i < chs.length; i++) {
//			if (chs[i]<'0') {
//
//			}
//		}
//		return true;
//	}
	/**
	 *
	 * @param text
	 * @param text2
	 * @param string
	 * @return
	 */
	public static boolean verifyText(String text, String text2, String string) {
		if (text.equals("") || text2.equals("") || string.equals("")) {
			alertView = new AlertView("不能为空!");
			return false;
		}
		return true;
	}
	/**
	 * 验证输入的单价是否是0
	 * @param string
	 * @return
	 */
	
	private static boolean isZero(String string) {
		int count = 0;

		char[] chs = string.toCharArray();
		boolean flag = false;
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '.') {
				flag = true;
				continue;
			}
			else if (chs[i]=='0') {
				++count;
			}
		}

		if (count != chs.length) {
			return true;
		}else if (flag && count == chs.length-1) {
			return false;
		}
		return false;
	}
	public static Boolean isInput(String account,String password) {
		String reGEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(reGEx);

		Matcher matcherAccount = pat.matcher(account);
		Matcher matcherPassword = pat.matcher(password);
//zhanghaomima
		if (matcherAccount.find())
		{
			alertView = new AlertView(Login_ACCOUNT_ISNOTChineseCharacter);
			return false;
		}else if (matcherPassword.find()) {
			alertView = new AlertView(Login_PASSWORD_ISNOTChineseCharacter);

		}else if (account ==null && password ==null) 
		{
			alertView = new AlertView(Login_ACCOUNTORPASSWORD_ISNULL);
			return false;	
		}else if (password.length() <6) {
			alertView = new AlertView(Login_ACCOUNT_ISNOTChineseCharacter);
			return false;

		}else if (password.length()>6) {
			alertView = new AlertView(Login_ACCOUNT_MOREThAN6);
			return false;
		}

		return true;

	}
}

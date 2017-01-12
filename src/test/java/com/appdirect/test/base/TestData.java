package com.appdirect.test.base;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.appdirect.util.ExcelUtils;



public class TestData extends TestBaseSetup{
	
	static ExcelUtils excel;
	
	
	@DataProvider(name = "users")
	public static Object[][] users(Method m) throws IOException{
	
		String sheetName=m.getName().equals("verfySignUpforInvalidUsers")?"InvalidUsers":m.getName().equals("verfySignUpforValidUsers")?"ValidUsers":"RegUsers";
		excel = new ExcelUtils(sheetName);
		return ExcelUtils.get2DObjectArr();
	}
	

}

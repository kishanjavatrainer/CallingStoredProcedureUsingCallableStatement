package com.infotech.client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import com.infotech.util.DBUtil;

public class ClientTest {

	public static void main(String[] args) {
		String SQL = "CALL get_order_by_cust(?,?,?,?,?)";
		try(Connection connection = DBUtil.getConnection();CallableStatement cs = 
				connection.prepareCall(SQL)) {
			int custId =145;
			cs.setInt(1, custId);
			
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.registerOutParameter(5, Types.INTEGER);
			
			cs.execute();
			
			System.out.println("Shipped order:"+cs.getInt(2));
			System.out.println("Canceled order:"+cs.getInt(3));
			System.out.println("Resolved order:"+cs.getInt(4));
			System.out.println("Disputed order:"+cs.getInt(5));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

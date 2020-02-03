package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkJava {
	public String ConnectDB(String first) {
		
	    String data = Integer.toString(0);
	    
	    // DB
	    String driver = "JP.co.Hitachi.soft.HiRDB.JDBC.HiRDBDriver";
        try {
            Class.forName(driver);
        }catch(Exception e){
        	e.printStackTrace();
        	return "0";
        } 
 
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        System.setProperty("jdbc.drivers", "JP.co.Hitachi.soft.HiRDB.JDBC.HiRDBDriver");
        
        try {
            // 
        	con = DriverManager.getConnection("jdbc:hitachi:hirdb://DBID=22200,DBHOST=hirdbserver,USER=USER1,PASSWORD=USER1");
            
            pstmt = con.prepareStatement("SELECT ITEM FROM CONNTEST WHERE ITEMNO = " + first);
		    // ResultSet取得
		    rs = pstmt.executeQuery();
            
		    while(rs.next()) {
				System.out.print(rs.getString("ITEM"));
				data = rs.getString("ITEM");
		    }
		    // ResultSetの解放
			rs.close();
			// PreparedStatementの解放
			pstmt.close();
			// 
			con.close();
			
			return data;
			
        }catch(SQLException e){
			//
			e.printStackTrace();
			// SQLSTATEの出力
			System.out.println("SQLSTATE=" + e.getSQLState());
			// SQLCODEの出力
			System.out.println("SQLCODE=" + e.getErrorCode());
			// SQLERRM(SQLメッセージ)の出力
			System.out.println("SQLERRM=" + e.getMessage());
			
			return "99";
        }
	}
}

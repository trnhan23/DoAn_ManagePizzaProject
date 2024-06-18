package QuanLyPizza.DAO;

import MyCustom.MyDialog;
import com.mysql.jdbc.Driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnect {
	
	
	private final String serverName = "ADMIN-PC";
	private final String dbName = "QLPizza";
	private final String portNumber = "1433";
	private final String instance = "";// MSSQLSERVER LEAVE THIS ONE
	private final String userID = "sa";
	private final String password = "Nguyentam_@1984";
	public static Connection conn = null;
	
	public Connection getConnection()  {
		
		String url = "jdbc:sqlserver://" + serverName + ":" +portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
		System.out.print(url);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, userID, password);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return conn;
		}

    
 

    /*public MyConnect() {
        docFileText();

        String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        Properties pro = new Properties();
        pro.put("user", userName);
        pro.put("password", password);
        try {
            com.mysql.jdbc.Driver driver = new Driver();
            conn = driver.connect(strConnect, pro);
        } catch (SQLException ex) {
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }

    }

    private void docFileText() {
        // Xử lý đọc file để lấy ra 4 tham số
        severName = "";
        dbName = "";
        userName = "";
        password = "";

        try {
            FileInputStream fis = new FileInputStream("ConnectVariable.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            severName = br.readLine();
            dbName = br.readLine();
            userName = br.readLine();
            password = br.readLine();

            if (password == null) {
                password = "";
            }

        } catch (Exception e) {
        }*/
    }


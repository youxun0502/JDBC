package tw.ispan.jdbc.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo7SaveImage {

	private Connection conn;

	public void createConnection() throws SQLException {
		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;TrustServerCertificate=True;";
		String account = "sa";
		String password = "Passw0rd!!";

		this.conn = DriverManager.getConnection(connStr, account, password);
		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("連線開啟");
		}
	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("關閉連線OK");

		}
	}

	public void saveImageInDB() throws SQLException, IOException {
		String sql = "insert into gallery (image_name, image_file) values (?,?)";
		File file = new File("C:\\JDBC\\1234.avif");
		FileInputStream fis = new FileInputStream(file);

		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, "範例一");
		preState.setBinaryStream(2, fis);

		int row = preState.executeUpdate();
		System.out.println("新增了 " + row + "筆資料");

		preState.close();
		fis.close();
	}

	public void retrieveImage() throws SQLException, IOException {
		String sql = "select image_file from gallery where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, 1);

		ResultSet rs = preState.executeQuery();
		rs.next();

		rs.getBlob("image_file");
		Blob blob = rs.getBlob("image_file");

		FileOutputStream fos = new FileOutputStream("C:\\JDBC\\output\\ out666.avif");
		fos.write(blob.getBytes(1, (int) blob.length()));

		System.out.println("FIle Output OK!!");

		fos.close();
		rs.close();
		preState.close();
	}

	public void retrieveImage2() throws SQLException, IOException {
		String sql = "select image_file from gallery where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		
	
		ResultSet rs = preState.executeQuery();
		
		rs.next();

		Blob blob = rs.getBlob("image_file");//image_file 為sql欄位
		
		FileOutputStream fos = new FileOutputStream("C:\\JDBC\\output\\ out6661.avif");
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		bos.write(blob.getBytes(1, (int) blob.length()));
		
		bos.flush();//強制寫出記憶體輸出至硬碟(小於8Kb檔案必備)
		
		System.out.println("輸出檔案OK!!");
		
		bos.close();
		fos.close();
		rs.close();
		preState.close();
	}

	public static void main(String[] args) {
		Demo7SaveImage demo7 = new Demo7SaveImage();

		try {
			demo7.createConnection();
//			demo7.saveImageInDB();
//			demo7.retrieveImage();
			demo7.retrieveImage2();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				demo7.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

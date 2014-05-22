package escola_projeto;

import java.sql.Connection;
import java.sql.DriverManager;

public class conectaMysql {
	private String ip = "jdbc:mysql://localhost/carlosno";
	private String usuario = "root";
	private String senha = "Senai1234";
	private String driver = "com.mysql.jdbc.Driver";
	private Connection con;

	public Connection iniciaConexao(){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(ip, usuario, senha);
			System.out.println("Conectado!");
			
		}catch(Exception e){
			System.out.println("Não conectou!");
			e.printStackTrace();
		}
		return con;
	}
}
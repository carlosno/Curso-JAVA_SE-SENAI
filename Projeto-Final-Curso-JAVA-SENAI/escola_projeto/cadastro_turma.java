package escola_projeto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import swing.conectaMysql;

public class cadastro_turma extends JFrame implements ActionListener {
 
	JFrame tela = new JFrame ("Cadastro Turma");
	
	JTable tabela;
	JScrollPane barraRolagem = new JScrollPane();
	
	JLabel Texto1 = new JLabel ("Codigo:");
	JLabel Texto2 = new JLabel ("Curso");
	JLabel Texto3 = new JLabel ("Periodo"); 
	
	JTextField campoCodigo = new JTextField();
	JTextField campoCurso = new JTextField();
	JTextField campoPeriodo = new JTextField();
	
	JButton botao = new JButton("Cadastrar");
	JButton botao1 = new JButton("Consultar");
	JButton botao2 = new JButton("Remover");
	
	
	public void montatela(){
		tela.setSize(480, 650);
		tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tela.setLayout(new FlowLayout());
		tela. setLocationRelativeTo(null);
		
		tabela = new MontaTabela().criar("select * from turma");
		tabela.setSize(400, 600);
		barraRolagem = new JScrollPane(tabela);
		
		campoCodigo.setColumns(40);
		campoCurso.setColumns(40);
		campoPeriodo.setColumns(40);
		
		botao.addActionListener(this);
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		
		tela.add(Texto1);
		tela.add(campoCodigo);
		tela.add(Texto2);
		tela.add(campoCurso);
		tela.add(Texto3);
		tela.add(campoPeriodo);
		
		tela.add(botao);
		tela.add(botao1);
		tela.add(botao2);
		tela.add(barraRolagem);
		tela.setVisible(true);
		
}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		
		
		
		conectaMysql conecta = new conectaMysql();
		if(arg0.getSource()==botao){
			
			String comandoSQL = "INSERT INTO turma VALUES "
					+ "(?, ?, ?)";
		
			Connection con = conecta.iniciaConexao();
			
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				//Passando os valores para cada uma das ?
				ps.setString(1, campoCodigo.getText());
				ps.setString(2, campoCurso.getText());
				ps.setString(3, campoPeriodo.getText());
				
				
				if(ps.executeUpdate() != 0){
					System.out.println("Cadastrado!");
					JOptionPane.showMessageDialog(null," Cadastrado !");
					campoCodigo.setText("");
					campoCurso.setText("");
					campoPeriodo.setText("");
									
				}
				else{
					System.out.println("N�o cadastrado!");
					JOptionPane.showMessageDialog(null," N�o Cadastrado !");}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		if(arg0.getSource()==botao1){
			
			String comandoSQL = "SELECT * FROM turma where cod=? ";
			Connection con = conecta.iniciaConexao();
		
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoCodigo.getText());
			
			ResultSet resultado = ps.executeQuery();			
			
			while(resultado.next()){//percorendo objeto resultado com o select do banco
			campoCodigo.setText(resultado.getString("cod"));
			campoCurso.setText(resultado.getString("curso"));
			campoPeriodo.setText(resultado.getString("periodo"));
			
			
			}
			
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		
		if(arg0.getSource()== botao2){
			String comandoSQL = "DELETE FROM turma where cod=?";
			Connection con = conecta.iniciaConexao();
			try{
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoCodigo.getText());
				
				if(ps.executeUpdate() != 0){
					JOptionPane.showMessageDialog(null, "Removido");
					campoCodigo.setText("");
					campoCurso.setText("");
					campoPeriodo.setText("");
					
					
				}else{
					JOptionPane.showMessageDialog(null, "N�o removido");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			}	
		
	
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new cadastro_turma().montatela();
	
	}

}

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

public class cadastro_disciplina extends JFrame implements ActionListener {

JFrame tela = new JFrame("Cadastro Disciplinas");

	JTable tabela;
	JScrollPane barraRolagem = new JScrollPane();
	
	JLabel texto1 = new JLabel("Codigo:");
	JLabel texto2 = new JLabel("Matricula Professor:");
	JLabel texto3 = new JLabel("Ementa:");
	JLabel texto4 = new JLabel("Nome:");
	
	JTextField campoCodigo = new JTextField();
	JTextField campoProfessor = new JTextField();
	JTextField campoEmenta = new JTextField();
	JTextField campoNome = new JTextField();
	
	JButton botao = new JButton("Cadastrar");
	JButton botao1 = new JButton("Consultar");
	JButton botao2 = new JButton("Remover");
	
	public void montaTela(){
		tela.setSize(480, 700);
		tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tela. setLocationRelativeTo(null);
		tela.setLayout(new FlowLayout());
		
		tabela = new MontaTabela().criar("select * from disciplina");
		tabela.setSize(400, 300);
		barraRolagem = new JScrollPane(tabela);
		
		campoCodigo.setColumns(40);
		campoProfessor.setColumns(40);
		campoEmenta.setColumns(40);
		campoNome.setColumns(40);
		
		botao.addActionListener(this);
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		tela.add(texto1);
		tela.add(campoCodigo);
		tela.add(texto2);
		tela.add(campoProfessor);
		tela.add(texto3);
		tela.add(campoEmenta);
		tela.add(texto4);
		tela.add(campoNome);
		
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
			
			String comandoSQL = "INSERT INTO disciplina VALUES "
					+ "(?, ?, ?,?)";
		
			Connection con = conecta.iniciaConexao();
			
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				//Passando os valores para cada uma das ?
				ps.setString(1, campoCodigo.getText());
				ps.setString(2, campoProfessor.getText());
				ps.setString(3, campoEmenta.getText());
				ps.setString(4, campoNome.getText());
				
				if(ps.executeUpdate() != 0){
					System.out.println("Cadastrado!");
					JOptionPane.showMessageDialog(null," Cadastrado !");
					campoCodigo.setText("");
					campoProfessor.setText("");
					campoEmenta.setText("");
					campoNome.setText("");
					
				}
				else{
					System.out.println("Não cadastrado!");
					JOptionPane.showMessageDialog(null," Não Cadastrado !");}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(arg0.getSource()==botao1){
			
			String comandoSQL = "SELECT * FROM disciplina where cod=? ";
			Connection con = conecta.iniciaConexao();
		
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoCodigo.getText());
			
			ResultSet resultado = ps.executeQuery();			
			
			while(resultado.next()){//percorendo objeto resultado com o select do banco
			campoCodigo.setText(resultado.getString("cod"));
			campoProfessor.setText(resultado.getString("professores_matricula"));
			campoEmenta.setText(resultado.getString("ementa"));
			campoNome.setText(resultado.getString("nome"));
			
			}
			
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
	
	
		
		if(arg0.getSource()== botao2){
			String comandoSQL = "DELETE FROM disciplina where cod=?";
			Connection con = conecta.iniciaConexao();
			try{
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoCodigo.getText());
				
				if(ps.executeUpdate() != 0){
					JOptionPane.showMessageDialog(null, "Removido");
					campoCodigo.setText("");
					campoProfessor.setText("");
					campoEmenta.setText("");
					campoNome.setText("");
					
				}else{
					JOptionPane.showMessageDialog(null, "Não removido");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			}	
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cadastro_disciplina().montaTela();
	}

}

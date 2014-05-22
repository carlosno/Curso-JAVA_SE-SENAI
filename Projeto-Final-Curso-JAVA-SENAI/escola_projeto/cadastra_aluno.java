package escola_projeto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import swing.conectaMysql;

public class cadastra_aluno extends JFrame implements ActionListener {
	JFrame tela = new JFrame("Cadastro de Alunos");
	
	JTable tabela;
	JScrollPane barraRolagem = new JScrollPane();
	
	
	JLabel texto1 = new JLabel("Matricula:");
	JLabel texto2 = new JLabel("Turma:");
	JLabel texto3 = new JLabel("Nome:");
	JLabel texto4 = new JLabel("Curso:");
	
	JTextField campoMatricula = new JTextField();
	JTextField campoTurma = new JTextField();
	JTextField campoNome = new JTextField();
	JTextField campoCurso = new JTextField();
	
	JButton botao = new JButton("Cadastrar");
	JButton botao1 = new JButton("Consultar");
	JButton botao2 = new JButton("Remover");
	
	public void montaTela(){
		tela.setSize(480, 700);
		tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tela.setLayout(new FlowLayout());
		tela. setLocationRelativeTo(null);
		
		campoMatricula.setColumns(40);
		campoTurma.setColumns(40);
		campoNome.setColumns(40);
		campoCurso.setColumns(40);
		
		tabela = new MontaTabela().criar("select Matricula, turma_cod as Código, Nome, Curso from alunos");
		tabela.setSize(400, 300);
		barraRolagem = new JScrollPane(tabela);
		

		
		botao.addActionListener(this);
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		tela.add(texto1);
		tela.add(campoMatricula);
		tela.add(texto2);
		tela.add(campoTurma);
		tela.add(texto3);
		tela.add(campoNome);
		tela.add(texto4);
		tela.add(campoCurso);
		
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
			
			String comandoSQL = "INSERT INTO alunos VALUES "
					+ "(?, ?, ?, ?)";
		
			Connection con = conecta.iniciaConexao();
			
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				//Passando os valores para cada uma das ?
				ps.setString(1, campoMatricula.getText());
				ps.setString(2, campoTurma.getText());
				ps.setString(3, campoNome.getText());
				ps.setString(4, campoCurso.getText());
				
				if(ps.executeUpdate() != 0){
					System.out.println("Cadastrado!");
					JOptionPane.showMessageDialog(null," Cadastrado !");
					campoMatricula.setText("");
					campoTurma.setText("");
					campoNome.setText("");
					campoCurso.setText("");
					tela.remove(barraRolagem);
					tela.repaint();
					tabela = new MontaTabela().criar("select * from alunos");
					tabela.setSize(400, 300);
					barraRolagem = new JScrollPane(tabela);
					tela.add(barraRolagem);
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
			
			String comandoSQL = "SELECT * FROM alunos where matricula=? ";
			Connection con = conecta.iniciaConexao();
		
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoMatricula.getText());
			
			ResultSet resultado = ps.executeQuery();			
			
			while(resultado.next()){//percorendo objeto resultado com o select do banco
			campoMatricula.setText(resultado.getString("matricula"));
			campoTurma.setText(resultado.getString("turma_cod"));
			campoNome.setText(resultado.getString("nome"));
			campoCurso.setText(resultado.getString("curso"));
			
			}
			
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			}
		if(arg0.getSource()== botao2){
			String comandoSQL = "DELETE FROM alunos where matricula=?";
			Connection con = conecta.iniciaConexao();
			try{
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoMatricula.getText());
				
				if(ps.executeUpdate() != 0){
					JOptionPane.showMessageDialog(null, "Removido");
					campoMatricula.setText("");
					campoTurma.setText("");
					campoNome.setText("");
					campoCurso.setText("");
					tela.remove(barraRolagem);
					tela.repaint();
					tabela = new MontaTabela().criar("select * from alunos");
					tabela.setSize(400, 300);
					barraRolagem = new JScrollPane(tabela);
					tela.add(barraRolagem);
					
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

		
		new cadastra_aluno().montaTela();
	}

}

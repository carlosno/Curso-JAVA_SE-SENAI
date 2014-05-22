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

public class cadastra_aluno_disciplina extends JFrame implements ActionListener {
	JFrame tela = new JFrame("Cadastro Disciplinas Para Alunos");
	
	JTable tabela;
	JScrollPane barraRolagem = new JScrollPane();
	
	JLabel texto1 = new JLabel("Matricula do Aluno:");
	JLabel texto2 = new JLabel("Codigo da Disciplina:");
	JLabel texto3 = new JLabel("Nota A:");
	JLabel texto4 = new JLabel("Nota B:");
	JLabel texto5 = new JLabel("Frequencia:");
	
	JTextField campoMatricula = new JTextField();
	JTextField campoDisciplina = new JTextField();
	JTextField campoNotaA = new JTextField();
	JTextField campoNotaB = new JTextField();
	JTextField campoFrequencia = new JTextField();
	
	JButton botao = new JButton("Cadastrar");
	JButton botao1 = new JButton("Consultar");
	JButton botao2 = new JButton("Remover");
	JButton botao3 = new JButton("Atualizar");
	
	public void montaTela(){
		tela.setSize(480, 730);
		tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tela.setLayout(new FlowLayout());
		tela. setLocationRelativeTo(null);
		
		campoMatricula.setColumns(40);
		campoDisciplina.setColumns(40);
		campoNotaA.setColumns(40);
		campoNotaB.setColumns(40);
		campoFrequencia.setColumns(40);
		
		tabela = new MontaTabela().criar("select * from alunos_disciplina");
		tabela.setSize(400, 300);
		barraRolagem = new JScrollPane(tabela);
		
		botao.addActionListener(this);
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		botao3.addActionListener(this);
		tela.add(texto1);
		tela.add(campoMatricula);
		tela.add(texto2);
		tela.add(campoDisciplina);
		tela.add(texto3);
		tela.add(campoNotaA);
		tela.add(texto4);
		tela.add(campoNotaB);
		tela.add(texto5);
		tela.add(campoFrequencia);
		
		tela.add(botao);
		tela.add(botao1);
		tela.add(botao2);
		tela.add(botao3);
		tela.add(barraRolagem);
		tela.setVisible(true);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		conectaMysql conecta = new conectaMysql();
		if(arg0.getSource()==botao){
		String comandoSQL = "INSERT INTO alunos_disciplina VALUES "
					+ "(?, ?, ?, ?, ?)";
			Connection con = conecta.iniciaConexao();
			
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				//Passando os valores para cada uma das ?
				ps.setString(1, campoMatricula.getText());
				ps.setString(2, campoDisciplina.getText());
				ps.setString(3, campoNotaA.getText());
				ps.setString(4, campoNotaB.getText());
				ps.setString(5, campoFrequencia.getText());
				
				if(ps.executeUpdate() != 0){
					System.out.println("Cadastrado!");
					JOptionPane.showMessageDialog(null," Cadastrado !");
					campoMatricula.setText("");
					campoDisciplina.setText("");
					campoNotaA.setText("");
					campoNotaB.setText("");
					campoFrequencia.setText("");
					
				}
				else{
					System.out.println("Não cadastrado!");
					JOptionPane.showMessageDialog(null," Não Cadastrado !");}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		if(arg0.getSource()== botao1){
			
			String comandoSQL = "SELECT * FROM alunos_disciplina where Alunos_matricula=? ";
			Connection con = conecta.iniciaConexao();
		
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoMatricula.getText());
			
			ResultSet resultado = ps.executeQuery();			
			
			while(resultado.next()){//percorendo objeto resultado com o select do banco
			campoMatricula.setText(resultado.getString("Alunos_matricula"));
			campoDisciplina.setText(resultado.getString("disciplina_cod"));
			campoNotaA.setText(resultado.getString("notaA"));
			campoNotaB.setText(resultado.getString("notaB"));
			campoFrequencia.setText(resultado.getString("frequencia"));
			}
			
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		
		if(arg0.getSource()== botao2){
			String comandoSQL = "DELETE FROM alunos_disciplina where Alunos_matricula=?";
			Connection con = conecta.iniciaConexao();
			try{
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				ps.setString(1, campoMatricula.getText());
				
				if(ps.executeUpdate() != 0){
					JOptionPane.showMessageDialog(null, "Removido");
					campoMatricula.setText("");
					campoDisciplina.setText("");
					campoNotaA.setText("");
					campoNotaB.setText("");
					campoFrequencia.setText("");
					
				}else{
					JOptionPane.showMessageDialog(null, "Não removido");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			}	
	
	
		if(arg0.getSource() == botao3){
			String comandoSQL = "UPDATE alunos_disciplina SET NotaA = ?, NotaB = ?, "
					+ " frequencia = ?";
			Connection con = conecta.iniciaConexao();
			try {
				PreparedStatement ps = con.prepareStatement(comandoSQL);
				//Passando os valores para cada uma das ?
				ps.setString(1, campoNotaA.getText());
				ps.setString(2, campoNotaB.getText());
				ps.setString(3, campoFrequencia.getText());
				
				if(ps.executeUpdate() != 0)
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
				else
					JOptionPane.showMessageDialog(null, "Não atualizado!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

			}
		
		
		
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		new cadastra_aluno_disciplina().montaTela();
	
	}

}

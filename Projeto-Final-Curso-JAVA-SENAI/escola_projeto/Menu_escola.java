package escola_projeto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


 


public class Menu_escola extends JFrame implements ActionListener {

	
	//Componentes
		JFrame tela = new JFrame("Projeto Escola");
		JMenuBar barraMenu = new JMenuBar();
		
		//Itens do Menu Cadastro
		JMenu menu1 = new JMenu("Cadastros");
		JMenuItem item1Menu1 = new JMenuItem("Cadastrar Aluno");
		JMenuItem item2Menu1 = new JMenuItem("Cadastrar Disciplina do Aluno");
		JMenuItem item3Menu1 = new JMenuItem("Cadastrar Disciplina");
		JMenuItem item4Menu1 = new JMenuItem("Cadastrar Professor");
		JMenuItem item5Menu1 = new JMenuItem("Cadastrar Turma");
		JMenuItem item6Menu1 = new JMenuItem("Cadastrar Turma Disciplinas");
		JMenuItem item7Menu1 = new JMenuItem("Sair");
		
		//botão
		
		JButton botao = new JButton("Cadastrar Disciplina Do Aluno");
		JButton botao1 = new JButton("Cadastrar Aluno");
		JButton botao2 = new JButton("Cadastrar Disciplina");
		JButton botao3 = new JButton("Cadastrar Professor");
		JButton botao4 = new JButton("Cadastrar Disciplinas Da Turma");
		JButton botao5 = new JButton("Cadastrar Turma");
	
		
		//Montagem e configuração da tela
		public void montaTela(){
			tela.setDefaultCloseOperation(EXIT_ON_CLOSE);
			tela.setLayout(null);
			tela. setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			
			//Botão
			botao.addActionListener(this);
			botao1.addActionListener(this);
			botao2.addActionListener(this);
			botao3.addActionListener(this);
			botao4.addActionListener(this);
			botao5.addActionListener(this);
			
			//         margemX, margemY, largura, altura
			barraMenu.setBounds(0, 0, 2000, 30);
			
			botao.setBounds(30, 50, 280, 30);
			botao1.setBounds(30, 100, 280, 30);
			botao2.setBounds(30, 150, 280, 30);
			botao3.setBounds(30, 200, 280, 30);
			botao4.setBounds(30, 250, 280, 30);
			botao5.setBounds(30, 300, 280, 30);
			
			//botão
			tela.add(botao);
			tela.add(botao1);
			tela.add(botao2);
			tela.add(botao3);
			tela.add(botao4);
			tela.add(botao5);
			
//			//Configuração do tamanho da barra
//			barraMenu.setSize(400, 20); //largura, altura
			
			//Configurar os itens do menu para disparar eventos
			item1Menu1.addActionListener(this);
			item2Menu1.addActionListener(this);
			item3Menu1.addActionListener(this);
			item4Menu1.addActionListener(this);
			item5Menu1.addActionListener(this);
			item6Menu1.addActionListener(this);
			item7Menu1.addActionListener(this);
			
			//Adicionar os itens dentro do Menu 1
			menu1.add(item1Menu1);
			menu1.add(item2Menu1);
			menu1.add(item3Menu1);
			menu1.add(item4Menu1);
			menu1.add(item5Menu1);
			menu1.add(item6Menu1);		
			menu1.addSeparator();
			menu1.add(item7Menu1);
			
		
			
			//Adicionar o menu1 e menu2 na barra
			barraMenu.add(menu1);

			//Adicionar a barra na tela
			tela.add(barraMenu);
			
			tela.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
						
			if(arg0.getSource() == item1Menu1 ||arg0.getSource() == botao1){
				cadastra_aluno cc = new cadastra_aluno();
				cc.montaTela();
				}
		
			if(arg0.getSource() == item2Menu1 ||arg0.getSource() == botao){
				cadastra_aluno_disciplina cc = new cadastra_aluno_disciplina();
				cc.montaTela();
				}
		
			if(arg0.getSource() == item3Menu1 ||arg0.getSource() == botao2){
				cadastro_disciplina cc = new cadastro_disciplina();
				cc.montaTela();
				}
		
			if(arg0.getSource() == item4Menu1 || arg0.getSource() == botao3){
				cadastro_professor cc = new cadastro_professor();
				cc.montaTela();
				}
		
			if(arg0.getSource() == item5Menu1 || arg0.getSource() == botao4 ){
				cadastro_turma cc = new cadastro_turma();
				cc.montatela();
				}
			

			if(arg0.getSource() == item6Menu1 || arg0.getSource() == botao5){
				cadastro_turma_disciplina cc = new cadastro_turma_disciplina();
				cc.montatela();
				}
			
			
			if(arg0.getSource() == item7Menu1){
				
				System.exit(0);
				}
		}
			
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Menu_escola().montaTela();
	
	}

}

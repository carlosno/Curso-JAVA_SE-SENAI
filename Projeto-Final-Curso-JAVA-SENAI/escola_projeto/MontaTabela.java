package escola_projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class MontaTabela {

	//Método para montagem de um JTable de acordo com o comando SELECT
	public JTable criar(String comandoSelect){
		//Componente JTable que será preenchido e retornado
		JTable tabela = new JTable(); //import javax.swing.JTable;
		
		//Classe para modelagem da tabela com colunas e linhas
		//DefaultTableModel - import java.swing.table.*;
		DefaultTableModel modelo = new DefaultTableModel();
		
		//Conexão com o banco de dados
		//import java.sql.*;
		Connection conexao = new conectaMysql().iniciaConexao();
		
		try{
			PreparedStatement comando = 
					conexao.prepareStatement(comandoSelect);

			//Armazenar os dados retornados pelo SELECT no ResultSet
			ResultSet resultado = comando.executeQuery();
			
			//Com os dados no ResultSet, podemos recuperar os dados
			//e a estrutura da tabela
			//A classe ResultSetMetaData armazena a parte estrutural
			ResultSetMetaData estrutura = resultado.getMetaData();
			
			//Configuração da parte estrutural da tabela (colunas)
			//Contagem de colunas retornado pelo SELECT
			int quantidadeColunas = estrutura.getColumnCount();
			
			//Vetor de String para armazenar o no das colunas
			//O vetor precisa ter o mesmo tamanho da variável
			//'quantidadeColunas'
			String nomeColunas[] = new String[quantidadeColunas];
			
			//Laço para atribuir o nome das colunas em cada posição do vetor
			for(int i=1 ; i<=quantidadeColunas ; i++){
				//A contagem de colunas no SQL começa em 1
				//A contagem dos índices no vetor começa em 0
				//Cada nome de coluna da estrutura e atribuído ao vetor
				nomeColunas[i-1] = estrutura.getColumnLabel(i);
			}
			
			//Ao final do processo, atribui-se as colunas ao modelo
			modelo.setColumnIdentifiers(nomeColunas); //Fim das colunas
			
			//Início da montagem das linhas, mesmo código do "TestaSelect"
			while(resultado.next()){
				//Criar um vetor para armazenar uma linha, isto é, o
				//valor de cada coluna para a linha
				String valoresLinha[] = new String[quantidadeColunas];
				//Laço para percorrer cada coluna de uma linha e recuperar
				//os valores
				for(int i=1 ; i<=quantidadeColunas ; i++){
					valoresLinha[i-1] = resultado.getString(i);
				}
				//Adicionando no modelo a linha com todas as
				//suas colunas preenchidas
				modelo.addRow(valoresLinha);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//Colocar o modelo dentro da tabela
		tabela.setModel(modelo);
		
		return tabela;
	}
}


















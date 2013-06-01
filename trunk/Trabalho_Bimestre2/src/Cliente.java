import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {
	
	private int codigo;
	private String nome;
	private String endereco;
	private String contato;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	/**
	 * Construtor com parâmetros.
	 * 
	 * @param codigo - o código do cliente.
	 * @param nome - o nome do cliente.
	 * @param endereco - o endereço do cliente.
	 * @param contato - o contato do cliente.
	 */
	public Cliente(int codigo, String nome, String endereco, String contato) {
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.contato = contato;
	}
	
	@Override
	public String toString() {
		return "Cliente[" + this.codigo + "] = \n\tNome: " + this.nome + "\n\tEndereço: " + this.endereco + "\n\tContato: " + this.contato;
	}
	
	/**
	 * Prepara uma String com os dados do cliente pronta para ser gravada em arquivo.
	 * 
	 * @return a String com os dados do cliente.
	 */
	public String toStringParaArquivo() {
		return this.codigo + " | " + this.nome + " | " + this.endereco + " | " + this.contato;
	}
	
	/**
	 * Cria uma lista de clientes a partir de um arquivo.
	 * 
	 * @param arquivo - o endereço do arquivo que contém os clientes.
	 * 
	 * @return uma lista dos clientes do arquivo.
	 */
	static ListaEncadeadaDinamica<Cliente> lerDados(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<Cliente> provisorio = new ListaEncadeadaDinamica<Cliente>();
		
		try {
			in = new BufferedReader(new FileReader(arquivo));
			String linhaAtual;
			while((linhaAtual = in.readLine()) != null) {
				provisorio.adicionarFinal(montar(linhaAtual));
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Monta um cliente a partir de uma linha com todos os dados.
	 * 
	 * @param linhaArquivo - a linha com todos os dados.
	 * 
	 * @return um cliente com os dados da linha passada.
	 */
	private static Cliente montar(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|");
		Cliente provisorio = new Cliente(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), campos[3].trim());
		return provisorio;
	}
	
	/**
	 * Grava em um arquivo os clientes de uma lista.
	 * 
	 * @param arquivo - o endereço do arquivo que se deseja salvar os clientes.
	 * @param listaClientes - a lista de clientes a ser salva no arquivo.
	 */
	static void salvarArquivo(String arquivo, ListaEncadeadaDinamica<Cliente> listaClientes) {
		
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(arquivo));
			Cliente provisorio;
			
			for (int i = 0; i < listaClientes.tamanho(); i++) {
				provisorio = listaClientes.buscarPorPosicao(i+1);
				out.write(provisorio.toStringParaArquivo());
				out.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		
	}

}

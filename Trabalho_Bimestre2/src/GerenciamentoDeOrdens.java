import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class GerenciamentoDeOrdens {
	
	private ListaEncadeadaDinamica<Cliente> listaClientes;
	private ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico;
	private int codigoAtual;
	
	public ListaEncadeadaDinamica<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ListaEncadeadaDinamica<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ListaEncadeadaDinamica<OrdemDeServico> getListaOrdensDeServico() {
		return listaOrdensDeServico;
	}

	public void setListaOrdensDeServico(
			ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico) {
		this.listaOrdensDeServico = listaOrdensDeServico;
	}

	public int getCodigoAtual() {
		return codigoAtual;
	}

	public void setCodigoAtual(int codigoAtual) {
		this.codigoAtual = codigoAtual;
	}

	/**
	 * Cria uma lista de clientes a partir de um arquivo.
	 * 
	 * @param arquivo - endereço do arquivo que contém os clientes.
	 * 
	 * @return uma lista dos clientes do arquivo.
	 */
	private ListaEncadeadaDinamica<Cliente> lerDadosClientes(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<Cliente> provisorio = new ListaEncadeadaDinamica<Cliente>();
		
		try {
			in = new BufferedReader(new FileReader(arquivo));
			String linhaAtual;
			while((linhaAtual = in.readLine()) != null) {
				provisorio.adicionarFinal(this.montarCliente(linhaAtual));
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
	 * Cria uma lista de ordens de serviço a partir de um arquivo.
	 * 
	 * @param arquivo - endereço do arquivo que contém as ordens de serviço.
	 * 
	 * @return uma lista das ordens de serviço do arquivo.
	 */
	private ListaEncadeadaDinamica<OrdemDeServico> lerDadosOrdens(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		
		try {
			in = new BufferedReader(new FileReader(arquivo));
			String linhaAtual;
			while((linhaAtual = in.readLine()) != null) {
				provisorio.adicionarFinal(this.montarOrdemDeServico(linhaAtual));
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
	private Cliente montarCliente(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|\\|\\|");
		Cliente provisorio = new Cliente(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), campos[3].trim());
		return provisorio;
	}
	
	/**
	 * Monta uma ordem de serviço a partir de uma linha com todos os dados.
	 * 
	 * @param linhaArquivo - a linha com todos os dados.
	 * 
	 * @return uma ordem de serviço com os dados da linha passada.
	 */
	private OrdemDeServico montarOrdemDeServico(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|\\|\\|\\|\\|\\|");
		OrdemDeServico provisorio = new OrdemDeServico(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), 
				this.conversaoStringEmDate(campos[3].trim()), this.conversaoStringEmDate(campos[4].trim()), this.criarLista(campos[5].trim()), 
				campos[6].trim());
		return provisorio;
	}
	
	/**
	 * Cria uma lista de Integer a partir de uma linha dada.
	 * 
	 * @param linhaConverter - a linha com os dados a serem convertidos.
	 * 
	 * @return a lista com os dados da linha.
	 */
	private ListaEncadeadaDinamica<Integer> criarLista(String linhaConverter) {
		ListaEncadeadaDinamica<Integer> provisorio = new ListaEncadeadaDinamica<Integer>();
		
		String[] campos = linhaConverter.split("");
		
		for (int i = 0; i < campos.length; i++) {
			provisorio.adicionarFinal(Integer.parseInt(campos[i]));
		}
		
		return provisorio;
	}
	
	/**
	 * Converte uma String em um Date.
	 * 
	 * @param aSerConvertida - a String a ser convertida.
	 * 
	 * @return um objeto da classe Date com a data a partir da String.
	 */
	@SuppressWarnings("deprecation")
	private Date conversaoStringEmDate(String aSerConvertida) {
		String[] campos = aSerConvertida.split("\\/||/");
		return new Date(Integer.parseInt(campos[0]), Integer.parseInt(campos[1].trim()), 
				Integer.parseInt(campos[2].trim()));
	}

	/**
	 * Construtor padrão.
	 * 
	 * @param arquivoClientes - o endereço do arquivo que contém a lista de clientes.
	 * @param arquivoOrdens - o endereço do arquivo que contém a lista de ordens de serviço.
	 */
	public GerenciamentoDeOrdens(String arquivoClientes, String arquivoOrdens) {
		this.listaClientes = this.lerDadosClientes(arquivoClientes);
		this.listaOrdensDeServico = this.lerDadosOrdens(arquivoOrdens);
	}

	/**
	 * Cadastra um novo cliente na lista de clientes.
	 * 
	 * @param nome - o nome do novo cliente.
	 * @param endereco - o endereço do novo cliente.
	 * @param contato - o contato do novo cliente.
	 */
	public void cadastrarCliente(String nome, String endereco, String contato) {
		Cliente provisorio = new Cliente(this.codigoAtual, nome, endereco, contato);
		this.codigoAtual++;
		this.listaClientes.adicionarFinal(provisorio);
	}
	
	/**
	 * Pesquisa um cliente na lista de clientes.
	 * 
	 * @param clientePesquisar - o nome do cliente que se deseja pesquisar.
	 * 
	 * @return o cliente encontrado,
	 * 		   <code>null</code> caso contrário.
	 */
	public Cliente pesquisar(String clientePesquisar) {
		
		if (this.listaClientes.vazia()) {
			return null;
		}
		
		Cliente provisorio = null;
		
		for (int i = 0; i < this.listaClientes.tamanho(); i++) {
			provisorio = this.listaClientes.buscarPorPosicao(i+1);
			
			if (clientePesquisar.equalsIgnoreCase(provisorio.getNome())) {
				return provisorio;
			}
		}
		
		return provisorio;
		
	}

	/**
	 * Modifica um cliente na lista de clientes.
	 * 
	 * @param antigo - o cliente a ser modificado
	 * @param novoNome - o novo nome do cliente.
	 * @param novoEndereco - o novo endereço do cliente.
	 * @param novoContato - o novo contato do cliente.
	 * 
	 * @return <code>true</code> caso o cliente seja atualizado com sucesso,
	 * 		   <code>false</code> caso contrário. 
	 */
	public boolean modificarCliente(Cliente antigo, String novoNome, String novoEndereco, 
			String novoContato) {
		
		if (this.listaClientes.vazia()) {
			return false;
		}
		
		Cliente provisorio;
		
		for (int i = 0; i < this.listaClientes.tamanho(); i++) {
			provisorio = this.listaClientes.buscarPorPosicao(i+1);
			
			if ((provisorio.getNome().equalsIgnoreCase(antigo.getNome())) && 
					(provisorio.getEndereco().equalsIgnoreCase(antigo.getEndereco()) && 
					(provisorio.getContato().equalsIgnoreCase(antigo.getContato())))) {
				provisorio.setNome(novoNome);
				provisorio.setEndereco(novoEndereco);
				provisorio.setContato(novoContato);
				return true;
			}
		}
		
		return false;
	}
}


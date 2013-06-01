import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class OrdemDeServico {

	private int codigo;
	private String descricao;
	private String descricaoAtendimento;
	private Date dataAbertura;
	private Date dataEncerramento;
	private ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos;
	private String prioridade;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAtendimento() {
		return descricaoAtendimento;
	}

	public void setDescricaoAtendimento(String descricaoAtendimento) {
		this.descricaoAtendimento = descricaoAtendimento;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataEnceramento() {
		return dataEncerramento;
	}

	public void setDataEnceramento(Date dataEnceramento) {
		this.dataEncerramento = dataEnceramento;
	}

	public ListaEncadeadaDinamica<Integer> getListaClientesEnvolvidos() {
		return listaClientesEnvolvidos;
	}

	public void setListaClientesEnvolvidos(
			ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos) {
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param codigo
	 * @param descricao
	 * @param descricaoAtendimento
	 * @param dataAbertura
	 * @param dataEncerramento
	 * @param listaClientesEnvolvidos
	 * @param prioridade
	 */
	public OrdemDeServico(int codigo, String descricao, String descricaoAtendimento, Date dataAbertura, Date dataEncerramento,  
			ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos, String prioridade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAtendimento = descricaoAtendimento;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
		this.prioridade = prioridade;
	}
	
	/**
	 * Prepara uma String com os dados da ordem se serviço.
	 * 
	 * @param listaClientes - a lista de clientes da empresa.
	 * 
	 * @return a String com os dados.
	 */
	public String toString(ListaEncadeadaDinamica<Cliente> listaClientes) {
		@SuppressWarnings("deprecation")
		String temporario = "Ordem[" + this.codigo + "] = \n\tDescrição: " + this.descricao + "\n\tDescrição do atendimento: " + 
				this.descricaoAtendimento + "\n\tData de abertura: " + 
				this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + "\n\tData de encerramento: " + 
				this.dataEncerramento.getDate()+"/"+this.dataEncerramento.getMonth()+"/"+this.dataEncerramento.getYear() + 
				"\n\tClientes envolvidos: ";

		ListaEncadeadaDinamica<Cliente> listaProvisoria = conversao(listaClientes);
		Cliente provisorio;
		
		for (int i = 0; i < listaProvisoria.tamanho(); i++) {
			provisorio = listaProvisoria.buscarPorPosicao(i+1);
			temporario += "\n\t\t" + provisorio.toString();
		}
		
		return temporario += "\n\tPrioridade: " + this.prioridade;
	}
	
	/**
	 * Prepara uma String com os dados da ordem de serviço pronta para ser gravada em arquivo.
	 * 
	 * @return a String com os dados da ordem de serviço.
	 */
	@SuppressWarnings("deprecation")
	public String toStringParaArquivo() {
		return this.codigo + " | "+ this.descricao + " | " + this.descricaoAtendimento + " | " + 
				this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + " | " +
				this.dataEncerramento.getDate()+"/"+this.dataEncerramento.getMonth()+"/"+ this.dataEncerramento.getYear() + " | " + 
				this.listaClientesEnvolvidos.toString() + " | " + this.prioridade;
	}
	
	/**
	 * Cria uma lista de clientes com os códigos de uma segunda lista.
	 * 
	 * @param listaClientes - a lista de clientes da empresa.
	 * 
	 * @return uma lista de clientes criada com apenas os códigos pedidos.
	 */
	public ListaEncadeadaDinamica<Cliente> conversao(ListaEncadeadaDinamica<Cliente> listaClientes) {
		ListaEncadeadaDinamica<Cliente> provisorio = new ListaEncadeadaDinamica<Cliente>();
		
		Integer integerProvisorio;
		Cliente clienteProvisorio;
		
		for (int i = 0; i < this.listaClientesEnvolvidos.tamanho(); i++) {
			integerProvisorio = this.listaClientesEnvolvidos.buscarPorPosicao(i+1);
			clienteProvisorio = listaClientes.buscarPorPosicao(integerProvisorio);
			
			provisorio.adicionarFinal(clienteProvisorio);
		}
		
		return provisorio;
	}
	
	/**
	 * Cria uma lista de ordens de serviço a partir de um arquivo.
	 * 
	 * @param arquivo - o endereço do arquivo que contém as ordens de serviço.
	 * 
	 * @return uma lista das ordens de serviço do arquivo.
	 */
	static ListaEncadeadaDinamica<OrdemDeServico> lerDados(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		
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
	 * Monta uma ordem de serviço a partir de uma linha com todos os dados.
	 * 
	 * @param linhaArquivo - a linha com todos os dados.
	 * 
	 * @return uma ordem de serviço com os dados da linha passada.
	 */
	private static OrdemDeServico montar(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|");
		OrdemDeServico provisorio = new OrdemDeServico(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), 
				conversaoStringEmDate(campos[3].trim()), conversaoStringEmDate(campos[4].trim()), criarLista(campos[5].trim()), 
				campos[6].trim());
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
	private static Date conversaoStringEmDate(String aSerConvertida) {
		String[] campos = aSerConvertida.split("\\/");
		return new Date(Integer.parseInt(campos[2].trim()), Integer.parseInt(campos[1].trim()), Integer.parseInt(campos[0].trim()));
	}
	
	/**
	 * Cria uma lista de Integer a partir de uma linha dada.
	 * 
	 * @param linhaConverter - a linha com os dados a serem convertidos.
	 * 
	 * @return a lista com os dados da linha.
	 */
	private static ListaEncadeadaDinamica<Integer> criarLista(String linhaConverter) {
		ListaEncadeadaDinamica<Integer> provisorio = new ListaEncadeadaDinamica<Integer>();
		
		String[] campos = linhaConverter.split("\\,");
		
		for (int i = 0; i < campos.length; i++) {
			provisorio.adicionarFinal(Integer.parseInt(campos[i].trim()));
		}
		
		return provisorio;
	}
	
	/**
	 * Grava em um arquivo as ordens de serviço de uma lista.
	 * 
	 * @param arquivo - o endereço do arquivo que se deseja salvar as ordens de serviço.
	 * @param listaOrdensDeServico - a lista de ordens de serviço a ser salva no arquivo.
	 */
	static void salvarArquivo(String arquivo, ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico) {
		
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(arquivo));
			OrdemDeServico provisorio;
			
			for (int i = 0; i < listaOrdensDeServico.tamanho(); i++) {
				provisorio = listaOrdensDeServico.buscarPorPosicao(i+1);
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

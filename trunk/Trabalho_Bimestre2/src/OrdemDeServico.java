import java.util.Date;

/**
 * 
 * @author Arthur e Paulo Jos�
 * 
 */
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

	public void setListaClientesEnvolvidos(ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos) {
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * Construtor com argumentos para inst�ncia��o a partir da leitura de um
	 * arquivo.
	 * 
	 * @param codigo
	 *            - o c�digo da ordem de servi�o.
	 * @param descricao
	 *            - a descri��o da ordem de servi�o.
	 * @param descricaoAtendimento
	 *            - a descri��o do atendimento realizado.
	 * @param dataAbertura
	 *            - a data de abertura da ordem de servi�o.
	 * @param dataEncerramento
	 *            - a data de encerramento da ordem de servi�o.
	 * @param listaClientesEnvolvidos
	 *            - a lista de clientes envolvidos nesta ordem de servi�o.
	 * @param prioridade
	 *            - a prioridade � se realizar a ordem de servi�o.
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
	 * Construtor com argumentos para inst�ncia��o padr�o.
	 * 
	 * @param codigo
	 *            - o c�digo da ordem de servi�o.
	 * @param descricao
	 *            - a descri��o da ordem de servi�o.
	 * @param descricaoAtendimento
	 *            - a descri��o do atendimento realizado.
	 * @param dataAbertura
	 *            - a data de abertura da ordem de servi�o.
	 * @param listaClientesEnvolvidos
	 *            - a lista de clientes envolvidos nesta ordem de servi�o.
	 * @param prioridade
	 *            - a prioridade � se realizar a ordem de servi�o.
	 */
	public OrdemDeServico(int codigo, String descricao, Date dataAbertura, ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos, 
			String prioridade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
		this.prioridade = prioridade;
	}
	
	/**
	 * Prepara uma String com os dados da ordem se servi�o.
	 * 
	 * @param listaClientes
	 *            - a lista de clientes da empresa.
	 * 
	 * @return a String com os dados.
	 */
	@SuppressWarnings("deprecation")
	public String toString(ListaEncadeadaDinamica<Cliente> listaClientes) {
		String temporario;
		
		if (this.dataEncerramento != null) {
			 temporario = "Ordem[" + this.codigo + "] = \n\tDescri��o: " + this.descricao + "\n\tDescri��o do atendimento: " + 
					this.descricaoAtendimento + "\n\tData de abertura: " + 
					this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + "\n\tData de encerramento: " + 
					this.dataEncerramento.getDate()+"/"+this.dataEncerramento.getMonth()+"/"+this.dataEncerramento.getYear() + 
					"\n\tClientes envolvidos: ";
		} else {
			temporario = "Ordem[" + this.codigo + "] = \n\tDescri��o: " + this.descricao + "\n\tData de abertura: " + 
					this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + "\n\tClientes envolvidos: ";
		}

		ListaEncadeadaDinamica<Cliente> listaProvisoria = conversao(listaClientes);
		Cliente provisorio;
		
		for (int i = 0; i < listaProvisoria.tamanho(); i++) {
			provisorio = listaProvisoria.buscarPorPosicao(i+1);
			temporario += "\n\t\t" + provisorio.toString();
		}
		
		return temporario += "\n\tPrioridade: " + this.prioridade;
	}

	/**
	 * Prepara uma String com os dados da ordem de servi�o pronta para ser
	 * gravada em arquivo.
	 * 
	 * @return a String com os dados da ordem de servi�o.
	 */
	@SuppressWarnings("deprecation")
	public String toStringParaArquivo() {
		if (this.dataEncerramento != null) {
			return this.codigo + " | "+ this.descricao + " | " + this.descricaoAtendimento + " | " + 
					this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + " | " +
					this.dataEncerramento.getDate()+"/"+this.dataEncerramento.getMonth()+"/"+ this.dataEncerramento.getYear() + " | " + 
					this.listaClientesEnvolvidos.toString() + " | " + this.prioridade;
		} else {
			return this.codigo + " | "+ this.descricao + " | " + null + " | " + 
					this.dataAbertura.getDate()+"/"+this.dataAbertura.getMonth()+"/"+this.dataAbertura.getYear() + " | " + null+"/"+null+"/"+null + 
					" | " + this.listaClientesEnvolvidos.toString() + " | " + this.prioridade;
		}
	}
	
	/**
	 * Cria uma lista de clientes com os c�digos de uma segunda lista.
	 * 
	 * @param listaClientes
	 *            - a lista de clientes da empresa.
	 * 
	 * @return uma lista de clientes criada com apenas os c�digos pedidos.
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
	
}

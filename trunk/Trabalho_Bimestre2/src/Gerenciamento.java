import java.util.Date;


public class Gerenciamento {
	
	private ListaEncadeadaDinamica<Cliente> listaClientes;
	private ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico;
	
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

	/**
	 * Construtor padr�o.
	 * 
	 * @param arquivoClientes - o endere�o do arquivo que cont�m a lista de clientes.
	 * @param arquivoOrdens - o endere�o do arquivo que cont�m a lista de ordens de servi�o.
	 */
	public Gerenciamento(String arquivoClientes, String arquivoOrdens) {
		this.listaClientes = Cliente.lerDados(arquivoClientes);
		this.listaOrdensDeServico = OrdemDeServico.lerDados(arquivoOrdens);
	}
	
	/**
	 * Salva a lista de clientes e de ordens de servi�o em um arquivo.
	 * 
	 * @param arquivoClientes - o endere�o do arquivo que quer se salvar os clientes.
	 * @param arquivoOrdens - o endere�o do arquivo que quer se salvar as ordens de servi�o.
	 */
	public void salvarArquivos(String arquivoClientes, String arquivoOrdens) {
		Cliente.salvarArquivo(arquivoClientes, getListaClientes());
		OrdemDeServico.salvarArquivo(arquivoOrdens, getListaOrdensDeServico());
	}

	/**
	 * Cadastra um novo cliente na lista de clientes.
	 * 
	 * @param nome - o nome do novo cliente.
	 * @param endereco - o endere�o do novo cliente.
	 * @param contato - o contato do novo cliente.
	 */
	public void cadastrarCliente(String nome, String endereco, String contato) {
		Cliente provisorio = new Cliente(this.listaClientes.tamanho()+1, nome, endereco, contato);
		this.listaClientes.adicionarFinal(provisorio);
	}
	
	/**
	 * Pesquisa um cliente na lista de clientes.
	 * 
	 * @param clientePesquisar - o nome do cliente que se deseja pesquisar.
	 * 
	 * @return o cliente encontrado,
	 * 		   <code>null</code> caso contr�rio.
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
	 * @param novoEndereco - o novo endere�o do cliente.
	 * @param novoContato - o novo contato do cliente.
	 * 
	 * @return <code>true</code> caso o cliente seja atualizado com sucesso,
	 * 		   <code>false</code> caso contr�rio. 
	 */
	public boolean modificarCliente(Cliente antigo, String novoNome, String novoEndereco, String novoContato) {
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

	/**
	 * Registra uma ordem de servi�o na lista de ordens de servi�o.
	 * 
	 * @param descricao - a descri��o da ordem.
	 * @param dataAbertura - a data de cria��o da ordem.
	 * @param listaClientesEnvolvidos - a lista com os clientes envolvidos da ordem.
	 * @param prioridade - a prioridade da ordem.
	 */
	public void registrarOrdemDeServico(String descricao, Date dataAbertura, ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos, 
			String prioridade) {
		OrdemDeServico provisorio = new OrdemDeServico(this.listaOrdensDeServico.tamanho()+1, descricao, null, dataAbertura, null, 
				listaClientesEnvolvidos, prioridade);
		this.listaOrdensDeServico.adicionarFinal(provisorio);
	}
	
	/**
	 * Encerra uma ordem de servi�o.
	 * 
	 * @param codigoOrdemDeServico - o c�digo da ordem de servi�o que se deseja encerrar.
	 * @param descricaoAtendimento - a descri��o do atendimento realizado.
	 * @param dataEncerramento - a data de encerramento da ordem.
	 * 
	 * @return <code>true</code> caso a ordem de servi�o tenha sido encerrada,
	 * 		   <code>false</code> caso contr�rio. 
	 */
	public boolean encerrarOrdemDeServico(int codigoOrdemDeServico, String descricaoAtendimento, Date dataEncerramento) {
		if (this.listaOrdensDeServico.vazia() || codigoOrdemDeServico > this.listaOrdensDeServico.tamanho()) {
			return false;
		}
		
		OrdemDeServico provisorio = this.listaOrdensDeServico.buscarPorPosicao(codigoOrdemDeServico);
		provisorio.setDescricaoAtendimento(descricaoAtendimento);
		provisorio.setDataEnceramento(dataEncerramento);
		return true;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o n�o finalizadas.
	 * 
	 * @return uma lista com as ordens de servi�o n�o finalizadas,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> ordensNaoAtendidas() {
		if (this.listaOrdensDeServico.vazia()) {
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			if (ordemProvisorio.getDataEnceramento() == null) {
				provisorio.adicionarFinal(ordemProvisorio);
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o pertencentes a um determinado cliente.
	 * 
	 * @param cliente - o cliente na qual se deseja buscar as ordens.
	 * 
	 * @return uma lista com as ordens de servi�o do cliente,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> buscaOrdensCliente(Cliente cliente) {
		if (this.listaOrdensDeServico.vazia()){
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			for (int j = 0; j < ordemProvisorio.getListaClientesEnvolvidos().tamanho(); j++) {
				if (ordemProvisorio.getListaClientesEnvolvidos().buscarPorPosicao(j+1) == cliente.getCodigo()) {
					provisorio.adicionarFinal(ordemProvisorio);
				}
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o finalizadas.
	 * 
	 * @return uma lista com as ordens de servi�o finalizadas,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> ordensAtendidas() {
		if (this.listaOrdensDeServico.vazia()) {
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			if (ordemProvisorio.getDataEnceramento() != null) {
				provisorio.adicionarFinal(ordemProvisorio);
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o de prioridade urgente.
	 * 
	 * @return uma lista com as ordens de servi�o de prioridade urgente,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> ordensPrioridadeUrgente() {
		if (this.listaOrdensDeServico.vazia()) {
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			if (ordemProvisorio.getPrioridade().equalsIgnoreCase("urgente")) {
				provisorio.adicionarFinal(ordemProvisorio);
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o de prioridade baixa.
	 * 
	 * @return uma lista com as ordens de servi�o de prioridade baixa,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> ordensPrioridadeBaixa() {
		if (this.listaOrdensDeServico.vazia()) {
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			if (ordemProvisorio.getPrioridade().equalsIgnoreCase("baixa")) {
				provisorio.adicionarFinal(ordemProvisorio);
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Busca em uma lista as ordens de servi�o de prioridade normal.
	 * 
	 * @return uma lista com as ordens de servi�o de prioridade normal,
	 * 		   <code>null</code> caso a lista esteja vazia.
	 */
	public ListaEncadeadaDinamica<OrdemDeServico> ordensPrioridadeNormal() {
		if (this.listaOrdensDeServico.vazia()) {
			return null;
		}
		
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		OrdemDeServico ordemProvisorio;
		
		for (int i = 0; i < this.listaOrdensDeServico.tamanho(); i++) {
			ordemProvisorio = this.listaOrdensDeServico.buscarPorPosicao(i+1);
			
			if (ordemProvisorio.getPrioridade().equalsIgnoreCase("normal")) {
				provisorio.adicionarFinal(ordemProvisorio);
			}
		}
		
		return provisorio;
	}
	
}

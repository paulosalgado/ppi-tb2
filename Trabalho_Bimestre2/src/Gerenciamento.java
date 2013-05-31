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
	 * Construtor padrão.
	 * 
	 * @param arquivoClientes - o endereço do arquivo que contém a lista de clientes.
	 * @param arquivoOrdens - o endereço do arquivo que contém a lista de ordens de serviço.
	 */
	public Gerenciamento(String arquivoClientes, String arquivoOrdens) {
		this.listaClientes = Cliente.lerDados(arquivoClientes);
		this.listaOrdensDeServico = OrdemDeServico.lerDados(arquivoOrdens);
	}

	/**
	 * Cadastra um novo cliente na lista de clientes.
	 * 
	 * @param nome - o nome do novo cliente.
	 * @param endereco - o endereço do novo cliente.
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
	public boolean modificarCliente(Cliente antigo, String novoNome, String novoEndereco, String novoContato) {
		if (this.listaClientes.vazia()) {
			return false;
		}
		
		Cliente provisorio;
		
		for (int i = 0; i < this.listaClientes.tamanho(); i++) {
			provisorio = this.listaClientes.buscarPorPosicao(i+1);
			
			if ((provisorio.getNome().equalsIgnoreCase(antigo.getNome())) && (provisorio.getEndereco().equalsIgnoreCase(antigo.getEndereco()) && 
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

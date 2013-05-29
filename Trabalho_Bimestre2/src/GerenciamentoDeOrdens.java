
public class GerenciamentoDeOrdens {
	
	private ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico;
	private ListaEncadeadaDinamica<Cliente> listaClientes;
	private int codigoAtual;
	
	public ListaEncadeadaDinamica<OrdemDeServico> getListaOrdensDeServico() {
		return listaOrdensDeServico;
	}

	public void setListaOrdensDeServico(ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico) {
		this.listaOrdensDeServico = listaOrdensDeServico;
	}
	
	public GerenciamentoDeOrdens() {
		this.listaOrdensDeServico = new ListaEncadeadaDinamica<OrdemDeServico>();
		this.codigoAtual = 1;
	}
	
	public ListaEncadeadaDinamica<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ListaEncadeadaDinamica<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void cadastrarCliente(String nome, String endereco, String contato) {
		Cliente provisorio = new Cliente(this.codigoAtual, nome, endereco, contato);
		this.codigoAtual++;
		this.listaClientes.adicionarFinal(provisorio);
	}
	
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

	public boolean modificarCliente(Cliente antigo, String novoNome, String novoEndereco, 
			String contatoNovo) {
		
		if (this.listaClientes.vazia()) {
			return false;
		}
		
		Cliente provisorio;
		
		for (int i = 0; i < this.listaClientes.tamanho(); i++) {
			provisorio = this.listaClientes.buscarPorPosicao(i+1);
			
			if (provisorio.getNome().equalsIgnoreCase(antigo.getNome() && 
					provisorio.getEndereco().equalsIgnoreCase(antigo.getEndereco()) && 
					provisorio.getContato().equalsIgnoreCase(antigo.getContato()) {
				return provisorio;
			}
		}
		
	}
	
}

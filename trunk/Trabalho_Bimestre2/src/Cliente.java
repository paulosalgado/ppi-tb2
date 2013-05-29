
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

}

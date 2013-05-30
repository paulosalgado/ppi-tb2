import java.util.Date;

public class OrdemDeServico {

	private int codigo;
	private String descricao;
	private String descricaoAtendimento;
	private Date dataAbertura;
	private Date dataEnceramento;
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
		return dataEnceramento;
	}

	public void setDataEnceramento(Date dataEnceramento) {
		this.dataEnceramento = dataEnceramento;
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
	 * @param listaClientesEnvolvidos
	 * @param prioridade
	 */
	public OrdemDeServico(int codigo, String descricao, String descricaoAtendimento, Date dataAbertura, 
			ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos, String prioridade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAtendimento = descricaoAtendimento;
		this.dataAbertura = dataAbertura;
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
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
		this.dataEnceramento = dataEncerramento;
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
		this.prioridade = prioridade;
	}
	
}

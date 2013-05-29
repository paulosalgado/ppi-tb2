import java.util.Date;


public class OrdemDeServico {

	private int codigo;
	private String descricao;
	private String descricaoAtendimento;
	private Date dataAbertura;
	private Date dataEnceramento;
	private ListaEncadeadaDinamica<Cliente> listaClientesEnvolvidos;
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
	public ListaEncadeadaDinamica<Cliente> getListaClientesEnvolvidos() {
		return listaClientesEnvolvidos;
	}
	public void setListaClientesEnvolvidos(
			ListaEncadeadaDinamica<Cliente> listaClientesEnvolvidos) {
		this.listaClientesEnvolvidos = listaClientesEnvolvidos;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	public OrdemDeServico() {
		this.listaClientesEnvolvidos = new ListaEncadeadaDinamica<Cliente>();
	}
	
}

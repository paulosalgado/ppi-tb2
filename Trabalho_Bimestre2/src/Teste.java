
public class Teste {
	
	public static void main(String[] args) {
		
		Gerenciamento t = new Gerenciamento("dados_clientes.txt", "dados_ordens.txt");
		System.out.println(t.getListaClientes().buscarPorPosicao(1).toString() + "\n");
		System.out.println(t.getListaOrdensDeServico().buscarPorPosicao(1).toString(t.getListaClientes()));
		
	}

}

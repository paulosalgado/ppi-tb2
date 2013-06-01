
public class Teste {
	
	public static void main(String[] args) {
		
		Gerenciamento t = new Gerenciamento("dados_clientes.txt", "dados_ordens.txt");
		
		System.out.print("\n\n" + t.getListaOrdensDeServico().buscarPorPosicao(1).toStringParaArquivo());
		
		
	}

}

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author Arthur e Paulo Jos�
 * 
 */
public class Principal {
	
	private static Gerenciamento g;

	/**
	 * Menu principal para o m�todo main.
	 */
	private static void menu() {
		System.out.println("GERENCIAMENTO DE ORDENS DE SERVI�O");
		System.out.println("\n1 - Cadastrar novo cliente");
		System.out.println("2 - Pesquisar um novo cliente");
		System.out.println("3 - Atualizar os dados de um cliente");
		System.out.println("4 - Registrar uma nova ordem de servi�o");
		System.out.println("5 - Encerrar uma ordem de servi�o");
		System.out.println("6 - Listar ordens de servi�o que ainda n�o foram atendidas");
		System.out.println("7 - Listar ordens de servi�o por cliente");
		System.out.println("8 - Listar ordens de servi�o que j� foram atendidas");
		System.out.println("9 - Listar ordens de servi�o de acordo com sua ''Fila de Prioridades''");
		System.out.println("0 - Encerrar programa");
	}

	/**
	 * Cadastrar novo cliente.
	 */
	private static void cadastrar() {
		String nome, endereco, contato;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme o nome do novo contato: ");
		nome = base.nextLine();
		
		System.out.print("Informe o endere�o do novo contato: ");
		endereco = base.nextLine();
		
		System.out.print("Informe o contato do novo contato: ");
		contato = base.nextLine();
		
		boolean retorno = g.cadastrarCliente(nome, endereco, contato); 
		
		if (retorno == true) {
			System.out.println("\nCliente cadastrado com sucesso!\n\n");
		} else {
			System.out.println("\nCliente n�o foi cadastrado! Certifique-se que ele j� n�o consta na lista de clientes.\n\n");
		}
	}
	
	/**
	 * Pesquisar um novo cliente.
	 */
	private static void pesquisar() {
		String nome;
		Cliente provisorio;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme o nome do cliente que deseja pesquisar: ");
		nome = base.nextLine();
		
		provisorio = g.pesquisar(nome);
		
		if (provisorio.getNome().equalsIgnoreCase(nome)) {
			System.out.println("\n" + provisorio.toString() + "\n\n");
		} else {
			System.out.println("\nCliente n�o encontrado!\n\n");
		}
	}
	
	/**
	 * Atualizar os dados de um cliente.
	 */
	private static void atualizar() {
		String nome, endereco, contato, textoProvisorio;
		Cliente provisorio;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme o nome do cliente que deseja atualizar: ");
		nome = base.nextLine();
		
		provisorio = g.pesquisar(nome);
		
		if (provisorio.getNome().equalsIgnoreCase(nome)) {
			
			System.out.print("Informe o novo nome do contato ou null caso deseje que continue o mesmo: ");
			textoProvisorio = base.nextLine();
			
			if (textoProvisorio.equalsIgnoreCase("null")) {
				nome = provisorio.getNome();
			} else {
				nome = textoProvisorio;
			}
			
			System.out.print("Informe o novo endere�o do contato ou null caso deseje que continue o mesmo: ");
			textoProvisorio = base.nextLine();
			
			if (textoProvisorio.equalsIgnoreCase("null")) {
				endereco = provisorio.getEndereco();
			} else {
				endereco = textoProvisorio;
			}
			
			System.out.print("Informe o novo contato do contato ou null caso deseje que continue o mesmo: ");
			textoProvisorio = base.nextLine();
			
			if (textoProvisorio.equalsIgnoreCase("null")) {
				contato = provisorio.getContato();
			} else {
				contato = textoProvisorio;
			}
			
			boolean retorno = g.modificarCliente(provisorio, nome, endereco, contato); 
			
			if (retorno == true) {
				System.out.println("\nCliente atualizado com sucesso!\n\n");
			} else {
				System.out.println("\nFalha na atualiza��o do cliente.\n\n");
			}
			
		} else {
			System.out.println("\nCliente n�o encontrado!\n\n");
		}
	}
	
	/**
	 * Registrar uma nova ordem de servi�o.
	 */
	@SuppressWarnings("deprecation")
	private static void registrar() {
		String descricao, prioridade, nome, analise = "sim";
		boolean ok = false, reserva = false;
		@SuppressWarnings("unused")
		Date dataAbertura;
		int dia, mes, ano;
		ListaEncadeadaDinamica<Integer> listaClientesEnvolvidos = new ListaEncadeadaDinamica<Integer>();
		Cliente provisorio;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme a descri��o da nova ordem de servi�o: ");
		descricao = base.nextLine();
		
		System.out.print("Informe o dia da abertura: ");
		dia = base.nextInt();
		
		System.out.print("Informe o m�s da abertura: ");
		mes = base.nextInt();
		
		System.out.print("Informe o ano da abertura: ");
		ano = base.nextInt();
		
		base.nextLine();
		do {
			
			do {
				System.out.print("Informe o nome do cliente que deseja associar a esta ordem: ");
				nome = base.nextLine();
				
				provisorio = g.pesquisar(nome);
				
				if (provisorio.getNome().equalsIgnoreCase(nome)) {
					listaClientesEnvolvidos.adicionarFinal(provisorio.getCodigo());
					ok = true;
				} else {
					System.out.println("\nCliente n�o encontrado!");
				}
			} while (ok != true);
			
			System.out.print("Deseja colocar mais um cliente? (sim ou n�o): ");
			do {
				analise = base.nextLine();
			} while ((analise.equalsIgnoreCase("sim")) && (analise.equalsIgnoreCase("n�o")));
		} while (analise.equalsIgnoreCase("sim"));
		
		System.out.print("Informe a prioridade da ordem (baixa, normal ou urgente): ");
		do {
			prioridade = base.nextLine();
			
			if (prioridade.equalsIgnoreCase("baixa") || prioridade.equalsIgnoreCase("normal") || prioridade.equalsIgnoreCase("urgente")) {
				reserva = true;
			}
		} while (reserva != true);
		
		prioridade = prioridade.toUpperCase();
		
		g.registrarOrdemDeServico(descricao, new Date(ano, mes, dia), listaClientesEnvolvidos, prioridade); 
		System.out.println("\nOrdem de servi�o cadastrada com sucesso!\n\n");
	}
	
	/**
	 * Encerrar uma ordem de servi�o.
	 */
	private static void encerrar() {
		int codigoOrdemDeServico;
		String descricaoAtendimento;
		int dia, mes, ano;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme o c�digo da ordem de servi�o a ser finalizada: ");
		codigoOrdemDeServico = base.nextInt();
		
		if (codigoOrdemDeServico < 1 || codigoOrdemDeServico > g.getListaOrdensDeServico().tamanho()) {
			System.out.println("\nFalha! C�digo da ordem de servi�o inv�lido.\n\n");
		} else {
			base.nextLine();
			System.out.print("Informe a descri��o do atendimento realizado: ");
			descricaoAtendimento = base.nextLine();
			
			System.out.print("Informe o dia do encerramento: ");
			dia = base.nextInt();
			
			System.out.print("Informe o m�s do encerramento: ");
			mes = base.nextInt();
			
			System.out.print("Informe o ano do encerramento: ");
			ano = base.nextInt();
			
			@SuppressWarnings("deprecation")
			boolean retorno = g.encerrarOrdemDeServico(codigoOrdemDeServico, descricaoAtendimento, new Date(ano, mes, dia));
			
			if (retorno == true) {
				System.out.println("\nOrdem de servi�o finalizada com sucesso!\n\n");
			} else {
				System.out.println("\nFalha no encerramento. Certifique-se que esta ordem j� n�o tenha sido finalizada.\n\n");
			}
		}
	}

	/**
	 * Listar ordens de servi�o por cliente.
	 */
	private static void listarCliente() {
		String nome;
		Cliente provisorio;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		System.out.print("\nInforme o nome do cliente que deseja pesquisar: ");
		nome = base.nextLine();
		
		provisorio = g.pesquisar(nome);
		
		if (provisorio.getNome().equalsIgnoreCase(nome)) {
			ListaEncadeadaDinamica<OrdemDeServico> listaProvisoria = g.buscaOrdensCliente(provisorio);
			System.out.println("\nAs ordens de servi�o associadas a " + nome + " s�o: \n\n");
			imprimirLista(listaProvisoria);
		} else {
			System.out.println("\nCliente n�o encontrado!\n\n");
		}
	}
	
	/**
	 * Listar ordens de servi�o de acordo com sua ''Fila de Prioridades''.
	 */
	private static void listarPrioridade() {
		ListaEncadeadaDinamica<OrdemDeServico> listaProvisoria = g.ordensPrioridadeUrgente();
		System.out.println("\nLista de ordens de prioridade urgente: \n\n");
		imprimirLista(listaProvisoria);
		
		listaProvisoria = g.ordensPrioridadeNormal();
		System.out.println("Lista de ordens de prioridade normal: \n\n");
		imprimirLista(listaProvisoria);
		
		listaProvisoria = g.ordensPrioridadeBaixa();
		System.out.println("Lista de ordens de prioridade baixa: \n\n");
		imprimirLista(listaProvisoria);
	}
	
	/**
	 * Imprime uma lista no console.
	 * 
	 * @param lista - a lista que se deseja imprimir.
	 */
	private static void imprimirLista(ListaEncadeadaDinamica<OrdemDeServico> lista) {
		OrdemDeServico provisorio;
		
		if (lista.vazia()) {
			System.out.println("A lista est� vazia!\n");
		}
		
		for (int i = 0; i < lista.tamanho(); i++) {
			provisorio = lista.buscarPorPosicao(i+1);
			System.out.println(provisorio.toString(g.getListaClientes()) + "\n");
		}
		
		System.out.println();
	}
	
	/**
	 * Espera usu�rio clicar "Enter" para continuar com o fluxo de execu��o.
	 */
	private static void esperaUsuario() {
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		base.nextLine();
	}

	/**
	 * Fluxo principal na execu��o.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		g = new Gerenciamento("dados_clientes.txt", "dados_ordens.txt");
		char opcao;
		
		@SuppressWarnings("resource")
		Scanner base = new Scanner(System.in);
		
		do {
			menu();
			opcao = (char)System.in.read();
			
			switch (opcao) {
				case '1':
					esperaUsuario();
					cadastrar();
					esperaUsuario();
					break;
					
				case '2':
					esperaUsuario();
					pesquisar();
					esperaUsuario();
					break;
					
				case '3':
					esperaUsuario();
					atualizar();
					esperaUsuario();
					break;
					
				case '4':
					esperaUsuario();
					registrar();
					esperaUsuario();
					break;
					
				case '5':
					esperaUsuario();
					encerrar();
					esperaUsuario();
					break;
					
				case '6':
					esperaUsuario();
					/* Listar ordens de servi�o que ainda n�o foram atendidas. */
					System.out.println("\nOrdens ainda n�o atendidas: \n\n");
					imprimirLista(g.ordensNaoAtendidas());
					esperaUsuario();
					break;
					
				case '7':
					esperaUsuario();
					listarCliente();
					esperaUsuario();
					break;
					
				case '8':
					esperaUsuario();
					/* Listar ordens de servi�o que j� foram atendidas. */
					System.out.println("\nOrdens j� atendidas: \n\n");
					imprimirLista(g.ordensAtendidas());
					esperaUsuario();
					break;
					
				case '9':
					esperaUsuario();
					listarPrioridade();
					esperaUsuario();
					break;
					
				case '0':
					/* Encerrar programa. */
					base.nextLine();
					System.out.println("\nSaindo do programa...");
					esperaUsuario();
					break;
					
				default:
					base.nextLine();
					System.out.println("\nOp��o inv�lida! Por favor, escolha uma op��o dentre as de cima!\n\n");
					esperaUsuario();
					break;
			}
		} while (opcao != '0');
		
		g.salvarArquivos("dados_clientes.txt", "dados_ordens.txt");
	}
	
}

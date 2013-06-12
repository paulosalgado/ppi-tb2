import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author Arthur e Paulo José
 * 
 */
public class TratamentoDeArquivos {

	/**
	 * Cria uma lista de clientes a partir de um arquivo.
	 * 
	 * @param arquivo
	 *            - o endereço do arquivo que contém os clientes.
	 * 
	 * @return uma lista dos clientes do arquivo.
	 */
	static ListaEncadeadaDinamica<Cliente> lerDadosCliente(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<Cliente> provisorio = new ListaEncadeadaDinamica<Cliente>();
		
		try {
			in = new BufferedReader(new FileReader(arquivo));
			String linhaAtual;
			while((linhaAtual = in.readLine()) != null) {
				provisorio.adicionarFinal(montarCliente(linhaAtual));
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Monta um cliente a partir de uma linha com todos os dados.
	 * 
	 * @param linhaArquivo
	 *            - a linha com todos os dados.
	 * 
	 * @return um cliente com os dados da linha passada.
	 */
	private static Cliente montarCliente(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|");
		Cliente provisorio = new Cliente(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), campos[3].trim());
		return provisorio;
	}
	
	/**
	 * Grava em um arquivo os clientes de uma lista.
	 * 
	 * @param arquivo
	 *            - o endereço do arquivo que se deseja salvar os clientes.
	 * @param listaClientes
	 *            - a lista de clientes a ser salva no arquivo.
	 */
	static void salvarArquivoCliente(String arquivo, ListaEncadeadaDinamica<Cliente> listaClientes) {
		
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(arquivo));
			Cliente provisorio;
			
			for (int i = 0; i < listaClientes.tamanho(); i++) {
				provisorio = listaClientes.buscarPorPosicao(i+1);
				out.write(provisorio.toStringParaArquivo());
				out.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	/**
	 * Cria uma lista de ordens de serviço a partir de um arquivo.
	 * 
	 * @param arquivo
	 *            - o endereço do arquivo que contém as ordens de serviço.
	 * 
	 * @return uma lista das ordens de serviço do arquivo.
	 */
	static ListaEncadeadaDinamica<OrdemDeServico> lerDadosOrdemDeServico(String arquivo) {
		BufferedReader in = null;
		ListaEncadeadaDinamica<OrdemDeServico> provisorio = new ListaEncadeadaDinamica<OrdemDeServico>();
		
		try {
			in = new BufferedReader(new FileReader(arquivo));
			String linhaAtual;
			while((linhaAtual = in.readLine()) != null) {
				provisorio.adicionarFinal(montarOrdemDeServico(linhaAtual));
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
			}
		}
		
		return provisorio;
	}
	
	/**
	 * Monta uma ordem de serviço a partir de uma linha com todos os dados.
	 * 
	 * @param linhaArquivo
	 *            - a linha com todos os dados.
	 * 
	 * @return uma ordem de serviço com os dados da linha passada.
	 */
	private static OrdemDeServico montarOrdemDeServico(String linhaArquivo) {
		String[] campos = linhaArquivo.split("\\|");
		OrdemDeServico provisorio = new OrdemDeServico(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(), 
				conversaoStringEmDate(campos[3].trim()), conversaoStringEmDate(campos[4].trim()), criarLista(campos[5].trim()), 
				campos[6].trim());
		return provisorio;
	}

	/**
	 * Converte uma String em um Date.
	 * 
	 * @param aSerConvertida
	 *            - a String a ser convertida.
	 * 
	 * @return um objeto da classe Date com a data a partir da String.
	 */
	@SuppressWarnings("deprecation")
	private static Date conversaoStringEmDate(String aSerConvertida) {
		String[] campos = aSerConvertida.split("\\/");
		if (campos[0].equalsIgnoreCase("null")) {
			return null;
		} else {
			return new Date(Integer.parseInt(campos[2].trim()), Integer.parseInt(campos[1].trim()), Integer.parseInt(campos[0].trim()));
		}
	}
	
	/**
	 * Cria uma lista de Integer a partir de uma linha dada.
	 * 
	 * @param linhaConverter
	 *            - a linha com os dados a serem convertidos.
	 * 
	 * @return a lista com os dados da linha.
	 */
	private static ListaEncadeadaDinamica<Integer> criarLista(String linhaConverter) {
		ListaEncadeadaDinamica<Integer> provisorio = new ListaEncadeadaDinamica<Integer>();
		
		String[] campos = linhaConverter.split("\\,");
		
		for (int i = 0; i < campos.length; i++) {
			provisorio.adicionarFinal(Integer.parseInt(campos[i].trim()));
		}
		
		return provisorio;
	}

	/**
	 * Grava em um arquivo as ordens de serviço de uma lista.
	 * 
	 * @param arquivo
	 *            - o endereço do arquivo que se deseja salvar as ordens de
	 *            serviço.
	 * @param listaOrdensDeServico
	 *            - a lista de ordens de serviço a ser salva no arquivo.
	 */
	static void salvarArquivoOrdemDeServico(String arquivo, ListaEncadeadaDinamica<OrdemDeServico> listaOrdensDeServico) {
		
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(arquivo));
			OrdemDeServico provisorio;
			
			for (int i = 0; i < listaOrdensDeServico.tamanho(); i++) {
				provisorio = listaOrdensDeServico.buscarPorPosicao(i+1);
				out.write(provisorio.toStringParaArquivo());
				out.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		
	}
	
}

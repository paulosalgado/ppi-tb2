
/**
 * 
 * @author Arthur e Paulo Jos�
 * 
 */
public class ListaEncadeadaDinamica <T> {

	/**
	 * Declara��o da classe N�.
	 * 
	 * @param <T>
	 *            - o tipo de n�.
	 */
	@SuppressWarnings("hiding")
	private class No<T> {

		T valor;
		No<T> proximo;
		
	}

	/* primeiro n� da lista */
	private No<T> primeiro;

	/**
	 * Construtor padr�o.
	 */
	public ListaEncadeadaDinamica() {
		
	}
	
	public No<T> getPrimeiro() {
		return primeiro;
	}
	
	public void setPrimeiro(No<T> primeiro) {
		this.primeiro = primeiro;
	}

	/**
	 * Calcula o tamanho de uma lista.
	 * 
	 * @return o tamanho da lista (de 1 a n).
	 */
	public int tamanho() {
		
		if (primeiro == null) {
			return 0;
		} else {
		
			No<T> provisorio = primeiro;
			
			int n = 0;
			
			while (provisorio != null) {
	
				n++;
				provisorio = provisorio.proximo;
				
			}
			
			return n;
		
		}
		
	}
	
	/**
	 * Esvazia uma lista.
	 */
	public void esvaziar() {
		this.primeiro = null;
	}
	
	/**
	 * Checa se uma lista est� vazia.
	 * 
	 * @return <code>true</code> se a lista estiver vazia, <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean vazia() {
		return primeiro == null;
	}

	/**
	 * Adiciona um novo elemento no in�cio da lista.
	 * 
	 * @param elemento
	 *            - o elemento a inserido.
	 */
	public void adicionarInicio(T elemento) {
		
		No<T> novo = new No<T>();
		
		novo.valor = elemento;
		novo.proximo = primeiro;
		primeiro = novo;
		
	}

	/**
	 * Adiciona um novo elemento no final da lista.
	 * 
	 * @param elemento
	 *            - o elemento a ser inserido.
	 */
	public void adicionarFinal(T elemento) {
		
		No<T> novo = new No<T>();
		novo.valor = elemento;
		
		/* loop ir� at� o pen�ltimo n� da lista */
		if (this.primeiro == null) {
			this.primeiro = novo;
		} else {

			No<T> provisorio = primeiro;
			int n = 0;
			
			/* loop ir� at� o pen�ltimo n� da lista */
			while (n < this.tamanho() - 1) {
				
				n++;
				provisorio = provisorio.proximo;
				
			}
			
			provisorio.proximo = novo;
			
		}
		
	}
	
	/**
	 * Adiciona um novo elemento dada uma posi��o.
	 * 
	 * @param elemento
	 *            - o elemento que se deseja adicionar.
	 * @param posicao
	 *            - a posi��o que se deseja adicionar o elemento.
	 * 
	 * @return <code>true</code> caso sucesso, <code>false</code> caso
	 *         contr�rio.
	 */
	public boolean adicionarDadaPosicao(T elemento, int posicao) {
		
		if ((posicao < 1) || (posicao > this.tamanho() + 1)) {
			return false;
		}
		
		if (this.vazia()) {
			
			if (posicao == 1) {
				
				this.adicionarInicio(elemento);
				return true;
				
			} else {
				return false;
			}
			
		} else {
			
			if (posicao == 1) {
				this.adicionarInicio(elemento);
				return true;
			}
			
			No<T> temporario = primeiro;
			int n = 0;
			
			while (n < posicao - 2) {
				
				temporario = temporario.proximo;
				n++;
				
			}
			
			No<T> novo = new No<T>();
			novo.valor = elemento;
			
			novo.proximo = temporario.proximo;
			temporario.proximo = novo;
			
			return true;
			
		}
		
	}

	/**
	 * Remove o primeiro n� de uma lista.
	 * 
	 * @return <code>true</code> se removido com sucesso. <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean removerPrimeiro() {
		
		if (!vazia()) {
		
			primeiro = primeiro.proximo;
			return true;
		
		} else {
			return false;
		}
		
	}
	
	/**
	 * Remove o �ltimo n� de uma lista.
	 * 
	 * @return <code>true</code> se removido com sucesso. <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean removerUltimo() {
		
		if (!vazia()) {
		
			int n = 0;
			No<T> provisorio = primeiro;
			
			/* loop ir� at� o pen�ltimo n� da lista */
			while (n < this.tamanho() - 2) {
				
				n++;
				provisorio = provisorio.proximo;
				
			}
			
			provisorio.proximo = null;
			return true;
		
		} else {
			return false;
		}
		
	}
	
	/**
	 * Remove um elemento dada uma posi��o.
	 * 
	 * @param posicao
	 *            - a posi��o do elemento que se deseja remover.
	 * 
	 * @return <code>true</code> caso sucesso, <code>false</code> caso
	 *         contr�rio.
	 */
	public boolean removerDadaPosicao(int posicao) {
		
		/* lista vazia */
		if (this.vazia() || posicao > this.tamanho() || posicao < 1) {
			return false;
		}
		
		/* apenas um elemento e deseja-se apag�-lo */
		if (this.tamanho() == 1 && posicao == 1) {
			
			this.esvaziar();
			return true;
			
		}
		
		if (posicao == 1) {
			this.removerPrimeiro();
			return true;
		}
		
		int n = 0;
		No<T> provisorio = primeiro;
		
		/* remover �ltimo elemento */
		if (posicao == this.tamanho()) {
			while (n < tamanho() - 2) {
				
				provisorio = provisorio.proximo;
				n++;
				
			}
			
			provisorio.proximo = null;
			return true;
		}
		
		/* remover qualquer outro valor */
		while (n < posicao - 2) {
			
			provisorio = provisorio.proximo;
			n++;
			
		}
		
		provisorio.proximo = provisorio.proximo.proximo;
		return true;
	}
	
	/**
	 * Remove um elemento da lista dado o seu valor.
	 * 
	 * @param elemento
	 *            - o elemento a ser removido.
	 * 
	 * @return <code>true</code> caso sucesso na remo��o, <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean removerDadoValor(T elemento) {
		 if (this.vazia()) {
			 return false;
		 } else {
			 No<T> provisorio = primeiro;
			 int n = 0;
			 
			 while (provisorio != null) {
				 if (provisorio.valor == elemento) {
					 return this.removerDadaPosicao(n + 1);
				 }
				 
				 provisorio = provisorio.proximo;
				 n++;
			 }
			 
		 }
		 
		 /* se chegar aqui n�o foi poss�vel encontrar o elemento */
		 return false;
	}
	
	/**
	 * Modifica um elemento da lista dada sua posi��o.
	 * 
	 * @param posicao
	 *            - a posi��o do valor a ser modificado.
	 * @param novo
	 *            - o novo elemento a ser inserido.
	 * 
	 * @return <code>true</code> caso sucesso na modifica��o, <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean modificarDadaPosicao(int posicao, T novo) {
		if (this.vazia() || posicao < 0 || posicao > this.tamanho()) {
			return false;
		} else {
			this.removerDadaPosicao(posicao);
			this.adicionarDadaPosicao(novo, posicao);
			return true;
		}
	}
	
	/**
	 * Modifica um elemento da lista dado o seu valor.
	 * 
	 * @param elemento
	 *            - o elemento a ser modificado.
	 * @param novo
	 *            - o novo elemento a ser inserido.
	 * 
	 * @return <code>true</code> caso sucesso na modifica��o, <code>false</code>
	 *         caso contr�rio.
	 */
	public boolean modificarDadoElemento(T elemento, T novo) {
		if(this.vazia()) {
			return false;
		} else {
			No<T> provisorio = primeiro;
			 int n = 0;
			 
			 while (provisorio != null) {
				 if (provisorio.valor == elemento) {
					 return this.modificarDadaPosicao(n+1, novo);
				 }
				 
				 provisorio = provisorio.proximo;
				 n++;
			 }
			
		}
		
		/* se chegar aqui o valor n�o existe */
		return false;
	}
	
	/**
	 * Busca em uma lista um valor de uma posi��o informada.
	 * 
	 * @param posicaoBuscar
	 *            - a posi��o que se deseja buscar.
	 * 
	 * @return a posi��o encontrada, -1 caso contr�rio.
	 */
	public T buscarPorPosicao(int posicaoBuscar) {
		
		if ((posicaoBuscar > this.tamanho()) || (posicaoBuscar < 1)) {
			
			return null;
			
		} else {
			
			int n = 0;
			
			No<T> provisorio = primeiro;
			
			while (n < posicaoBuscar - 1) {
				
				n++;
				provisorio = provisorio.proximo;
				
			}
			
			return provisorio.valor;
			
		}
		
	}
	
	/**
	 * Busca em uma lista a posi��o de um valor informado.
	 * 
	 * @param elemento
	 *            - o elemento que se deseja buscar.
	 * 
	 * @return a posi��o do valor da lista, 0 caso o valor n�o exista.
	 */
	public int buscarPorValor(T elemento) {
		
		int n = 0;
		
		No<T> provisorio = primeiro;
		
		while (provisorio != null) {
			
			n++;
			
			if (provisorio.valor == elemento) {
				return n;
			}
			
			provisorio = provisorio.proximo;
			
		}
		
		/* caso chegue aqui, o valor n�o existe */
		return 0;
		
	}
	
	/**
	 * Cria uma String com os elemento da lista.
	 * 
	 * @return a String com os elementos da lista, <code>null</code> caso a
	 *         lista esteja vazia.
	 */
	@Override
	public String toString() {
		
		if (this.vazia()) {
			return null;
		} else {
			
			int n = 0;
			
			String texto = "";
			
			No<T> provisorio = primeiro;
			
			/* loop ir� at� o pen�ltimo n� da lista */
			while (n < this.tamanho()) {
				
				if (n != 0) {
					texto += ", ";
				}
				
				texto += provisorio.valor;
				provisorio = provisorio.proximo;
				
				n++;
				
			}
			
			return texto;
			
		}
		
	}
	
}

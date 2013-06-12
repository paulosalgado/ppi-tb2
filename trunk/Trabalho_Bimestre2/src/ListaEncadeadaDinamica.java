
/**
 * 
 * @author Arthur e Paulo José
 * 
 */
public class ListaEncadeadaDinamica <T> {

	/**
	 * Declaração da classe Nó.
	 * 
	 * @param <T>
	 *            - o tipo de nó.
	 */
	@SuppressWarnings("hiding")
	private class No<T> {

		T valor;
		No<T> proximo;
		
	}

	/* primeiro nó da lista */
	private No<T> primeiro;

	/**
	 * Construtor padrão.
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
	 * Checa se uma lista está vazia.
	 * 
	 * @return <code>true</code> se a lista estiver vazia, <code>false</code>
	 *         caso contrário.
	 */
	public boolean vazia() {
		return primeiro == null;
	}

	/**
	 * Adiciona um novo elemento no início da lista.
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
		
		/* loop irá até o penúltimo nó da lista */
		if (this.primeiro == null) {
			this.primeiro = novo;
		} else {

			No<T> provisorio = primeiro;
			int n = 0;
			
			/* loop irá até o penúltimo nó da lista */
			while (n < this.tamanho() - 1) {
				
				n++;
				provisorio = provisorio.proximo;
				
			}
			
			provisorio.proximo = novo;
			
		}
		
	}
	
	/**
	 * Adiciona um novo elemento dada uma posição.
	 * 
	 * @param elemento
	 *            - o elemento que se deseja adicionar.
	 * @param posicao
	 *            - a posição que se deseja adicionar o elemento.
	 * 
	 * @return <code>true</code> caso sucesso, <code>false</code> caso
	 *         contrário.
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
	 * Remove o primeiro nó de uma lista.
	 * 
	 * @return <code>true</code> se removido com sucesso. <code>false</code>
	 *         caso contrário.
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
	 * Remove o último nó de uma lista.
	 * 
	 * @return <code>true</code> se removido com sucesso. <code>false</code>
	 *         caso contrário.
	 */
	public boolean removerUltimo() {
		
		if (!vazia()) {
		
			int n = 0;
			No<T> provisorio = primeiro;
			
			/* loop irá até o penúltimo nó da lista */
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
	 * Remove um elemento dada uma posição.
	 * 
	 * @param posicao
	 *            - a posição do elemento que se deseja remover.
	 * 
	 * @return <code>true</code> caso sucesso, <code>false</code> caso
	 *         contrário.
	 */
	public boolean removerDadaPosicao(int posicao) {
		
		/* lista vazia */
		if (this.vazia() || posicao > this.tamanho() || posicao < 1) {
			return false;
		}
		
		/* apenas um elemento e deseja-se apagá-lo */
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
		
		/* remover último elemento */
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
	 * @return <code>true</code> caso sucesso na remoção, <code>false</code>
	 *         caso contrário.
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
		 
		 /* se chegar aqui não foi possível encontrar o elemento */
		 return false;
	}
	
	/**
	 * Modifica um elemento da lista dada sua posição.
	 * 
	 * @param posicao
	 *            - a posição do valor a ser modificado.
	 * @param novo
	 *            - o novo elemento a ser inserido.
	 * 
	 * @return <code>true</code> caso sucesso na modificação, <code>false</code>
	 *         caso contrário.
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
	 * @return <code>true</code> caso sucesso na modificação, <code>false</code>
	 *         caso contrário.
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
		
		/* se chegar aqui o valor não existe */
		return false;
	}
	
	/**
	 * Busca em uma lista um valor de uma posição informada.
	 * 
	 * @param posicaoBuscar
	 *            - a posição que se deseja buscar.
	 * 
	 * @return a posição encontrada, -1 caso contrário.
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
	 * Busca em uma lista a posição de um valor informado.
	 * 
	 * @param elemento
	 *            - o elemento que se deseja buscar.
	 * 
	 * @return a posição do valor da lista, 0 caso o valor não exista.
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
		
		/* caso chegue aqui, o valor não existe */
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
			
			/* loop irá até o penúltimo nó da lista */
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

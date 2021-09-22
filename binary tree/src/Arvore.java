import java.util.ArrayList;

public class Arvore{
	public ArrayList<Numero> ArvoreBinaria = new ArrayList<Numero>();

	public void ReturnElement(int num) {
		boolean bool = SearchElement(num);
		if(bool == true) {
			Numero direita = null;
			Numero esquerda = null;
			Numero pai = null;
			for(Numero i : ArvoreBinaria ) {
				if(i.getValor() == num) {
					direita = i.getDireita();
					esquerda = i.getEsquerda();
				}
			}
			for(Numero i : ArvoreBinaria ) {
				if(i.getDireita() != null) {
					if(i.getDireita().getValor() == num) {
						pai = i;
					}
				}
				if(i.getEsquerda() != null) {
					if(i.getEsquerda().getValor() == num) {
						pai = i;
					}
				}
			}
			String direitaString = (direita != null)? "O filho da direita é: " + direita.getValor() : "Esse número não tem filho na direita";
			String esquerdaString = (esquerda != null)? "O filho da esquerda é: " + esquerda.getValor() : "Esse número não tem filho na esquerda";
			String paiString = (pai != null)? "O pai é: " + pai.getValor() : "Esse número é a raiz da árvore";
			System.out.printf("%s.%n%s.%n%s.%n",direitaString,esquerdaString,paiString);
		}else {
			System.out.println("Esse valor não existe na árvore");
		}
		
	}
	
	public boolean SearchElement(int num) {
		boolean bool = false;
		for(Numero i: ArvoreBinaria) {
			if(i.getValor() == num) {
				bool = true;
			}
		}return bool;
	}
	
	public boolean AddElement(int num) {
		boolean bool = false;
		boolean bool2 = SearchElement(num);
		
		if(bool2 == false) {
			Numero numero = new Numero(num);

			Numero recursivo = ArvoreBinaria.get(0); // árvore root
			
			while(true) {
				
				if(numero.getValor() > recursivo.getValor()) { // caso o valor a ser adicionado for maior que o recursivo
					if(recursivo.getDireita() == null) { // caso o recursivo não tenha filho na direita
						recursivo.setDireita(numero);
						break;
					}else { // caso o recursivo tenha filho na direita
						recursivo = recursivo.getDireita();
					}
				}else { // caso o valor a ser adicionado for menor que o recursivo
					if(recursivo.getEsquerda() == null) { // caso o recursivo não tenha filho na esquerda
						recursivo.setEsquerda(numero);
						break;
					}else { // caso o recursivo tenha filho na esquerda
						recursivo = recursivo.getEsquerda();
					}
				}
			}
			bool = true;
			ArvoreBinaria.add(numero);
		}return bool;
	}
	
	public boolean RemoveElement(int num) {
		boolean bool = false;
		boolean bool2 = SearchElement(num);
		boolean direita = false;
		Numero pai = null;
		Numero root = ArvoreBinaria.get(0);
		Numero erase = null;
		if(bool2 == true) {
			
			bool = true;
			for(Numero i : ArvoreBinaria) {
				if(i.getValor() == num) {
					for(Numero a : ArvoreBinaria ) {
						if(a.getDireita() != null) { // encontrar o pai com o filho na direita
							if(a.getDireita().getValor() == num) {
								pai = a;
								direita = true;
							}
						}
						if(a.getEsquerda() != null) { // encontrar o pai com o filho na esquerda
							if(a.getEsquerda().getValor() == num) {
								pai = a;
							}
						}
					}
					
					if(i.getDireita() == null && i.getEsquerda() == null) { // caso 1: nenhum filho
						if(pai != null) {
							if(direita) {
								pai.setDireita(null);
							}else {
								pai.setEsquerda(null);
							}
						}
						int ind = ArvoreBinaria.indexOf(i);
						erase = ArvoreBinaria.get(ind);
					
					}else if(i.getDireita() == null && i.getEsquerda() != null) { // caso 2: um filho da esquerda
						
						if(i.getValor() == root.getValor()) {
							ArvoreBinaria.add(0,i.getEsquerda());
						}else {
							pai.setEsquerda(i.getEsquerda());
						}
						int ind = ArvoreBinaria.indexOf(i);
						erase = ArvoreBinaria.get(ind);
					
					
					}else if(i.getEsquerda() == null && i.getDireita() != null) { // caso 2: um filho da direita
						if(i.getValor() == root.getValor()) {
							ArvoreBinaria.add(0,i.getDireita());
						}else {
							pai.setDireita(i.getDireita());
						}
						int ind = ArvoreBinaria.indexOf(i);
						erase = ArvoreBinaria.get(ind);
					
					}else { // caso 3: dois filhos 
						
						if(i.getDireita().getEsquerda() == null) { // filho a direita não tem filho a esquerda
							if(i.getValor() == root.getValor()) {
								ArvoreBinaria.add(0,i.getDireita());
							}else {
								if(direita) {
									pai.setDireita(i.getDireita());
								}else {
									pai.setEsquerda(i.getDireita());
								}
								
							}
							i.getDireita().setEsquerda(i.getEsquerda());
							int ind = ArvoreBinaria.indexOf(i);
							erase = ArvoreBinaria.get(ind);
						
						}else { // filho a direita tem filho a esquerda
						
						
							Numero substituto = i.getDireita().getEsquerda();
						
							while(substituto.getEsquerda() != null) { // da subarvore da direita, o menor da subarvore da esquerda
								substituto = substituto.getEsquerda();
							}
						
							Numero SubstitutoPai = null;
							if(substituto.getDireita() != null) {
							
								for(Numero x: ArvoreBinaria) {
									if(x.getEsquerda().getValor() == substituto.getValor()) {
										SubstitutoPai = x;
									}
								}
							
								SubstitutoPai.setEsquerda(substituto.getDireita());
							
							}
						
							substituto.setDireita(i.getDireita()); // substituto herda os filhos do i
							substituto.setEsquerda(i.getEsquerda());
							if(i.getValor() == root.getValor()) {
								ArvoreBinaria.add(0,substituto);
							}else {
								if(direita) {
									pai.setDireita(substituto);
								}else {
									pai.setEsquerda(substituto);
								}
								
							}
							int ind = ArvoreBinaria.indexOf(i);
							erase = ArvoreBinaria.get(ind);
						}
					}
				}
			}
		}ArvoreBinaria.remove(erase);
		return bool;
	}
	
	public String PrintList(Numero root){
        String print = "";
        if (root.getEsquerda() != null) {
        	print += (print.isEmpty() ? "" : ",") + PrintList(root.getEsquerda());
        }
        print += (print.isEmpty() ? "" : ",") + root.getValor();
        if (root.getDireita() != null) {
        	print += (print.isEmpty() ? "" : ",") + PrintList(root.getDireita());
        }
        return print;
    }
}

import java.util.Scanner;
class Numero{
	int valor;
	Numero FilhoEsquerda = null;
	Numero FilhoDireita = null;
	
	public Numero(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {return this.valor;}
	
	public void setEsquerda(Numero numero) {this.FilhoEsquerda = numero;}
	
	public Numero getEsquerda() {return this.FilhoEsquerda;}
	
	public void setDireita(Numero numero) {this.FilhoDireita = numero;}
	
	public Numero getDireita() {return this.FilhoDireita;}
	
}

public class Main{
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		Arvore arvore = new Arvore();
		
		//int[] R = {86,11,7,35,170,2,120,21,88,152,31,12,181,28,134,70,1,85,25,77,10,128,198};
		System.out.println("Digite o valor inicial da árvore:");
		int rootNum = sc.nextInt();
		
		Numero root = new Numero(rootNum);
		arvore.ArvoreBinaria.add(root);
		
		System.out.println("[1]add elemento,[2]remover elemento,[3]retornar um elemento,[4]exibir árvore em-ordem,[5]sair.");
		while(true) {
			int input = sc.nextInt();
			
			if(input == 1) {
				System.out.println("Digite o valor do elemento a ser adicionado");
				int add = sc.nextInt();
				boolean verdade = arvore.AddElement(add);
				if(verdade) {
					System.out.println("O valor "+add+" foi adicionado");
				}else {
					System.out.println("O valor "+add+" já existe na árvore");
				}
			}
			
			else if(input == 2) {
				System.out.println("Digite o valor do elemento a ser removido");
				int remove = sc.nextInt();
				Numero raizAtual = arvore.ArvoreBinaria.get(0);
				if(arvore.ArvoreBinaria.size() == 1 && raizAtual.getValor() == remove) {
					System.out.println("Adicione um novo valor a árvore antes de deletar sua raiz");
				}else {
					boolean verdade = arvore.RemoveElement(remove);
					if(verdade == true) {
						System.out.println("Elemento deletado com sucesso");
					}else {
						System.out.println("O elemento a ser deletado não existe");
					}
				}
			}
			
			else if(input == 3) {
				System.out.println("Digite o valor do elemento a ser retornado");
				int returnNum = sc.nextInt();
				arvore.ReturnElement(returnNum);
			}
			
			else if(input == 4) {
				System.out.println("exibindo árvore em formato de lista:");
				arvore.PrintList();
			}
			
			else if(input == 5) {break;}
			
			else {
				System.out.println("Comando inválido.");
			}
		}sc.close();
	}
}

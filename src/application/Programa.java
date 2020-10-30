package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jogotabuleiro.TabuleiroExcecao;
import xadrez.JogoXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		JogoXadrez jogoXadrez = new JogoXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
		
		
		
		while(true) {
			try {
		UI.clearScreen();
		UI.printPartida (jogoXadrez, capturada);
		System.out.println();
		System.out.print("Posi��o de Origem: ");
		PosicaoXadrez inicial = UI.lerPosicaoXadrez(sc);
		
		boolean [][] movimentoPossivel = jogoXadrez.movimentoPossivel(inicial);
		UI.clearScreen();
		UI.printTabuleiro (jogoXadrez.getPecas(), movimentoPossivel);
		System.out.println();
		System.out.print("Posi��o de Destino: ");
		PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
		PecaDeXadrez capturarPeca = jogoXadrez.movimentoXadrez(inicial, destino);
		
		if (capturarPeca != null) {
			capturada.add(capturarPeca);
		}
		}
			catch(TabuleiroExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
			
		}
		
	}



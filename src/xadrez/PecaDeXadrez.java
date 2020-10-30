package xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	private Cores cores;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	public Cores getCores() {
		return cores;
	}

	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCores() != cores;
	}

}

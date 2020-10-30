package xadrez;

import jogotabuleiro.Posicao;

public class PosicaoXadrez {
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha> 8) {
			throw new XadrezExcecao("Erro na Posi�ao do Xedrez.Valores v�lidos de a1 a h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected Posicao ToPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
		
	}
	
	protected static PosicaoXadrez fromPosicao(Posicao posicao) {
		return new PosicaoXadrez((char)('a'- posicao.getColuna()), 8 - posicao.getLinha()); 
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}

package xadrez;

import java.util.ArrayList;
import java.util.List;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import jogotabuleiro.TabuleiroExcecao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class JogoXadrez {

	private int virar;
	private Cores jogadorAtual;
	private Tabuleiro tabuleiro;               
	
	private List<Peca> pecasDoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public JogoXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		virar = 1;
		jogadorAtual = Cores.BRANCO;
		initialSetup();
	}

	public int getVirar() {
		return virar;
	}

	public Cores getJogadorAtual() {
		return jogadorAtual;
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);

			}
		}
		return mat;
	}

	public boolean[][] movimentoPossivel(PosicaoXadrez posicaoInicial) {
		Posicao posicao = posicaoInicial.ToPosicao();
		validacaoPosicaoInicial(posicao);
		return tabuleiro.peca(posicao).possivelMovimento();
	}

	public PecaDeXadrez movimentoXadrez(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoDestino) {
		Posicao inicial = posicaoInicial.ToPosicao();
		Posicao destino = posicaoDestino.ToPosicao();
		validacaoPosicaoInicial(inicial);
		ValidacaoPosicaoDestino(inicial, destino);
		Peca capturarPeca = fazerMovimento(inicial, destino);
		virarProximo();
		return (PecaDeXadrez) capturarPeca;

	}

	private Peca fazerMovimento(Posicao inicial, Posicao destino) {
		Peca p = tabuleiro.removaPeca(inicial);
		Peca capturarPeca = tabuleiro.removaPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if(capturarPeca != null) {
			pecasDoTabuleiro.remove(capturarPeca);
			pecasCapturadas.add(capturarPeca);
		}
		
		
		return capturarPeca;
	}

	private void validacaoPosicaoInicial(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezExcecao("Não existe peça na posição de origem ");
		}

		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCores()) {
			throw new XadrezExcecao("A peça escolhida não é sua ");
		}
		if (!tabuleiro.peca(posicao).existeAlgumPossivelMovimento()) {
			throw new TabuleiroExcecao("Não existe movimentos possíveis para peça escolhida");
		}
	}

	private void ValidacaoPosicaoDestino(Posicao inicial, Posicao destino) {
		if (!tabuleiro.peca(inicial).possivelMovimento(destino)) {
			throw new XadrezExcecao("A peça escolhida não pode se mover para posição de destino");
		}
	}

	private void virarProximo() {
		virar++;
		jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private void novoLugarPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).ToPosicao());
		pecasDoTabuleiro.add(peca);
	}

	private void initialSetup() {
		novoLugarPeca('b', 6, new Torre(tabuleiro, Cores.BRANCO));
		novoLugarPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
		novoLugarPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));

	}
}

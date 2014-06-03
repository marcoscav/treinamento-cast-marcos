package br.com.cast.scc.excecoes;

public class NenhumRegistroEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NenhumRegistroEncontradoException(String mensagem){
		super(mensagem);
	}
}

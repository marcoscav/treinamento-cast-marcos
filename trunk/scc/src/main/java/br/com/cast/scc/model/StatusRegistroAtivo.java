package br.com.cast.scc.model;

import java.util.Arrays;
import java.util.List;


public enum StatusRegistroAtivo {
	ATIVO('A', "Ativo"),
	INATIVO('I', "Inativo"),
	EXCLUIDO('E', "Excluido");
	
	private Character situacao;
	private String descricao;
	
	private StatusRegistroAtivo(Character situacao, String descricao) {
		this.situacao = situacao;
		this.descricao = descricao;
	}
	
	public Character getSituacao(){
		return situacao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static List<StatusRegistroAtivo> getEscolhas() {
		List<StatusRegistroAtivo> escolhas = Arrays.asList(ATIVO, INATIVO);
		return escolhas;
	}
}

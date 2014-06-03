package br.com.cast.scc.service;

import java.util.Iterator;

import br.com.cast.scc.filtro.FiltroPesquisaVeiculo;
import br.com.cast.scc.model.Veiculo;

public interface IVeiculoService {
	public Iterator<? extends Veiculo> consultaPaginada(long inicio, long total, FiltroPesquisaVeiculo filtro);
	public Long total();
}

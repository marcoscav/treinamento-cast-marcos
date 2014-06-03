package br.com.cast.scc.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cast.scc.dao.VeiculoDAO;
import br.com.cast.scc.excecoes.NenhumRegistroEncontradoException;
import br.com.cast.scc.filtro.FiltroPesquisaVeiculo;
import br.com.cast.scc.model.Veiculo;

@Component
public class VeiculoServiceImpl implements IVeiculoService {
	
	@Autowired
	private VeiculoDAO veiculoDAO; 
	
	@Override
	public Iterator<? extends Veiculo> consultaPaginada(long inicio,
			long total, FiltroPesquisaVeiculo filtro) {
		Iterator<? extends Veiculo> resultados = veiculoDAO.consultaPaginada(inicio, total, filtro);
		if (!resultados.hasNext()){
			throw new NenhumRegistroEncontradoException("Nenhum registro encontrado!");
		}
		return resultados;
	}

	@Override
	public Long total() {
		return veiculoDAO.total(Veiculo.class);
	}
}

package br.com.cast.scc.filtro;

import java.util.Iterator;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import br.com.cast.scc.excecoes.NenhumRegistroEncontradoException;
import br.com.cast.scc.model.DominioTipoVeiculo;
import br.com.cast.scc.model.StatusRegistroAtivo;
import br.com.cast.scc.model.Veiculo;
import br.com.cast.scc.service.IVeiculoService;

public class FiltroPesquisaVeiculo implements IDataProvider<Veiculo> {
	private static final long serialVersionUID = 1L;
	
	private IVeiculoService veiculoService;
	private Long codigo;
	private String rntrc;
	private String placa;
	private Long nuEmbarcacao;
	private StatusRegistroAtivo status;
	private DominioTipoVeiculo tpVeiculo;
	private Form<FiltroPesquisaVeiculo> formulario;
	
	public FiltroPesquisaVeiculo(IVeiculoService veiculoService, Form<FiltroPesquisaVeiculo> formulario){
		this.veiculoService = veiculoService;
		this.formulario = formulario;
	}
	
	public StatusRegistroAtivo getStatus() {
		return status;
	}

	public void setStatus(StatusRegistroAtivo status) {
		this.status = status;
	}

	public String getRntrc() {
		return rntrc;
	}

	public void setRntrc(String rntrc) {
		this.rntrc = rntrc;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getNuEmbarcacao() {
		return nuEmbarcacao;
	}

	public void setNuEmbarcacao(Long nuEmbarcacao) {
		this.nuEmbarcacao = nuEmbarcacao;
	}

	public DominioTipoVeiculo getTpVeiculo() {
		return tpVeiculo;
	}

	public void setTpVeiculo(DominioTipoVeiculo tpVeiculo) {
		this.tpVeiculo = tpVeiculo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getCodigo() {
		return this.codigo;
	}

	@Override
	public IModel<Veiculo> model(Veiculo veiculo) {
		return new CompoundPropertyModel<Veiculo>(veiculo);
	}

	@Override
	public void detach() {}

	@Override
	public Iterator<? extends Veiculo> iterator(long inicio, long total) {
		Iterator<? extends Veiculo> resultados = null;
		try {
			 resultados = veiculoService.consultaPaginada(inicio, total, this);
		} catch(NenhumRegistroEncontradoException e){
			throw new NenhumRegistroEncontradoException(e.getMessage());
			//formulario.error(e.getMessage());
		}
		return resultados;
	}

	@Override
	public long size() throws NenhumRegistroEncontradoException{
		return veiculoService.total();
	}
}

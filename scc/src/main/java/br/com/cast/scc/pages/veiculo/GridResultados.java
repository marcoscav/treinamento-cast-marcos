package br.com.cast.scc.pages.veiculo;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import br.com.cast.scc.filtro.FiltroPesquisaVeiculo;
import br.com.cast.scc.model.Veiculo;

public class GridResultados extends WebMarkupContainer {
	private static final long serialVersionUID = 1L;

	public GridResultados(String id, FiltroPesquisaVeiculo filtroPesquisaVeiculo) {
		super(id);
		setOutputMarkupId(true);
		DataView<Veiculo> grade = new DataView<Veiculo>("veiculos",
				filtroPesquisaVeiculo) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Veiculo> item) {

				item.add(new Label("tpVeiculo.descricao"));
				item.add(new Label("nuEmbarcacao"));
				item.add(new Label("placa1"));
				item.add(new Label("rntrc"));
				item.add(new Label("status"));
			}

		};
		add(grade);
	}
}

package br.com.cast.scc.pages.veiculo;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import br.com.cast.scc.model.DominioTipoVeiculo;
import br.com.cast.scc.model.Veiculo;
import br.com.cast.scc.service.IDominioTipoVeiculoService;
import br.com.cast.scc.service.IVeiculoService;

public class CadastrarVeiculoForm extends Form<Veiculo> {
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "veiculoServiceImpl")
	private IVeiculoService veiculoService;
	@SpringBean
	private IDominioTipoVeiculoService dominioTipoVeiculoService;
	private DropDownChoice<DominioTipoVeiculo> tipo;
	private WebMarkupContainer boxFluvial;
	private WebMarkupContainer boxTerrestre;
	private RequiredTextField<String> txtRNTRC;
	private TextField<String> txtPlaca1;
	private TextField<String> txtPlaca2;
	private TextField<String> txtPlaca3;
	private TextField<String> txtPlaca4;
	private RequiredTextField<Long> txtEmbarcacao;

	public CadastrarVeiculoForm(String id) {
		super(id);
		setDefaultModel(new CompoundPropertyModel<Veiculo>(new Veiculo()));
		criaSelectTipo();
		criaBoxFluvial();
		criaBoxTerrestre();
		criaPanels();
		criaCampoEnderecoDeAcesso();
	}

	private void criaCampoEnderecoDeAcesso() {
		add(new RequiredTextField<String>("endAcessoLink"));
	}

	private void criaSelectTipo() {
		IChoiceRenderer<DominioTipoVeiculo> renderer = new ChoiceRenderer<>(
				"descricao");
		List<DominioTipoVeiculo> lista = dominioTipoVeiculoService
				.consultarTodos();
		ListModel<DominioTipoVeiculo> model = new ListModel<>(lista);
		tipo = new DropDownChoice<>("TpVeiculo", model, renderer);
		tipo.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DominioTipoVeiculo dominioTipoVeiculo = tipo.getModelObject();
				if (dominioTipoVeiculo == null) {
					boxFluvial.setVisible(false);
					boxTerrestre.setVisible(false);
					target.add(boxFluvial);
					target.add(boxTerrestre);
				} else {
					if (dominioTipoVeiculo.getCodigo() == 2) {
						boxFluvial.setVisible(true);
						boxTerrestre.setVisible(false);
						target.add(boxFluvial);
						target.add(boxTerrestre);
					} else {
						boxFluvial.setVisible(false);
						boxTerrestre.setVisible(true);
						target.add(boxFluvial);
						target.add(boxTerrestre);
					}
				}
			}
		});
		add(tipo);
	}

	private void criaBoxFluvial() {
		boxFluvial = new WebMarkupContainer("boxFluvial");
		txtEmbarcacao = new RequiredTextField<Long>("nuEmbarcacao");
		txtEmbarcacao.add(new RangeValidator<Integer>(10, 10));
		txtEmbarcacao.setOutputMarkupId(true);
		boxFluvial.add(txtEmbarcacao);
		boxFluvial.setOutputMarkupPlaceholderTag(true);
		boxFluvial.setVisible(false);
		add(boxFluvial);
	}

	private void criaBoxTerrestre() {
		boxTerrestre = new WebMarkupContainer("boxTerrestre");
		txtRNTRC = new RequiredTextField<String>("rntrc");
		txtRNTRC.add(new StringValidator(1, 9));
		txtRNTRC.setOutputMarkupId(true);
		txtPlaca1 = criaPlaca("placa1", true);
		txtPlaca2 = criaPlaca("placa2", false);
		txtPlaca3 = criaPlaca("placa3", false);
		txtPlaca4 = criaPlaca("placa4", false);
		boxTerrestre.add(txtRNTRC, txtPlaca1, txtPlaca2, txtPlaca3, txtPlaca4);
		boxTerrestre.setOutputMarkupPlaceholderTag(true);
		boxTerrestre.setVisible(false);
		add(boxTerrestre);
	}

	/**
	 * Cria o campo para as placas com as configuracoes padroes.
	 * @return Placa
	 * @param id Id do TextFiend
	 * @param obrigatorio Define se é obrigatório ou não.
	 */
	private TextField<String> criaPlaca(String id, boolean obrigatorio) {
		TextField<String> txtPlaca = new TextField<String>(id);
		txtPlaca.add(new StringValidator(1, 9));
		txtPlaca.setOutputMarkupId(true);
		if (obrigatorio == true) {
			txtPlaca.setRequired(true);
		}
		return txtPlaca;
	}
	
	private void criaPanels() {
		add(new FeedbackPanel("msgErro", 
				new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR))
			.setOutputMarkupId(true));
		
		add(new FeedbackPanel("msgSucesso", 
				new ExactLevelFeedbackMessageFilter(FeedbackMessage.SUCCESS))
			.setOutputMarkupId(true));		
	}
}

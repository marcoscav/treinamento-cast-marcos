package br.com.cast.scc.pages.veiculo;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import br.com.cast.scc.filtro.FiltroPesquisaVeiculo;
import br.com.cast.scc.model.DominioTipoVeiculo;
import br.com.cast.scc.model.StatusRegistroAtivo;
import br.com.cast.scc.service.IDominioTipoVeiculoService;
import br.com.cast.scc.service.IVeiculoService;

public class ConsultarVeiculoIndexForm extends Form<FiltroPesquisaVeiculo> {

	@SpringBean
	private IDominioTipoVeiculoService dominioTipoVeiculoService;
	@SpringBean(name = "veiculoServiceImpl")
	private IVeiculoService veiculoService;
	private RadioChoice<StatusRegistroAtivo> radioStatus;
	private WebMarkupContainer boxFluvial;
	private WebMarkupContainer boxTerrestre;
	private GridResultados gridResultados;
	private DropDownChoice<DominioTipoVeiculo> tipo;
	private TextField<String> txtRNTRC;
	private TextField<String> txtPlaca;
	private TextField<Long> txtEmbarcacao;
	
	public ConsultarVeiculoIndexForm(String id) {
		super(id);
		FiltroPesquisaVeiculo filtro = 
				new FiltroPesquisaVeiculo(veiculoService, this);
		setDefaultModel(
				new CompoundPropertyModel<FiltroPesquisaVeiculo>(filtro));
		criaBoxFluvial();
		criaBoxTerrestre();
		criaSelectTipo();
		criaRadioStatus();
		criaGridResultados(filtro);
		criaBotaoPesquisar();
		criaBotaoLimpar();
		criaPanels();
		criaBotaoIncluir();
	}

	private void criaBotaoIncluir() {
		add(new Link<Void>("incluirVeiculo"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(CadastrarVeiculoPage.class);
			}
		});
	}

	private void criaPanels() {
		add(new FeedbackPanel("msgErro", 
				new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR))
			.setOutputMarkupId(true));
		
		add(new FeedbackPanel("msgSucesso", 
				new ExactLevelFeedbackMessageFilter(FeedbackMessage.SUCCESS))
			.setOutputMarkupId(true));		
	}

	private void criaBotaoLimpar() {
		add(new AjaxButton("limpar"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				boxFluvial.setVisible(false);
				boxTerrestre.setVisible(false);
				radioStatus.setModelObject(null);
				txtRNTRC.setModelObject(null);
				txtPlaca.setModelObject(null);
				tipo.setModelObject(null);
				txtEmbarcacao.setModelObject(null);
				DominioTipoVeiculo modelObject = tipo.getModelObject();
				System.out.println(modelObject);
				target.add(tipo, radioStatus, boxFluvial, boxTerrestre, txtRNTRC, txtPlaca, txtEmbarcacao);
			}
		});
	}

	private void criaBotaoPesquisar() {
		add(new AjaxButton("pesquisar") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(gridResultados);
			}
		});
	}

	private void criaGridResultados(FiltroPesquisaVeiculo filtro) {
		gridResultados = new GridResultados("resultados", filtro);
		add(gridResultados);
	}

	private void criaBoxTerrestre() {
		boxTerrestre = new WebMarkupContainer("boxTerrestre");
		txtRNTRC = new TextField<String>("rntrc");
		txtRNTRC.add(new StringValidator(1, 9));
		txtRNTRC.setOutputMarkupId(true);
		txtPlaca = new TextField<String>("placa");
		txtPlaca.add(new StringValidator(1, 9));
		txtPlaca.setOutputMarkupId(true);
		boxTerrestre.add(txtRNTRC, txtPlaca);
		boxTerrestre.setOutputMarkupPlaceholderTag(true);
		boxTerrestre.setVisible(false);
		add(boxTerrestre);
	}

	private void criaRadioStatus() {
		List<StatusRegistroAtivo> status = StatusRegistroAtivo.getEscolhas();
		IChoiceRenderer<StatusRegistroAtivo> renderer = new ChoiceRenderer<>("descricao");
		radioStatus = new RadioChoice<>("status", status, renderer);		
		radioStatus.setOutputMarkupId(true);
		
		//radioStatus.setSuffix("");
		add(radioStatus);
	}

	private void criaBoxFluvial() {
		boxFluvial = new WebMarkupContainer("boxFluvial");
		txtEmbarcacao = new TextField<Long>("nuEmbarcacao");
		txtEmbarcacao.add(new RangeValidator<Integer>(10, 10));
		txtEmbarcacao.setOutputMarkupId(true);
		boxFluvial.add(txtEmbarcacao);
		boxFluvial.setOutputMarkupPlaceholderTag(true);
		boxFluvial.setVisible(false);
		add(boxFluvial);
	}

	/**
	 * Cria a seleção unica para as opções Fluvial ou Terrestre, em um Select.
	 */
	private void criaSelectTipo() {
		IChoiceRenderer<DominioTipoVeiculo> renderer = new ChoiceRenderer<>("descricao");	
		List<DominioTipoVeiculo> lista = dominioTipoVeiculoService.consultarTodos();
		ListModel<DominioTipoVeiculo> model = new ListModel<>(lista);
		tipo = new DropDownChoice<>("TpVeiculo" ,
				model, renderer);
		tipo.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				DominioTipoVeiculo dominioTipoVeiculo = tipo.getModelObject();
				if (dominioTipoVeiculo == null){
					boxFluvial.setVisible(false);
					boxTerrestre.setVisible(false);
					target.add(boxFluvial);
					target.add(boxTerrestre);
				}else{
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
	private static final long serialVersionUID = 1L;
}

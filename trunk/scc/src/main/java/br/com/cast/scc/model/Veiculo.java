package br.com.cast.scc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_VEICULO")
public class Veiculo implements IEntidade{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "co_seq_veiculo")
	private Long codigo;
	@ManyToOne
	@JoinColumn(name="tp_veiculo")
	private DominioTipoVeiculo tpVeiculo;
	@Column(name = "ds_rntrc")
	private String rntrc;
	@Column(name = "nu_embarcacao")
	private Long nuEmbarcacao;
	@Column(name = "ds_placa1")
	private String placa1;
	@Column(name = "ds_placa2")
	private String placa2;
	@Column(name = "ds_placa3")
	private String placa3;
	@Column(name = "ds_placa4")
	private String placa4;
	@Column(name = "ds_endereco_link")
	private String endAcessoLink;
	@Column(name = "ds_login")
	private String loginAcesso;
	@Column(name = "ds_senha")
	private String senhaAcesso;
	@Column(name = "st_registro_ativo")
	private StatusRegistroAtivo status;
	@Column(name = "dt_ativacao")
	private Date dtAtivacao;
	@Column(name = "ds_justificativa_ativacao")
	private String dsJustificativaAtivacao;
	@Column(name = "dt_inativacao")
	private Date dtInativacao;
	@Column(name = "ds_justificativa_inativacao")
	private String dsJustificativaInativacao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRntrc() {
		return rntrc;
	}

	public void setRntrc(String rntrc) {
		this.rntrc = rntrc;
	}

	public Long getNuEmbarcacao() {
		return nuEmbarcacao;
	}

	public void setNuEmbarcacao(Long nuEmbarcacao) {
		this.nuEmbarcacao = nuEmbarcacao;
	}

	public String getPlaca1() {
		return placa1;
	}

	public void setPlaca1(String placa1) {
		this.placa1 = placa1;
	}

	public String getPlaca2() {
		return placa2;
	}

	public void setPlaca2(String placa2) {
		this.placa2 = placa2;
	}

	public String getPlaca3() {
		return placa3;
	}

	public void setPlaca3(String placa3) {
		this.placa3 = placa3;
	}

	public String getPlaca4() {
		return placa4;
	}

	public void setPlaca4(String placa4) {
		this.placa4 = placa4;
	}

	public String getEndAcessoLink() {
		return endAcessoLink;
	}

	public void setEndAcessoLink(String endAcessoLink) {
		this.endAcessoLink = endAcessoLink;
	}

	public String getLoginAcesso() {
		return loginAcesso;
	}

	public void setLoginAcesso(String loginAcesso) {
		this.loginAcesso = loginAcesso;
	}

	public String getSenhaAcesso() {
		return senhaAcesso;
	}

	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}

	public StatusRegistroAtivo getStatus() {
		return status;
	}

	public void setStatus(StatusRegistroAtivo status) {
		this.status = status;
	}

	public Date getDtAtivacao() {
		return dtAtivacao;
	}

	public void setDtAtivacao(Date dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}

	public String getDsJustificativaAtivacao() {
		return dsJustificativaAtivacao;
	}

	public void setDsJustificativaAtivacao(String dsJustificativaAtivacao) {
		this.dsJustificativaAtivacao = dsJustificativaAtivacao;
	}

	public Date getDtInativacao() {
		return dtInativacao;
	}

	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}

	public String getDsJustificativaInativacao() {
		return dsJustificativaInativacao;
	}

	public void setDsJustificativaInativacao(String dsJustificativaInativacao) {
		this.dsJustificativaInativacao = dsJustificativaInativacao;
	}

	public DominioTipoVeiculo getTpVeiculo() {
		return tpVeiculo;
	}

	public void setTpVeiculo(DominioTipoVeiculo tpVeiculo) {
		this.tpVeiculo = tpVeiculo;
	}

}

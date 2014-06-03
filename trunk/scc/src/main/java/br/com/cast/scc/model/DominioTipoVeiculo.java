package br.com.cast.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TIPO_VEICULO")
public class DominioTipoVeiculo implements IEntidade {
	private static final long serialVersionUID = 1L;
	
	public static final Long COD_TERRESTRE = 1L;
	public static final Long COD_FLUVIAL = 2L;
	
	@Id
	@Column(name = "CO_TIPO_VEICULO")
	private Long codigo;
	@Column(name = "DS_TIPO_VEICULO")
	private String descricao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

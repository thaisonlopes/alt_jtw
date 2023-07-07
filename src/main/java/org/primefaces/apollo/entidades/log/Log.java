package org.primefaces.apollo.entidades.log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.primefaces.apollo.entidades.usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "log")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	private LocalDateTime hora;

	@Column(nullable = false)
	private String acao;

	@Column(nullable = false)
	private String tela;

	@Column(nullable = false, length = 1000)
	private String descricao;

	@Column(nullable = false, length = 2000)
	private String dados;

	@Column(nullable = false)
	private String ip;

	@Column(nullable = false)
	private String dispositivo;

	// Construtores
	public Log() {
	}

	public Log(Usuario usuario, LocalDateTime hora, String acao, String tela, String descricao, String dados, String ip,
			String dispositivo) {
		this.usuario = usuario;
		this.hora = hora;
		this.acao = acao;
		this.tela = tela;
		this.descricao = descricao;
		this.dados = dados;
		this.ip = ip;
		this.dispositivo = dispositivo;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	// Equals and HashCode
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Log log = (Log) o;
		return Objects.equals(id, log.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// ToString
	@Override
	public String toString() {
		return "Log{" + "id=" + id + ", usuario=" + usuario + ", hora=" + hora + ", acao='" + acao + '\'' + ", tela='"
				+ tela + '\'' + ", descricao='" + descricao + '\'' + ", dados='" + dados + '\'' + ", ip='" + ip + '\''
				+ ", dispositivo='" + dispositivo + '\'' + '}';
	}
}

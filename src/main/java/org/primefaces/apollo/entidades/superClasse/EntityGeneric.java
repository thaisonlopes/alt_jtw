package org.primefaces.apollo.entidades.superClasse;

import java.time.LocalDateTime;

import org.primefaces.apollo.entidades.empresa.Empresa;
import org.primefaces.util.acesso.UserAndDate;
import org.primefaces.util.base.AltException;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

/**
 * 
 * @author willian
 *
 */
@MappedSuperclass
public class EntityGeneric implements java.io.Serializable {

	private static final long serialVersionUID = 142331852766878778L;

	@Column(name = "dthr_create")
	private LocalDateTime dthr_create;

	@Column(name = "cod_usuario_create")
	private int cod_usuario_create;

	@Column(name = "dthr_update")
	private LocalDateTime dthr_update;

	@Column(name = "cod_usuario_update")
	private int cod_usuario_update;

	@Version
	private Integer versao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa", nullable = false)
	private Empresa empresa;

	public LocalDateTime getDthr_create() {
		return dthr_create;
	}

	public void setDthr_create(LocalDateTime dthr_create) {
		this.dthr_create = dthr_create;
	}

	public int getCod_usuario_create() {
		return cod_usuario_create;
	}

	public void setCod_usuario_create(int cod_usuario_create) {
		this.cod_usuario_create = cod_usuario_create;
	}

	public LocalDateTime getDthr_update() {
		return dthr_update;
	}

	public void setDthr_update(LocalDateTime dthr_update) {
		this.dthr_update = dthr_update;
	}

	public int getCod_usuario_update() {
		return cod_usuario_update;
	}

	public void setCod_usuario_update(int cod_usuario_update) {
		this.cod_usuario_update = cod_usuario_update;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	@PrePersist
	protected void prePersist() {
		try {

			UserAndDate userAndDate = new UserAndDate() {

				@Override
				public void setUser(Integer codigo) {
					cod_usuario_create = codigo;
					cod_usuario_update = codigo;
				}

				@Override
				public void setDateTime(LocalDateTime dateTime) {
					dthr_create = dateTime;
					dthr_update = dateTime;
				}
				
				@Override
				public void setEmpresaAcesso(Empresa emp) {
					if (empresa == null || empresa.getCodigo() <= 0) {
                        if (emp == null) {
                            throw new AltException("Empresa inválida.\nSessão pode está expirada.");
                        }
                        empresa = emp;
                    }
					
				}

			};
			userAndDate.iniciarDados();

		} catch (Exception e) {
		}
	}

	@PreUpdate
	protected void preUpdateDadosUsuarioDataLanc() {
		try {

			UserAndDate userAndDate = new UserAndDate() {

				@Override
				public void setUser(Integer codigo) {
					cod_usuario_update = codigo;
				}

				@Override
				public void setDateTime(LocalDateTime dateTime) {
					dthr_update = dateTime;
				}
				
				@Override
				public void setEmpresaAcesso(Empresa empresa) {
					// Não implmentar essa função, pois no update não pode ser altearda
					
				}

			};
			userAndDate.iniciarDados();

		} catch (Exception e) {
		}
	}
}
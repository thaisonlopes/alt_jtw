package org.primefaces.apollo.entidades.usuarios.usuario;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Arrays;
import java.util.Objects;

import org.primefaces.apollo.entidades.empresa.Empresa;
import org.primefaces.apollo.entidades.superClasse.EntityGeneric;
import org.primefaces.apollo.entidades.usuarios.tipoUsuario.TipoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario extends EntityGeneric  {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false)
    private Long codigo;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "idTipoUsuario", nullable = false)
    private TipoUsuario idTipoUsuario;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    @Lob
    @Column
    private byte[] imagem;

    public Usuario() {
    }

    public Usuario(Long codigo, String cpf, String senha, TipoUsuario idTipoUsuario, Empresa empresa, byte[] imagem) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.senha = senha;
        this.idTipoUsuario = idTipoUsuario;
        this.empresa = empresa;
        this.imagem = imagem;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getidTipoUsuario() {
        return idTipoUsuario;
    }

    public void setidTipoUsuario(TipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(codigo, usuario.codigo) && Objects.equals(cpf, usuario.cpf) && Objects.equals(senha, usuario.senha) && Objects.equals(idTipoUsuario, usuario.idTipoUsuario) && Objects.equals(empresa, usuario.empresa) && Arrays.equals(imagem, usuario.imagem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idUsuario, codigo, cpf, senha, idTipoUsuario, empresa);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }
}

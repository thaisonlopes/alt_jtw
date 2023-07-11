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
    private TipoUsuario id_tipo_usuario;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    @Lob
    @Column
    private byte[] imagem;

    public Usuario() {
    }

    public Usuario(Long codigo, String cpf, String senha, TipoUsuario id_tipo_usuario, Empresa empresa, byte[] imagem) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.senha = senha;
        this.id_tipo_usuario = id_tipo_usuario;
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

    public TipoUsuario getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(TipoUsuario id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
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
        return Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(codigo, usuario.codigo) && Objects.equals(cpf, usuario.cpf) && Objects.equals(senha, usuario.senha) && Objects.equals(id_tipo_usuario, usuario.id_tipo_usuario) && Objects.equals(empresa, usuario.empresa) && Arrays.equals(imagem, usuario.imagem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idUsuario, codigo, cpf, senha, id_tipo_usuario, empresa);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }
}

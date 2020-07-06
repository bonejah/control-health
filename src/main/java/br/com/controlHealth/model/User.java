
package br.com.controlHealth.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbUsuario")
public class User implements Serializable {
	
	private static final long serialVersionUID = 4877406676923150789L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 30, nullable = false)
	private String email;

	@Column(length = 80, nullable = false)
	private String password;

	@Column(length = 10, name = "nickname", nullable = false)
	private String nickName;

	@Column(name = "isadmin")
	private boolean isAdmin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = true)
	private Calendar dataAtualizacao;
	
	public User() {}
	
	public User(String email, String password, String nickName, boolean isAdmin, Calendar dataCadastro,
			Calendar dataAtualizacao) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.isAdmin = isAdmin;
		this.dataCadastro = dataCadastro;
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public User(Integer id, String email, String password, String nickName, boolean isAdmin, Calendar dataCadastro,
			Calendar dataAtualizacao) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.isAdmin = isAdmin;
		this.dataCadastro = dataCadastro;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", nickName=" + nickName
				+ ", isAdmin=" + isAdmin + ", dataCadastro=" + dataCadastro + ", dataAtualizacao=" + dataAtualizacao
				+ "]";
	}

}

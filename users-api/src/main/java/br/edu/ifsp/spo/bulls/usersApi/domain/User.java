package br.edu.ifsp.spo.bulls.usersApi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")

public class User implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3104545566617263249L;
	//Uuid
    @Id
    private String userName;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String token;
    private LocalDateTime creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

	public User(String userName, @NotBlank(message = "Email is mandatory") String email,
			@NotBlank(message = "Password is mandatory") String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public User() {
		
	}

	@Override
	public boolean equals(Object obj) {

		User other = (User) obj;
		
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && Objects.equals(token, other.token)
				&& Objects.equals(userName, other.userName);
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, email, password, token, userName);
	}
	
}

package br.edu.ifsp.spo.bulls.common.api.dto;

import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Objeto de trânsito: Usuario ")
public class UserTO {
	@ApiModelProperty(value = "Identificador")
	private UUID id;

	@ApiModelProperty(value = "Nome de usuário")
	@NotBlank(message = "UserName is mandatory")
    private String userName;

	@ApiModelProperty(value = "Email")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email format is invalid")
    private String email;

	@ApiModelProperty(value = "Token de login")
    private String token;

	@ApiModelProperty(value = "Token de login com o google")
    private String idSocial;

	@ApiModelProperty(value = "Indica se o usuário tem o cadastro confirmado")
    private Boolean verified;

	@ApiModelProperty(value = "Usuario dono da estante virtual")
    private ProfileTO profile;

	public UserTO() {}

	public UserTO(String userName,String email, String token, Boolean verified) {
		super();
		this.userName = userName;
		this.email = email;
		this.token = token;
		this.verified = verified;
	}
	
	public UserTO(String userName,String email, Boolean verified) {
		super();
		this.userName = userName;
		this.email = email;
		this.verified = verified;
	}

	public UserTO(String userName,String email) {
		super();
		this.userName = userName;
		this.email = email;
	}
    
}

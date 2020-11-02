package com.bancointer.bancointer.model;
import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.validators.IsValidPublicKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@IsValidPublicKey
public class User implements IUser {

    static CryptographyRSA2048 cryptography  = new CryptographyRSA2048();
    static Logger logger =  LoggerFactory.getLogger(User.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID do usuário (chave primária do objeto)")
    Long id;

    @Column(length = 2048)
    @ApiModelProperty(notes = "nome do usuário")
    @NotEmpty(message = "{emptyName}")
    private String  nome;

    @Column(length = 2048)
    @ApiModelProperty(notes = "Email do usuário")
    @Email(message = "{notValidEmail}")
    private String email;

    @Column(length = 2048)
    @ApiModelProperty(notes = "Chave pública que será utilizada para criptografar os dados do usuário")
    private String publicKey;

    @OneToMany(mappedBy = "user", targetEntity = UniqueDigit.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ApiModelProperty(hidden = true)
    private List<UniqueDigit> digitosCalculados;


    @Override
    public User encryptFields() {

        this.setNome(cryptography.encrypt(this.getPublicKey(), this.getNome()));
        this.setEmail(cryptography.encrypt(this.getPublicKey(), this.getEmail()));
        return this;
    }

    @Override
    public User clearEncryptedFields() {
        this.setNome(null);
        this.setEmail(null);
        return this;
    }

}

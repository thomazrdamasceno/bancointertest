package com.bancointer.bancointer.domain;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String  name;

    @Column(length = 2048)
    @ApiModelProperty(notes = "Email do usuário")
    @Email(message = "{notValidEmail}")
    private String email;

    @Column(length = 2048)
    @ApiModelProperty(notes = "Chave pública que será utilizada para criptografar os dados do usuário")
    private String publicKey;

    @OneToMany(mappedBy = "user", targetEntity = UniqueDigit.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UniqueDigit> calculatedDigits;

    @Override
    public User encryptFields() {
        this.setName(cryptography.encrypt(this.getPublicKey(), this.getName()));
        this.setEmail(cryptography.encrypt(this.getPublicKey(), this.getEmail()));
        return this;
    }

    @Override
    public void clearEncryptedFields() {
        this.setName(null);
        this.setEmail(null);
    }

}

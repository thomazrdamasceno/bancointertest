package com.bancointer.bancointer.repository;
import com.bancointer.bancointer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.publicKey  from User u where u.id = :idUsuario")
    String getPublicKey(@Param("idUsuario") Long idUsuario);
}

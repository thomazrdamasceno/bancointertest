package com.bancointer.bancointer.repository;
import com.bancointer.bancointer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.publicKey FROM User u WHERE u.id = :idUser")
    String getPublicKey(@Param("idUser") Long idUser);
}

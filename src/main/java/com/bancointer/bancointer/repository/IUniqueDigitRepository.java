package com.bancointer.bancointer.repository;
import com.bancointer.bancointer.model.UniqueDigit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IUniqueDigitRepository extends JpaRepository<UniqueDigit, Long> {

    @Query(value = "select d  from UniqueDigit d where d.user.id = :idUsuario")
    List<UniqueDigit> getAllCalculationsByUser(@Param("idUsuario") Long idUsuario);
}

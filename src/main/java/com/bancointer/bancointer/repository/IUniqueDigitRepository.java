package com.bancointer.bancointer.repository;
import com.bancointer.bancointer.domain.UniqueDigit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IUniqueDigitRepository extends JpaRepository<UniqueDigit, Long> {
    @Query("SELECT d  FROM UniqueDigit d WHERE d.user.id = :idUser")
    List<UniqueDigit> getAllCalculationsByUser(@Param("idUser") Long idUser);

    @Query("SELECT CASE WHEN ( COUNT(d) > 0 ) THEN true ELSE false END FROM UniqueDigit d WHERE d.user.id = :idUser AND d.number = :number and d.concatenation = :concatenation")
    boolean checkIfCalculationExistsForUser(@Param("idUser") Long idUser, @Param("number") String number, @Param("concatenation") int concatenation);
}

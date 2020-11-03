package com.bancointer.bancointer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniqueDigit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String n;
    private Integer k;
    private Integer result;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

}

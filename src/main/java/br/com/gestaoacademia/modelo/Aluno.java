package br.com.gestaoacademia.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "alunos")
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "numero_celular", nullable = false, length = 20)
    private String numeroCelular;

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "pagamento_em_dia", nullable = false)
    private Boolean pagamentoEmDia = false;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

}

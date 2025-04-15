package br.com.gestaoacademia.repositorio;

import br.com.gestaoacademia.modelo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

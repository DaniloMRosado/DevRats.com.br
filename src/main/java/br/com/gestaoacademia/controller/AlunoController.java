package br.com.gestaoacademia.controller;

import br.com.gestaoacademia.modelo.Aluno;
import br.com.gestaoacademia.repositorio.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "home";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "form-aluno";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno inválido"));
        model.addAttribute("aluno", aluno);
        return "editar-aluno";
    }

    @PostMapping
    public String salvarAluno(@Valid @ModelAttribute Aluno aluno, BindingResult result) {
        if (result.hasErrors()) {
            return aluno.getId() != null ? "editar-aluno" : "form-aluno";
        }

        if (aluno.getId() != null) {
            Aluno existingAluno = alunoRepository.findById(aluno.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

            // Mantém a data original
            aluno.setDataCadastro(existingAluno.getDataCadastro());
        }

        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
}

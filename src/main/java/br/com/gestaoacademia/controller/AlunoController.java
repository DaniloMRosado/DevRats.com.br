package br.com.gestaoacademia.controller;

import br.com.gestaoacademia.modelo.Aluno;
import br.com.gestaoacademia.repositorio.AlunoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
}

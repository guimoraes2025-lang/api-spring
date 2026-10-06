package br.com.curso.listadetarefas.api.tarefa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tarefas.html")
public class TarefaWebController {

    private final TarefaService tarefaService;

    public TarefaWebController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas.html", tarefaService.listarTodas());
        model.addAttribute("novaTarefa", new Tarefa());
        return "tarefas.html"; // Nome do template: tarefas.html.html
    }

    @PostMapping
    public String criarTarefa(@ModelAttribute("novaTarefa") Tarefa tarefa) {
        if (tarefa.getDescricao() != null && !tarefa.getDescricao().trim().isEmpty()) {
            tarefaService.criar(tarefa);
        }
        return "redirect:/tarefas.html";
    }

    @PostMapping("/{id}/alternar")
    public String alternarStatus(@PathVariable Long id) {
        tarefaService.alternarStatus(id);
        return "redirect:/tarefas.html";
    }

    @PostMapping("/{id}/excluir")
    public String excluirTarefa(@PathVariable Long id) {
        tarefaService.deletar(id);
        return "redirect:/tarefas.html";
    }
}
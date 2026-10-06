package br.com.curso.listadetarefas.api.tarefa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    // Injecao de dependencia via construtor (boa pratica recomendada)
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> atualizar(Long id, Tarefa dadosAtualizados) {
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setDescricao(dadosAtualizados.getDescricao());
            tarefaExistente.setConcluida(dadosAtualizados.isConcluida());
            return tarefaRepository.save(tarefaExistente);
        });
    }

    public boolean deletar(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Tarefa> alternarStatus(Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setConcluida(!tarefa.isConcluida());
            return tarefaRepository.save(tarefa);
        });
    }
}
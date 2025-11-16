package med.voll.web_application.domain.consulta;


import jakarta.transaction.Transactional;
import med.voll.web_application.domain.medico.MedicoRepository;
import med.voll.web_application.domain.paciente.DadosListagemPaciente;
import med.voll.web_application.domain.paciente.PacienteRepository;
import med.voll.web_application.domain.paciente.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository; // chamando o repositório
    private final MedicoRepository medicorepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository){
        this.consultaRepository = consultaRepository;
        this.medicorepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Page<DadosListagemConsulta> listar(Pageable paginacao){
        return consultaRepository.findAllByOrderByData(paginacao).map(DadosListagemConsulta::new);
    }

    @Transactional
    public void cadastrar(DadosAgendamentoConsulta dados){
        var medicoConsulta = medicorepository.findById(dados.idMedico()).orElseThrow();
        var pacienteConsulta = pacienteRepository.findByCpf(dados.paciente()).orElseThrow();

        if(dados.id() == null){
            consultaRepository.save(new Consulta(medicoConsulta,pacienteConsulta,dados));
        }else{
            var consulta = consultaRepository.findById(dados.id()).orElseThrow();
            consulta.modificarDados(medicoConsulta,pacienteConsulta,dados);
        }
    }

    public DadosAgendamentoConsulta carregarPorId(Long id){
        var consulta = consultaRepository.findById(id).orElseThrow();
        return new DadosAgendamentoConsulta(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getNome(),consulta.getData(),consulta.getMedico().getEspecialidade());
    }
    

    @Transactional
    public void excluir(Long id) {
        consultaRepository.deleteById(id);
    }
}

package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validaciones.IValidadorDeConsultas;
import med.voll.api.domain.medico.IMedicoRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.IPacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    List<IValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos) {

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado.");
        }

        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("Este id para el medico no fue encontrado.");
        }

        // Validaciones
        validadores.forEach(v -> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        if (medico == null) {
            throw new ValidacionDeIntegridad("No existen médicos disponibles para este horario y especialidad.");
        }

        var consulta = new Consulta(medico, paciente, datos.fecha());

        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

//    public void cancelar(DatosCancelamientoConsulta datos) {
//        if (!consultaRepository.existsById(datos.idConsulta())) {
//            throw new ValidacionDeIntegridad("El id de la consulta no existe.");
//        }
//
//        validadoresCancelamiento.forEach(v -> v.validar(datos));
//
//        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
//        consulta.cancelar(datos.motivo());
//    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("Debe seleccionarse una especialidad para el médico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }
}

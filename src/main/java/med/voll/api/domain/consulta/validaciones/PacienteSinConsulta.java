package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.IConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements IValidadorDeConsultas{

    @Autowired
    private IConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteConConsulta = repository.existsByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario,
                ultimoHorario);

        if (pacienteConConsulta) {
            throw new ValidationException("El paciente ya tiene una consulta para este día.");
        }
    }
}

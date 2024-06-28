package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements IValidadorDeConsultas{

     @Autowired
    private IPacienteRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        if (datos.idPaciente() == null) {
            return;
        }
        var pacienteActivo = repository.findActivoById(datos.idPaciente());

        if (!pacienteActivo) {
            throw new ValidationException("No es permitido agendar citas con pacientes inactivos en el sistema.");
        }
    }
}

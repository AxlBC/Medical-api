package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements IValidadorDeConsultas{
    @Autowired
    private IMedicoRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        if (datos.idMedico() == null) {
            return;
        }
        var medicoActivo = repository.findActivoById(datos.idMedico());

        if (!medicoActivo) {
            throw new ValidationException("No es permitido agendar citas con médicos inactivos en el sistema.");
        }
    }
}

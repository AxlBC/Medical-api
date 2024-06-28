package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.IConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MédicoConConsulta implements IValidadorDeConsultas{

    @Autowired
    private IConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        if (datos.idMedico() == null) {
            return;
        }

        var medicoConConsulta = repository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());
        if (medicoConConsulta) {
            throw new ValidationException("Este médico ya tiene una consulta en ese horario");
        }
    }
}

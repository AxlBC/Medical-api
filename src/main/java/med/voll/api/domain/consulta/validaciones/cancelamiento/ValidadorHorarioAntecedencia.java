package med.voll.api.domain.consulta.validaciones.cancelamiento;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.IConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia implements IValidadorCancelamientoDeConsulta{
    @Autowired
    private IConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
//        var consulta = repository.getReferenceById(datos.idConsulta());
//        var ahora = LocalDateTime.now();
//        var diferenciaEnHoras = Duration.between(ahora, consulta.getData()).toHours();
//
//        if (diferenciaEnHoras < 24) {
//            throw new ValidationException("La consulta solamente puede ser cancelada con una antecedencia mínima de 24 " +
//                    "horas");
//        }
    }
}

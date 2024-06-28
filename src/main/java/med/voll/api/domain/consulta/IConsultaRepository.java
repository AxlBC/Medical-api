package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long aLong, LocalDateTime fecha);
}

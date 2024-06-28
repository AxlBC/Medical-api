package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")
// Lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
/* La anotación "@EqualsAndHashCode" de Lombok se utiliza para generar automáticamente los métodos "equals()" y "hashCode()"
 * en una clase Java.
 * Estos métodos son importantes porque:
 * "equals()" se utiliza para comparar si dos objetos son iguales, es decir, si tienen los mismos valores en sus atributos.
 * "hashCode()" se utiliza para generar un código hash único para cada objeto, que se utiliza en estructuras de datos como HashSet
 * y HashMap.
 */
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizaMedico datosActualizaMedico) {
        if (datosActualizaMedico.nombre() != null) {
            this.nombre = datosActualizaMedico.nombre();
        }
        if (datosActualizaMedico.documento() != null) {
            this.documento = datosActualizaMedico.documento();
        }
        if (datosActualizaMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizaMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}

package med.voll.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultasService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = {"consulta", "post"}
    )
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        var response = service.agendar(datos);
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping
//    @Transactional
//    @Operation(
//            summary = "Cancela una consulta de la agenda",
//            description = "Requiere motivo",
//            tags = {"consulta", "delete"}
//            )
//    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
//        service.cancelar(datos);
//        return ResponseEntity.noContent().build();
//    }
}
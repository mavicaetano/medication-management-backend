package caetano.maria.medicationmanagement.controller;

import caetano.maria.medicationmanagement.dto.MedicamentoDto;
import caetano.maria.medicationmanagement.model.Medicamento;
import caetano.maria.medicationmanagement.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Medicamento>> getAllMedicamento() {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.findAll());
    }

    //new feature: consulta de medicamento pelo numero de registro
    @GetMapping("/{nroRegistro}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable(value = "nroRegistro") Integer nroRegistro) {
        Optional<Medicamento> medicamentoOptional = medicamentoService.findById(nroRegistro);
        if (medicamentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveMedicamento(@RequestBody @Valid MedicamentoDto medicamentoDto) {
        return medicamentoService.saveMedicamento(medicamentoDto);
    }
}
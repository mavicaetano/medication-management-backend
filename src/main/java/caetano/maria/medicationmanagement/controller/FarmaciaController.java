package caetano.maria.medicationmanagement.controller;

import caetano.maria.medicationmanagement.dto.FarmaciaRecordDto;
import caetano.maria.medicationmanagement.model.Farmacia;
import caetano.maria.medicationmanagement.service.FarmaciaService;
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
@RequestMapping("/farmacias")
public class FarmaciaController {
    private final FarmaciaService farmaciaService;

    public FarmaciaController(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Farmacia>> getAllFarmacia() {
        return ResponseEntity.status(HttpStatus.OK).body(farmaciaService.findAll());
    }

    //não está encontrando a farmacia \/
    @GetMapping("/{cnpj}")
    public ResponseEntity<Object> getOneFarmacia(@PathVariable(value = "cnpj") Long cnpj) {
        Optional<Farmacia> farmaciaOptional = farmaciaService.findById(cnpj);
        if (farmaciaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmácia não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(farmaciaOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveFarmacia(@RequestBody @Valid FarmaciaRecordDto farmaciaRecordDto) {
        return farmaciaService.saveFarmacia(farmaciaRecordDto);
    }
}

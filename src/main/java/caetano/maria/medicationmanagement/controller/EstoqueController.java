package caetano.maria.medicationmanagement.controller;

import caetano.maria.medicationmanagement.dto.EstoqueAddMedicamentoDto;
import caetano.maria.medicationmanagement.dto.EstoqueListagemDto;
import caetano.maria.medicationmanagement.model.Estoque;
import caetano.maria.medicationmanagement.service.EstoqueService;
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

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EstoqueListagemDto>> getAllEstoque(@PathVariable Long cnpj){
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.findEstoquesByCnpj(cnpj));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> EstoqueAddMedicamentoDto(@RequestBody @Valid EstoqueAddMedicamentoDto estoqueAddMedicamentoDto) {
        return ResponseEntity.status((HttpStatus.OK)).body(estoqueService.addMedicamengit add toNoEstoque(estoqueAddMedicamentoDto));
    }
}

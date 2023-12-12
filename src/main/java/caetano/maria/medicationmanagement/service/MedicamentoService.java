package caetano.maria.medicationmanagement.service;

import caetano.maria.medicationmanagement.dto.MedicamentoDto;
import caetano.maria.medicationmanagement.model.Medicamento;
import caetano.maria.medicationmanagement.repository.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }
    public List<Medicamento> findAll() {
        return medicamentoRepository.findAll();
    }

    public Optional<Medicamento> findById(Integer nroRegistro) {
        return medicamentoRepository.findById(nroRegistro);
    }

    public ResponseEntity<Object> saveMedicamento(MedicamentoDto medicamentoDto) {
        if (medicamentoRepository.isNroRegistroExistente(medicamentoDto.getNroRegistro())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de Registro já existe.");
        }
        var medicamento = new Medicamento();
        BeanUtils.copyProperties(medicamentoDto, medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoRepository.save(medicamento));
    }
}

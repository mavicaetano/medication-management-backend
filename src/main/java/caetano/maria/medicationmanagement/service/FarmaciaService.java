package caetano.maria.medicationmanagement.service;

import caetano.maria.medicationmanagement.dto.FarmaciaRecordDto;
import caetano.maria.medicationmanagement.model.Endereco;
import caetano.maria.medicationmanagement.model.Farmacia;
import caetano.maria.medicationmanagement.repository.FarmaciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaService {
    private final FarmaciaRepository farmaciaRepository;

    public FarmaciaService(FarmaciaRepository farmaciaRepository) {
        this.farmaciaRepository = farmaciaRepository;
    }

    public List<Farmacia> findAll() {
        return farmaciaRepository.findAll();
    }

    public Optional<Farmacia> findById(Long cnpj) {
        return farmaciaRepository.findById(cnpj);
    }

    public ResponseEntity<Object> saveFarmacia(FarmaciaRecordDto farmaciaRecordDto) {
        if (farmaciaRepository.isCnpjExistente(farmaciaRecordDto.getCnpj())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já cadastrado.");
        }
        var farmacia = new Farmacia();
        BeanUtils.copyProperties(farmaciaRecordDto, farmacia);
        var endereco = new Endereco();
        BeanUtils.copyProperties(farmaciaRecordDto.getEndereco(), endereco);
        farmacia.setEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaRepository.save(farmacia));
    }
}

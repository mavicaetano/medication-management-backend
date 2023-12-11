package caetano.maria.medicationmanagement.service;

import caetano.maria.medicationmanagement.dto.EstoqueAddMedicamentoDto;
import caetano.maria.medicationmanagement.dto.EstoqueListagemDto;
import caetano.maria.medicationmanagement.exception.BusinessException;
import caetano.maria.medicationmanagement.model.Estoque;
import caetano.maria.medicationmanagement.model.Medicamento;
import caetano.maria.medicationmanagement.repository.EstoqueRepository;
import caetano.maria.medicationmanagement.repository.FarmaciaRepository;
import caetano.maria.medicationmanagement.repository.MedicamentoRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final FarmaciaRepository farmaciaRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, MedicamentoRepository medicamentoRepository, FarmaciaRepository farmaciaRepository){
        this.estoqueRepository = estoqueRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.farmaciaRepository = farmaciaRepository;
    }

    public List<EstoqueListagemDto> findEstoquesByCnpj(Long cnpj) {
        List<Estoque> estoquesByCnpj = estoqueRepository.findEstoquesByCnpj(cnpj);
        List<EstoqueListagemDto> estoquesDto = new ArrayList<>();

        for (Estoque estoque : estoquesByCnpj) {
            EstoqueListagemDto estoqueListagemDto = new EstoqueListagemDto();
            estoqueListagemDto.setNroRegistro(estoque.getNroRegistro());
            Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(estoque.getNroRegistro());
            medicamentoOptional.ifPresent(medicamento -> estoqueListagemDto.setNome(medicamento.getNome()));
            estoqueListagemDto.setQuantidade(estoque.getQuantidade());
            estoqueListagemDto.setDataAtualizacao(estoque.getDataAtualizacao());
            estoquesDto.add(estoqueListagemDto);
        }
        return estoquesDto;
    }

    public Object addMedicamentoNoEstoque(EstoqueAddMedicamentoDto estoqueAddMedicamentoDto) {
        if (!farmaciaRepository.isCnpjExistente(estoqueAddMedicamentoDto.getCnpj())) {
            throw new BusinessException("Este CNPJ não está cadastrado.");
        }

        if (!medicamentoRepository.isNroRegistroExistente(estoqueAddMedicamentoDto.getNroRegistro())) {
            throw new BusinessException("Este Número de Registro não está cadastrado.");
        }

        Optional<Estoque> estoqueOptional = estoqueRepository.findEstoqueByCnpjAndNroRegistro(estoqueAddMedicamentoDto.getCnpj(), estoqueAddMedicamentoDto.getNroRegistro());
        if (estoqueOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();
            estoque.setQuantidade(estoque.getQuantidade() + estoqueAddMedicamentoDto.getQuantidade());
            estoque.setDataAtualizacao(LocalDateTime.now());
            estoqueRepository.save(estoque);
            return estoque;
        }

        Estoque estoque = new Estoque();
        estoque.setCnpj(estoqueAddMedicamentoDto.getCnpj());
        estoque.setNroRegistro(estoqueAddMedicamentoDto.getNroRegistro());
        estoque.setQuantidade(estoqueAddMedicamentoDto.getQuantidade());
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
        return estoque;
    }
}

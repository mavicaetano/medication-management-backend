package caetano.maria.medicationmanagement.service;

import caetano.maria.medicationmanagement.dto.EstoqueAddMedicamentoDto;
import caetano.maria.medicationmanagement.dto.EstoqueListagemDto;
import caetano.maria.medicationmanagement.dto.EstoqueTransferenciaDto;
import caetano.maria.medicationmanagement.dto.EstoqueTransferenciaRetornoDto;
import caetano.maria.medicationmanagement.exception.BusinessException;
import caetano.maria.medicationmanagement.model.Estoque;
import caetano.maria.medicationmanagement.model.Medicamento;
import caetano.maria.medicationmanagement.repository.EstoqueRepository;
import caetano.maria.medicationmanagement.repository.FarmaciaRepository;
import caetano.maria.medicationmanagement.repository.MedicamentoRepository;
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
        validarExistenciaDoCnpjENroRegistro(estoqueAddMedicamentoDto.getNroRegistro(), estoqueAddMedicamentoDto.getCnpj());

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

    public Object removeMedicamentoNoEstoque(EstoqueAddMedicamentoDto estoqueAddMedicamentoDto) {
        validarExistenciaDoCnpjENroRegistro(estoqueAddMedicamentoDto.getNroRegistro(),
                estoqueAddMedicamentoDto.getCnpj());

        Optional<Estoque> estoqueOptional = estoqueRepository.findEstoqueByCnpjAndNroRegistro(estoqueAddMedicamentoDto.getCnpj(),
                estoqueAddMedicamentoDto.getNroRegistro());
        if (estoqueOptional.isEmpty()) {
            throw new BusinessException("Não tem estoque desse medicamento cadastrado para esse CNPJ.");
        }

        Estoque estoque = estoqueOptional.get();
        int quantidade = estoque.getQuantidade() - estoqueAddMedicamentoDto.getQuantidade();
        if (quantidade < 0) {
            throw new BusinessException("Não há medicamentos suficiente no estoque.");
        }

        if (quantidade == 0) {
            estoque.setQuantidade(quantidade);
            estoque.setDataAtualizacao(LocalDateTime.now());
            estoqueRepository.delete(estoque);
            return estoque;
        }

        estoque.setQuantidade(quantidade);
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
        return estoque;
    }

    private void validarExistenciaDoCnpjENroRegistro(Integer nroRegistro, Long... cnpj) {
        for (Long c : cnpj) {
            if (!farmaciaRepository.isCnpjExistente(c)) {
                throw new BusinessException("Este CNPJ não está cadastrado.");
            }
        }

        if (!medicamentoRepository.isNroRegistroExistente(nroRegistro)) {
            throw new BusinessException("Este Número de Registro não está cadastrado.");
        }
    }

    public Object transferirMedicamento(EstoqueTransferenciaDto estoqueTransferenciaDto) {
        validarExistenciaDoCnpjENroRegistro(estoqueTransferenciaDto.getNroRegistro(),
                estoqueTransferenciaDto.getCnpjOrigem(), estoqueTransferenciaDto.getCnpjDestino());

        Optional<Estoque> estoqueOrigemOptional = estoqueRepository.findEstoqueByCnpjAndNroRegistro(estoqueTransferenciaDto.getCnpjOrigem(),
                estoqueTransferenciaDto.getNroRegistro());
        if (estoqueOrigemOptional.isEmpty()) {
            throw new BusinessException("Não existe estoque desse medicamento na farmácia de origem.");
        }

        Optional<Estoque> estoqueDestinoOptional = estoqueRepository.findEstoqueByCnpjAndNroRegistro(estoqueTransferenciaDto.getCnpjDestino(),
                estoqueTransferenciaDto.getNroRegistro());
        if (estoqueDestinoOptional.isEmpty()) {
            throw new BusinessException("Não existe estoque desse medicamento na farmácia de destino.");
        }

        Estoque estoqueOrigem = estoqueOrigemOptional.get();
        int quantidadeOrigem = estoqueOrigem.getQuantidade() - estoqueTransferenciaDto.getQuantidade();
        if (quantidadeOrigem < 0) {
            throw new BusinessException("Não há medicamentos suficiente no estoque da farmácia de origem.");
        }

        estoqueOrigem.setQuantidade(quantidadeOrigem);
        estoqueOrigem.setDataAtualizacao(LocalDateTime.now());

        if (quantidadeOrigem == 0) {
            estoqueRepository.delete(estoqueOrigem);
        } else {
            estoqueRepository.save(estoqueOrigem);
        }

        Estoque estoqueDestino = estoqueDestinoOptional.get();
        estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + estoqueTransferenciaDto.getQuantidade());
        estoqueDestino.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoqueDestino);

        return getEstoqueTransferenciaRetornoDto(estoqueOrigem, estoqueDestino);
    }

    private static EstoqueTransferenciaRetornoDto getEstoqueTransferenciaRetornoDto(Estoque estoqueOrigem, Estoque estoqueDestino) {
        EstoqueTransferenciaRetornoDto estoqueTransferenciaRetornoDto = new EstoqueTransferenciaRetornoDto();
        estoqueTransferenciaRetornoDto.setNroRegistro(estoqueOrigem.getNroRegistro());
        estoqueTransferenciaRetornoDto.setCnpjOrigem(estoqueOrigem.getCnpj());
        estoqueTransferenciaRetornoDto.setQuantidadeOrigem(estoqueOrigem.getQuantidade());
        estoqueTransferenciaRetornoDto.setCnpjDestino(estoqueDestino.getCnpj());
        estoqueTransferenciaRetornoDto.setQuantidadeDestino(estoqueDestino.getQuantidade());
        return estoqueTransferenciaRetornoDto;
    }
}

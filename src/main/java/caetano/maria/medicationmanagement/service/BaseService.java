package caetano.maria.medicationmanagement.service;

import caetano.maria.medicationmanagement.enums.TipoMedicamentoEnum;
import caetano.maria.medicationmanagement.model.Endereco;
import caetano.maria.medicationmanagement.model.Estoque;
import caetano.maria.medicationmanagement.model.Farmacia;
import caetano.maria.medicationmanagement.model.Medicamento;
import caetano.maria.medicationmanagement.repository.EstoqueRepository;
import caetano.maria.medicationmanagement.repository.FarmaciaRepository;
import caetano.maria.medicationmanagement.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BaseService {
    private final FarmaciaRepository farmaciaRepository;

    private final MedicamentoRepository medicamentoRepository;

    private final EstoqueRepository estoqueRepository;

    public BaseService(FarmaciaRepository farmaciaRepository, MedicamentoRepository medicamentoRepository, EstoqueRepository estoqueRepository) {
        this.farmaciaRepository = farmaciaRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public void inicializarDados() {
        Farmacia farmacia1 = criarFarmacia1();
        Farmacia farmacia2 = criarFarmacia2();

        Medicamento medicamento1 = criarMedicamento1();
        Medicamento medicamento2 = criarMedicamento2();
        Medicamento medicamento3 = criarMedicamento3();
        Medicamento medicamento4 = criarMedicamento4();
        criarMedicamento5();
        Medicamento medicamento6 = criarMedicamento6();

        criarEstoque(farmacia1, medicamento1, 12);
        criarEstoque(farmacia1, medicamento2, 10);
        criarEstoque(farmacia2, medicamento2, 2);
        criarEstoque(farmacia2, medicamento3, 15);
        criarEstoque(farmacia2, medicamento4, 16);
        criarEstoque(farmacia2, medicamento6, 22);
    }

    private void criarEstoque(Farmacia farmacia1, Medicamento medicamento1, int quantidade) {
        Estoque estoque = new Estoque();
        estoque.setCnpj(farmacia1.getCnpj());
        estoque.setNroRegistro(medicamento1.getNroRegistro());
        estoque.setQuantidade(quantidade);
        estoque.setDataAtualizacao(LocalDateTime.now());

        estoqueRepository.save(estoque);
    }

    private Medicamento criarMedicamento6() {
        Medicamento medicamento6 = new Medicamento();
        medicamento6.setNroRegistro(4040);
        medicamento6.setNome("Propolizol");
        medicamento6.setLaboratorio("Bee");
        medicamento6.setDosagem("40mg");
        medicamento6.setDescricao("Nunc euismod ipsum purus, sit amet finibus libero ultricies in.");
        medicamento6.setPreco(23.50F);
        medicamento6.setTipo(TipoMedicamentoEnum.CONTROLADO);

        medicamentoRepository.save(medicamento6);
        return medicamento6;
    }

    private void criarMedicamento5() {
        Medicamento medicamento5 = new Medicamento();
        medicamento5.setNroRegistro(5656);
        medicamento5.setNome("Aspirazol");
        medicamento5.setLaboratorio("Acme");
        medicamento5.setDosagem("2mg");
        medicamento5.setDescricao("Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend.");
        medicamento5.setPreco(9.99F);
        medicamento5.setTipo(TipoMedicamentoEnum.CONTROLADO);

        medicamentoRepository.save(medicamento5);
    }

    private Medicamento criarMedicamento4() {
        Medicamento medicamento4 = new Medicamento();
        medicamento4.setNroRegistro(8880);
        medicamento4.setNome("Gelox");
        medicamento4.setLaboratorio("Ice");
        medicamento4.setDosagem("100mg");
        medicamento4.setDescricao("Quisque quam orci, vulputate sit amet.");
        medicamento4.setPreco(5.99F);
        medicamento4.setTipo(TipoMedicamentoEnum.COMUM);

        medicamentoRepository.save(medicamento4);
        return medicamento4;
    }

    private Medicamento criarMedicamento3() {
        Medicamento medicamento3 = new Medicamento();
        medicamento3.setNroRegistro(2233);
        medicamento3.setNome("Estomazol");
        medicamento3.setLaboratorio("Acme");
        medicamento3.setDosagem("150mg");
        medicamento3.setDescricao("Sed risus mauris, consectetur eget egestas vitae.");
        medicamento3.setPreco(102.90F);
        medicamento3.setTipo(TipoMedicamentoEnum.COMUM);

        medicamentoRepository.save(medicamento3);
        return medicamento3;
    }

    private Medicamento criarMedicamento2() {
        Medicamento medicamento2= new Medicamento();
        medicamento2.setNroRegistro(7473);
        medicamento2.setNome("Cafex");
        medicamento2.setLaboratorio("Colombia Farm");
        medicamento2.setDosagem("400mg");
        medicamento2.setDescricao("Pellentesque non ultricies mauris, ut lobortis elit.");
        medicamento2.setPreco(36.50F);
        medicamento2.setTipo(TipoMedicamentoEnum.COMUM);

        medicamentoRepository.save(medicamento2);
        return medicamento2;
    }

    private Medicamento criarMedicamento1() {
        Medicamento medicamento1 = new Medicamento();
        medicamento1.setNroRegistro(1010);
        medicamento1.setNome("Programapan");
        medicamento1.setLaboratorio("Matrix");
        medicamento1.setDosagem("20mg");
        medicamento1.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        medicamento1.setPreco(24.00F);
        medicamento1.setTipo(TipoMedicamentoEnum.COMUM);

        medicamentoRepository.save(medicamento1);
        return medicamento1;
    }

    private Farmacia criarFarmacia1() {
        Farmacia farmacia1 = new Farmacia();

        farmacia1.setCnpj(90561736000121L);
        farmacia1.setRazaoSocial("DevMed Ltda");
        farmacia1.setNomeFantasia("Farmácia DevMed");
        farmacia1.setEmail("devmed@farmacia.com");
        farmacia1.setTelefone("(48)4002-8922");
        farmacia1.setCelular("(48)94002-8922");

        Endereco endereco1 = criarEndereco1();

        farmacia1.setEndereco(endereco1);
        farmaciaRepository.save(farmacia1);
        return farmacia1;
    }

    private static Endereco criarEndereco1() {
        Endereco endereco1 = new Endereco();
        endereco1.setCep(88745000L);
        endereco1.setLogradouro("Rua Porto Real");
        endereco1.setNumero("100");
        endereco1.setBairro("Torre do Mago");
        endereco1.setCidade("Vale do Orvalho");
        endereco1.setEstado("SC");
        endereco1.setLatitude(15.23456D);
        endereco1.setLongitude(2.8678687D);
        return endereco1;
    }

    private Farmacia criarFarmacia2() {
        Farmacia farmacia2 = new Farmacia();

        farmacia2.setCnpj(43178995000198L);
        farmacia2.setRazaoSocial("MedHouse Ltda");
        farmacia2.setNomeFantasia("Farmácia MedHouse");
        farmacia2.setEmail("medhouse@farmacia.com");
        farmacia2.setTelefone("(49)4002-8922");
        farmacia2.setCelular("(49)94002-8922");

        Endereco endereco2 = criarEndereco2();

        farmacia2.setEndereco(endereco2);
        farmaciaRepository.save(farmacia2);
        return farmacia2;
    }

    private static Endereco criarEndereco2() {
        Endereco endereco2 = new Endereco();
        endereco2.setCep(88790000L);
        endereco2.setLogradouro("Rua da Liberdade");
        endereco2.setNumero("S/N");
        endereco2.setBairro("Fazenda");
        endereco2.setCidade("Vila Pelicanos");
        endereco2.setEstado("SC");
        endereco2.setLatitude(15.23456D);
        endereco2.setLongitude(2.8678687D);
        return endereco2;
    }
}

package service;

import org.springframework.beans.factory.annotation.Autowired;

import model.Cliente;
import model.ClienteRepository;
import model.Endereco;
import model.EnderecoRepository;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

public class ClienteServiceimp1 implements ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id)
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		ClienteSalvarCep(cliente);
		
	}

	private void ClienteSalvarCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep)orElseGet(() ->{
			Endereco novoEndereco = ViaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			ClienteSalvarCep(cliente);
		}
		
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
		
	}

}

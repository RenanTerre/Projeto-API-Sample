package br.edu.atitus.apisample.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.repositories.RegisterRepository;

@Service
public class RegisterService {

    private final RegisterRepository repository;

    public RegisterService(RegisterRepository repository) {
        super();
        this.repository = repository;
    }

    // Método save
    public RegisterEntity save(RegisterEntity newRegister) throws Exception {
        // Validação de regra de negócio
        if (newRegister.getUser() == null || newRegister.getUser().getId() == null)
            throw new Exception("Usuário não informado");
        if (newRegister.getLatitude() < -90 || newRegister.getLatitude() > 90)
            throw new Exception("Latitude Inválida");
        if (newRegister.getLongitude() < -180 || newRegister.getLongitude() > 180)
            throw new Exception("Longitude Inválida");

        // Invocar método save da camada repository
        repository.save(newRegister);

        // Retornar o objeto salvo
        return newRegister;
    }

    // Método findAll
    public List<RegisterEntity> findAll() throws Exception {
    	List<RegisterEntity> registers = repository.findAll();
    	
        return registers;
    }

    // Método findById
    public RegisterEntity findById(UUID id) throws Exception {
        RegisterEntity entity = repository.findById(id)
            .orElseThrow(() -> new Exception("Registro não encontrado com esse Id"));
        
        return entity;
    }

    // Método deleteById
    public void deleteById(UUID id) throws Exception {
        // Verifica se o registro existe antes de tentar deletar
        repository.deleteById(id);
    }
}

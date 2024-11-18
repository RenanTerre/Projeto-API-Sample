package br.edu.atitus.apisample.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.repositories.UserRepository;

//Spring, gerencia objetos dessa classe pra mim
@Service 
public class UserService {
	//Esssa classe possui uma dependencia de um objeto UserRepository
	private final UserRepository repository;
	//No método construtor existe a injeção de dependency
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
	
	public  UserEntity save(UserEntity newUser) throws Exception {
		// TODO Validar regras de negócio
		if (newUser == null)
			throw new Exception("Objeto nulo!");
		
		if (newUser.getName() == null || newUser.getName().isEmpty())
			throw new Exception("Nome inválido!");
		newUser.setName(newUser.getName().trim());
		
		if (newUser.getEmail() == null || newUser.getEmail().isEmpty())
			throw new Exception("Email inválido!");
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty())
			throw new Exception("Senha inválida!");
		
		//Validar o email com regex
		if (!isValidEmail(newUser.getEmail()))
			throw new Exception("Email inválido!");
		
		newUser.setEmail(newUser.getEmail().trim());
		
		if(repository.existsByEmail(newUser.getEmail()))
			throw new Exception("Já existe usuário com este e-mail");
		
		// Invocar método camada repository	
		this.repository.save(newUser);
		
		
		return newUser;
	}

}

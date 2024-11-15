package br.edu.atitus.apisample.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.UserEntity;

//Spring, gerencia objetos dessa classe pra mim
@Service 
public class UserService {
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
	
	public UserEntity save(UserEntity newUser) throws Exception {
		// TODO Validar regras de negócio
		if (newUser == null)
			throw new Exception("Objeto nulo!");
		
		if (newUser.getName() == null || newUser.getName().isEmpty())
			throw new Exception("Nome inválido!");
		newUser.setName(newUser.getName().trim());
		
		if (newUser.getEmail() == null || newUser.getEmail().isEmpty())
			throw new Exception("Email inválido!");
		//Validar o email com regex
		if (!isValidEmail(newUser.getEmail()))
			throw new Exception("Email inválido!");
		
		newUser.setEmail(newUser.getEmail().trim());
		
		// TODO Invocar método camada repository
		return newUser;
	}

}

package br.com.chocode.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chocode.back.dao.EntregadorDAO;
import br.com.chocode.back.model.Entregador;
import br.com.chocode.back.security.ChocodeCrypto;
import br.com.chocode.back.security.Token;
import br.com.chocode.back.security.TokenUtil;

@Component
public class EntregadorServiceImpl implements IEntregadorService {

	@Autowired
	private EntregadorDAO dao;

	@Override
	public Token gerarTokenDeUsuarioLogado(Entregador dadosLogin) {
		Entregador user = dao.findByEmail(dadosLogin.getEmail());
		try {

			if (user != null) { 
				
				// do ponto que estamos para uma senha criptografada, basta apenas
				// criptografarmos
				// a senha recebida e comparar os valores criptografados
				String senhaLogin = ChocodeCrypto.encrypt(dadosLogin.getSenha());

				System.out.println("Senha login = " + senhaLogin);
				System.out.println("Senha user  = " + user.getSenha());

				if (senhaLogin.equals(user.getSenha())) {
					return new Token(TokenUtil.createToken(user), user.getId());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Entregador save(Entregador entregador) {
		String senhaLogin;
		try {
			senhaLogin = ChocodeCrypto.encrypt(entregador.getSenha());
			entregador.setSenha(senhaLogin);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return dao.saveAndFlush(entregador);
	}

	@Override
	public List<Entregador> findAll() {
		List<Entregador> listaEntredadores = dao.findAll();
		return listaEntredadores;
	}

	public Entregador findById(Long id) {
		return dao.findById(id).get();
	}
}

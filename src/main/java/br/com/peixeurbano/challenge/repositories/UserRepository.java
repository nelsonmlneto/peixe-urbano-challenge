package br.com.peixeurbano.challenge.repositories;

import br.com.peixeurbano.challenge.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}

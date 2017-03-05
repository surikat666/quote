package com.strokova.quote.repository;

import com.strokova.quote.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}

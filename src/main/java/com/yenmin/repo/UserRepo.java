package com.yenmin.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yenmin.model.entity.User;

@Repository
public interface UserRepo extends MongoRepository<User, Long>{

	User findByUserSso(String userSso);
	Long deleteByUserSso(String userSso);

}

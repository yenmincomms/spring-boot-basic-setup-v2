package com.yenmin.service;

import java.util.List;
import java.util.Optional;

import com.yenmin.exception.YenminException;
import com.yenmin.model.ResponseMessage;
import com.yenmin.model.entity.User;

public interface IUserService {

	Object createUserDetails(User userDetails) throws YenminException;

	List<User> getAllUsers(Optional<Integer> pageIdx, Optional<Integer> pageSize, Optional<String> sortKey,
			Optional<String> orderDirection) throws YenminException;

	ResponseMessage deleteUserById(String sso) throws YenminException;

	User getUserById(String sso) throws YenminException;

}

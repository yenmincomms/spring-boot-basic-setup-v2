package com.yenmin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.yenmin.exception.YenminException;
import com.yenmin.model.ResponseMessage;
import com.yenmin.model.entity.User;
import com.yenmin.repo.UserRepo;
import com.yenmin.service.IUserService;
import com.yenmin.util.CommonUtil;

@Component
public class UserServiceImpl implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public Object createUserDetails(User userDetails) throws YenminException {
		User userResults = new User();
		try {
			User userInfo = null;
			userInfo = userRepo.findByUserSso(userDetails.getUserSso());
			if (userInfo != null) {
				userInfo.setFirstName(userDetails.getFirstName());
				userInfo.setLastName(userDetails.getLastName());
				userInfo.setUserEmail(userDetails.getUserEmail());
				userInfo.setUserPhone(userDetails.getUserPhone());
				userInfo.setUserSso(userDetails.getUserSso());
				userInfo.setModifiedBy(userDetails.getModifiedBy());
				userInfo.setModifiedDate(new Date());
				userResults = userRepo.save(userInfo);
			} else {
				userDetails.setCreatedBy(userDetails.getCreatedBy());
				userDetails.setCreatedDate(new Date());
				userDetails.setUserSso(userDetails.getUserSso());
				userResults = userRepo.save(userDetails);
			}
		} catch (Exception e) {
			LOG.error("Error in Creating users {}", e.getMessage());
		}
		return userResults;
	}

	@Override
	public List<User> getAllUsers(Optional<Integer> pageIdx, Optional<Integer> pageSize, Optional<String> sortKey,
			Optional<String> orderDirection) throws YenminException {
		List<User> users = new ArrayList<>();
		Pageable pageRequest = CommonUtil.getPageRequest(pageIdx, pageSize, sortKey, orderDirection);
		try {
			users = userRepo.findAll(pageRequest).toList();
		} catch (Exception e) {
			LOG.error("Error in fetching all users {}", e.getMessage());
		}
		return users;
	}

	@Override
	public ResponseMessage deleteUserById(String sso) throws YenminException {
		Object result = (long) 1;
		ResponseMessage message = new ResponseMessage();
		try {
			Object deleted = userRepo.deleteByUserSso(sso);
			if (deleted.equals(result)) {
				message.setMessage("User deleted Successfully");
				message.setSuccess(true);
			} else {
				message.setMessage("User SSO is not available");
				message.setSuccess(false);
			}
		} catch (Exception e) {
			LOG.error("Error in deleting user SSO {} : {}",sso, e.getMessage());
		}
		return message;
	}

	@Override
	public User getUserById(String sso) throws YenminException {
		User users = new User();
		try {
			users = userRepo.findByUserSso(sso);
		} catch (Exception e) {
			LOG.error("Error in fetching user SSO {}: {} ",sso, e.getMessage());
		}
		return users;
	}

}

package com.example.user.service;

import java.util.List;

import com.example.user.binding.ActivateUser;
import com.example.user.binding.LoginCredentials;
import com.example.user.binding.RecoverPassword;
import com.example.user.binding.UserAccount;

public interface IUserMgmtService {

	String registerUser(UserAccount account) throws Exception ;

	String activateUserAccount(ActivateUser activateUser);

	String login(LoginCredentials credentials);

	List<UserAccount> listUsers();

	UserAccount showUserByUserId(Integer id);
	
	UserAccount showUserByEmailAndName(String email,String name);
	
	String updateUser(UserAccount user);
	
	String deleteUserById(Integer id);
	
	String changeUserStatus(Integer id,String status);
	
	String recoverPassword(RecoverPassword password) throws Exception;
}

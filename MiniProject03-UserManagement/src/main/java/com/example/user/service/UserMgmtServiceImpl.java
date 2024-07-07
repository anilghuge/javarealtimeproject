package com.example.user.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.user.binding.ActivateUser;
import com.example.user.binding.LoginCredentials;
import com.example.user.binding.RecoverPassword;
import com.example.user.binding.UserAccount;
import com.example.user.dao.IUserMasterDAO;
import com.example.user.entity.UserMaster;
import com.example.user.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	@Autowired
	IUserMasterDAO userMasterDAO;

	@Autowired
	EmailUtils emailUtils;
	
	@Autowired
	private Environment env;

	@Override
	public String registerUser(UserAccount account) throws Exception {
		UserMaster master = new UserMaster();
		BeanUtils.copyProperties(account, master);
		String temPassword=RandGeneratedStr(6);
		master.setPassword(temPassword);
		master.setActive("InActive");
		UserMaster save = userMasterDAO.save(master);
		String subject="User Registration Success";
		String body=readEmailMessageBody(env.getProperty("mailbody.registeruser.location"),account.getName(),temPassword);
		emailUtils.sendEmailMessage(account.getEmail(), subject, body);
		return save != null ? "User is registered with id value ::" + save.getUserId() : "Problem is user registered";
	}

	private static String RandGeneratedStr(int length) {
		// a list of characters to choose from in form of a string
		String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
		// creating a StringBuffer size of AlphaNumericStr
		StringBuilder randomWord = new StringBuilder(length);
		int i;
		for (i = 0; i < length; i++) {
			// generating a random number using math.random()
			int ch = (int) (AlphaNumericStr.length() * Math.random());
			// adding Random character one by one at the end of s
			randomWord.append(AlphaNumericStr.charAt(ch));
		}
		return randomWord.toString();
	}

	@Override
	public String activateUserAccount(ActivateUser activateUser) {
//		UserMaster master=new UserMaster();
//		master.setEmail(activateUser.getEmail());
//		master.setPassword(activateUser.getTempPassword());
//		//check the record available by using email and temp password
//		Example<UserMaster> example=Example.of(master);
//		List<UserMaster> userList = userMasterDAO.findAll(example);
//		
//		if(userList.size()!=0) {
//			UserMaster entity=userList.get(0);
//			entity.setPassword(activateUser.getConfirmPassword());
//			entity.setActive("Active");
//			userMasterDAO.save(entity);
//			return "User is Activated";
//		}
//		return "User is not found for Activation";

		UserMaster userMaster = userMasterDAO.findByEmailAndPassword(activateUser.getEmail(),
				activateUser.getTempPassword());
		if (userMaster == null) {
			return "User is not found for Activation";
		} else {
			userMaster.setPassword(activateUser.getConfirmPassword());
			userMaster.setActive("Active");
			userMasterDAO.save(userMaster);
			return "User is Activated";
		}
	}

	@Override
	public String login(LoginCredentials credentials) {
		UserMaster master = new UserMaster();
		BeanUtils.copyProperties(credentials, master);
		Example<UserMaster> example = Example.of(master);
		List<UserMaster> list = userMasterDAO.findAll(example);
		if (list.size() == 0) {
			return "Invalid Credentials";
		} else {
			UserMaster userMaster = list.get(0);
			if (userMaster.getActive().equalsIgnoreCase("Active")) {
				return "Valid Credentials and Login successful";
			} else {
				return "User Account is not Active";
			}
		}
	}

	@Override
	public List<UserAccount> listUsers() {
		List<UserMaster> list = userMasterDAO.findAll();
		List<UserAccount> userAccounts = new ArrayList<>();
		list.forEach(l -> {
			UserAccount account = new UserAccount();
			BeanUtils.copyProperties(l, account);
			userAccounts.add(account);
		});
		return userAccounts;
	}

	@Override
	public UserAccount showUserByUserId(Integer id) {
		Optional<UserMaster> optional = userMasterDAO.findById(id);
		UserAccount account = null;
		if (optional.isPresent()) {
			account = new UserAccount();
			BeanUtils.copyProperties(optional.get(), account);
			return account;
		}
		return account;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		UserMaster master = userMasterDAO.findByEmailAndName(email, name);
		UserAccount account = null;
		if (master != null) {
			account = new UserAccount();
			BeanUtils.copyProperties(master, account);
		}
		return account;
	}

	@Override
	public String updateUser(UserAccount user) {
		Optional<UserMaster> master = userMasterDAO.findById(user.getUserId());
		if (master.isPresent()) {
			UserMaster userMaster = master.get();
			BeanUtils.copyProperties(user, userMaster);
			userMasterDAO.save(userMaster);
			return "User Details are updated";
		}
		return "User not found for updation";
	}

	@Override
	public String deleteUserById(Integer id) {
		Optional<UserMaster> optional = userMasterDAO.findById(id);
		if (optional.isPresent()) {
			userMasterDAO.deleteById(id);
			return "User is deleted";
		}
		return "user is not found for deletion";
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		Optional<UserMaster> optional = userMasterDAO.findById(id);
		if (optional.isPresent()) {
			UserMaster master = optional.get();
			master.setActive(status);
			userMasterDAO.save(master);
			return "User status change";
		}
		return "user not found for changing the status";
	}

	@Override
	public String recoverPassword(RecoverPassword recoverPassword) throws Exception {
		UserMaster master = userMasterDAO.findByEmailAndName(recoverPassword.getEmail(), recoverPassword.getName());
		if (master != null) {
			String pwd = master.getPassword();
			String subject="Mail for Password Recovery";
			String body=readEmailMessageBody(env.getProperty("mailbody.recoverpwd.location"),master.getName(),pwd);
			emailUtils.sendEmailMessage(master.getEmail(), subject, body);
		}
		return "User and Email is not found";
	}

	private String readEmailMessageBody(String fileName, String fullName, String pwd) {
		String mailBody = null;
		String url = "http://localhost:8083/activate";
		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {

			StringBuffer buffer = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				buffer.append(line);
			} while (line != null);

			mailBody = buffer.toString();
			mailBody=mailBody.replace("{FULL_NAME}", fullName);
			mailBody=mailBody.replace("{PWD}", pwd);
			mailBody=mailBody.replace("{URL}", url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mailBody;
	}

}

package services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import dto.UserDto;
import model.User;


@Service
public class UserServiceImpl implements UserService {
	
	UserDao dao;
	ModelMapper modelMapper;
	
	public UserServiceImpl(@Autowired UserDao dao, @Autowired ModelMapper modelMapper) {
		this.dao=dao;
		this.modelMapper=modelMapper;
	}

	@Override
	public List<UserDto> getUsers() {
        List<User> users = dao.getUsers();
        List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = dao.addUser(convertToEntity(userDto));
		return convertToDto(user);
	}

	@Override
	public List<UserDto> getUsersByParty(int party) {
		List<User> users = dao.getUsersByParty(party);
		List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
		return usersDto;
	}

	private UserDto convertToDto(User user) {
	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}

	private User convertToEntity(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    return user;
	}

}

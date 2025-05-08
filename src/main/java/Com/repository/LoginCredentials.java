package Com.repository;

import Com.Dto.LoginDto;

public interface LoginCredentials {
	boolean login(LoginDto l);
}
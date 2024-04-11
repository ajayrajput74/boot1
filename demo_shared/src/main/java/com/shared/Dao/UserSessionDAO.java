package com.shared.Dao;

import org.springframework.stereotype.Component;

import com.shared.entity.UserSession;

@Component
public interface UserSessionDAO {

	void updateUserSessionWhileDeleteUserSession(String accessToken, Integer integer);

	UserSession getSessionBySessionToken(String string);

	Integer save(UserSession session);

	UserSession getUserSessionByUserAndType(Integer userId, String agentType);

	void update(UserSession sessionOld);

}

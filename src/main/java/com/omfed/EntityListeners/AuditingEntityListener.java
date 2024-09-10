package com.omfed.EntityListeners;

import java.time.LocalDateTime;

import com.omfed.Entities.User;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditingEntityListener {

	@PrePersist
	public void prePersist(User user) {
		LocalDateTime now=LocalDateTime.now();
		user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setLastLogin(now);
	}
	@PreUpdate
	public void preUpdate(User user) {
		user.setUpdatedAt(LocalDateTime.now());
	}
}

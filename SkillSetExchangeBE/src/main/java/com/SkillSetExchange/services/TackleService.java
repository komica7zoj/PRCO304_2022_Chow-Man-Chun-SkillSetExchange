package com.SkillSetExchange.services;

import java.util.List;
import java.util.Map;

import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.repository.dao.TackleViewDAO;

import multichain.command.MultichainException;

public interface TackleService {
	List<TackleViewDAO> getTackleInfoByUsername(String username);
	int newTackleIssue(TackleInfo tackleInfo);
	List<TackleViewDAO> getTackleInfoByTackleName(String tackleName);
	int tackleAgreement( Map<String, Object> tackleInfo) throws MultichainException;
}

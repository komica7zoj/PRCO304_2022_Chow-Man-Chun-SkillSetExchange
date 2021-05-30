package com.SkillSetExchange.services;

import java.util.List;
import java.util.Map;

import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.models.DAO.CertificateDAO;
import com.SkillSetExchange.models.DAO.CreditUnitDAO;

import multichain.command.MultichainException;



public interface MultiChainService {


	public String sendasset(CreditUnitDAO creditUnitDAO, UserMultichainContent userMultichainContent,String metaData) throws MultichainException;
	public CreditUnitDAO getAssetBalances();
	public List<CertificateDAO> listCertificate();
	public Map<String,Object>getChainPassword();

	public void createConnection(UserMultichainContent userMultichainContent, String rpcPassword);
	
}

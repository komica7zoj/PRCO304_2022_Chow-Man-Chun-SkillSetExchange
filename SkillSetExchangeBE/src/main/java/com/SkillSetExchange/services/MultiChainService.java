package com.SkillSetExchange.services;

import java.util.List;

import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.models.DAO.CertificateDAO;
import com.SkillSetExchange.models.DAO.CreditUnitDAO;



public interface MultiChainService {

	public void createConnection(UserMultichainContent userMultichainContent);

	public String sendasset(CreditUnitDAO creditUnitDAO, UserMultichainContent userMultichainContent,String metaData);
	public CreditUnitDAO getAssetBalances();
	public List<CertificateDAO> listCertificate();
	
	
}

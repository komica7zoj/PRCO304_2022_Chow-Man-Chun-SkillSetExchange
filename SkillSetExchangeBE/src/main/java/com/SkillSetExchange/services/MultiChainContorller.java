package com.SkillSetExchange.services;

import java.util.List;

import com.SkillSetExchange.models.CertificateDAO;
import com.SkillSetExchange.models.CreditUnitDAO;
import com.SkillSetExchange.models.User;

public interface MultiChainContorller {

 
	public void sendasset(CreditUnitDAO creditUnitDAO, User user);
	public CreditUnitDAO getAssetBalances(User user);
	public List<CertificateDAO> listCertificate(User user);
	
	
}

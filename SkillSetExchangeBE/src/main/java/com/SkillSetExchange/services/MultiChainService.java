package com.SkillSetExchange.services;

import java.util.List;

import com.SkillSetExchange.models.CertificateDAO;
import com.SkillSetExchange.models.CreditUnitDAO;
import com.SkillSetExchange.models.UserMultichainContent;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

public interface MultiChainService {

	public void createConnection(UserMultichainContent userMultichainContent);

	public String sendasset(CreditUnitDAO creditUnitDAO, UserMultichainContent userMultichainContent);
	public CreditUnitDAO getAssetBalances();
	public List<CertificateDAO> listCertificate();
	
	
}

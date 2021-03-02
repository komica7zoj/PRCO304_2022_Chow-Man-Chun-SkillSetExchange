package com.SkillSetExchange.services.impl;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SkillSetExchange.models.CertificateDAO;
import com.SkillSetExchange.models.CreditUnitDAO;
import com.SkillSetExchange.models.User;
import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.services.MultiChainService;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;


@Service
public class MultiChainServiceImpl implements MultiChainService {
	BitcoinJSONRPCClient bitcoinClient;
	@Override
	public void sendasset(CreditUnitDAO creditUnitDAO, UserMultichainContent userMultichainContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public CreditUnitDAO getAssetBalances() {
		// TODO Auto-generated method stub
		
		CreditUnitDAO creditUnitDAO = new CreditUnitDAO();
		List<LinkedHashMap>  result = (List<LinkedHashMap>)bitcoinClient.query("gettotalbalances", 0);
		creditUnitDAO.balances = new BigDecimal(result.get(0).get("qty").toString());
		creditUnitDAO.assetName = result.get(0).get("name").toString();
		return creditUnitDAO;
	}

	@Override
	public List<CertificateDAO> listCertificate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createConnection(UserMultichainContent userMultichainContent) {
		// TODO Auto-generated method stub
		
		String user = userMultichainContent.username;
		String password = userMultichainContent.rpcPassword;
		String host = "127.0.0.1";
		int port = userMultichainContent.connectionPort;

		try {
		    URL url = new URL("http://" + user + ':' + password + "@" + host + ":" + port + "/");

		    BitcoinJSONRPCClient bitcoinClient = new BitcoinJSONRPCClient(url);
		    //System.out.println(bitcoinClient.getBalance());
		    this.bitcoinClient = bitcoinClient;
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}
		
	}

}

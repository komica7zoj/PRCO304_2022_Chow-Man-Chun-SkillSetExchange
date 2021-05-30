package com.SkillSetExchange.services.impl;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.SkillSetExchange.BlockChainUtil;
import com.SkillSetExchange.models.User;
import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.models.DAO.CertificateDAO;
import com.SkillSetExchange.models.DAO.CreditUnitDAO;
import com.SkillSetExchange.services.MultiChainService;

import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;


@Service
public class MultiChainServiceImpl implements MultiChainService {
	BitcoinJSONRPCClient bitcoinClient;
	CommandManager commandManager;
	@Override
	public String sendasset(CreditUnitDAO creditUnitDAO, UserMultichainContent userMultichainContent,String metaData) throws MultichainException {
		// TODO Auto-generated method stub
		//String resultSring = bitcoinClient.sendToAddress(userMultichainContent.multichainAddress, creditUnitDAO.balances, metaData);
		//String  resultA = (String)bitcoinClient.query("sendasset",userMultichainContent.multichainAddress, creditUnitDAO.assetName, creditUnitDAO.balances );
		//JSONObject param = new JSONObject();
		//param.put(creditUnitDAO.assetName, creditUnitDAO.balances);
		Map<String, Object> assetParam = new HashMap();
		Map<String, Object> metaDataParam = new HashMap();
		assetParam.put(creditUnitDAO.assetName, creditUnitDAO.balances);
		metaDataParam.put("text", metaData);
		//List<LinkedHashMap>  result = (List<LinkedHashMap>)bitcoinClient.query("send",userMultichainContent.multichainAddress,param);
		String message = (String) commandManager.invoke(CommandElt.SENDWITHDATA,userMultichainContent.multichainAddress, assetParam, metaDataParam);
		return message;
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
	public void createConnection(UserMultichainContent userMultichainContent, String rpcPassword) {
		// TODO Auto-generated method stub
		
		String user = userMultichainContent.username;
		String password =rpcPassword;
		String host = userMultichainContent.nodeAddress;
		int port = userMultichainContent.connectionPort;

		CommandManager commandManager= new CommandManager(host, String.valueOf(port), user,password);
this.commandManager = commandManager;
		
		try {
		    URL url = new URL("http://" + user + ':' + password + "@" + host + ":" + port + "/");

		    BitcoinJSONRPCClient bitcoinClient = new BitcoinJSONRPCClient(url);
		    //System.out.println(bitcoinClient.getBalance());
		    this.bitcoinClient = bitcoinClient;
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	public Map<String, Object> getChainPassword() {
		// TODO Auto-generated method stub
		try {
		Map<String, Object> userinfoMap = new HashMap<String,Object>();
		List<LinkedHashMap>  result = (List<LinkedHashMap>)bitcoinClient.query("liststreamitems","userinfo");
		result.forEach(userinfo ->{
			userinfoMap.put(userinfo.get("keys").toString(),((LinkedHashMap)((LinkedHashMap) userinfo.get("data")).get("json")).get("pw").toString()); 
		});
			
		return userinfoMap;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}

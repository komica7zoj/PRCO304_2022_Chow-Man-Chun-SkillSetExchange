package com.SkillSetExchange.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillSetExchange.BlockChainUtil;
import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.models.TransactionInfo;
import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.models.DAO.CreditUnitDAO;
import com.SkillSetExchange.models.DAO.SearchConditionDAO;
import com.SkillSetExchange.models.DAO.TackleInfoDAO;
import com.SkillSetExchange.repository.MultichainRepository;
import com.SkillSetExchange.repository.TackleRepository;
import com.SkillSetExchange.repository.TransactionRepository;
import com.SkillSetExchange.repository.dao.TackleViewDAO;
import com.SkillSetExchange.services.MultiChainService;
import com.SkillSetExchange.services.TackleService;

import multichain.command.MultichainException;

@Service
public class TackleServiceImpl implements TackleService {
	@Autowired
	TackleRepository tackleRepository;
	@Autowired
	TransactionRepository transactionRepository;
	
	  @Autowired
	  private MultiChainService multiChainService;
		
	  @Autowired
	  private MultichainRepository multichainRepository;
	@Override
	public  int newTackleIssue(TackleInfo tackleInfo) {
		// TODO Auto-generated method stub
		
			return tackleRepository.newTackle(tackleInfo);
	}


	@Override
	public List<TackleViewDAO> getTackleInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return tackleRepository.getTackleByUsername(username);
	}


	@Override
	public List<TackleViewDAO> getTackleInfoByTackleName(String tackleName) {
		// TODO Auto-generated method stub
		return tackleRepository.getTackleByTacklename(tackleName);

	}


	@Override
	public int tackleAgreement( Map<String, Object> tackleInfo) throws MultichainException {
		// TODO Auto-generated method stub
		if (tackleInfo.get("transactionInfoId") != null)
		{
			Long transId =((Number) tackleInfo.get("transactionInfoId")).longValue();
			UserMultichainContent userMultichainContent = multichainRepository.getUserMultichainContent(tackleInfo.get("tackleName").toString());
			UserMultichainContent tackleUserMultichainContent = multichainRepository.getUserMultichainContent(tackleInfo.get("username").toString());
		    String password =BlockChainUtil.chainPWMap.get("["+tackleUserMultichainContent.multichainAddress+"]").toString();
		    multiChainService.createConnection(tackleUserMultichainContent,password);
		    String username = tackleInfo.get("username").toString();
		    String skillSetName = tackleInfo.get("skillSetName").toString();
		    String academicLevel = tackleInfo.get("academicLevel").toString();
		    String transactionKey = multiChainService.sendasset(new CreditUnitDAO () {{ balances = new BigDecimal(tackleInfo.get("academicLevel").toString()); assetName = "70-266-41431";}}, userMultichainContent,
		    		username+" for "+skillSetName+" offer "+academicLevel+" credit unit to "+userMultichainContent.username);
		    
		    TransactionInfo transactionInfo = new TransactionInfo() {{id = transId; transactionHash = transactionKey; status = "S";}};
		    transactionRepository.updateTransaction(transactionInfo);
		    TackleInfo t_tackleInfo = new TackleInfo() {{status = "C";transactionInfoId = transId;}};
		    tackleRepository.completeTackle(t_tackleInfo);
		}
		else
		{
			
		}
		return 1;
	}
}

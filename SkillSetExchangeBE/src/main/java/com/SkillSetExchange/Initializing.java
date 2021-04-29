package com.SkillSetExchange;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.repository.MultichainRepository;
import com.SkillSetExchange.repository.TempRepository;
import com.SkillSetExchange.repository.dao.TempDAO;
import com.SkillSetExchange.services.MultiChainService;

@Component
public class Initializing implements InitializingBean {
	  @Autowired
	  private MultichainRepository multichainRepository;
	  
	  @Autowired
	  private  MultiChainService multiChainService;
	  @Autowired
	  private TempRepository tempRepository;
	  private   String MasterConnectPassword = getAlphaNumericString(128);
		private   String user = "multichainrpc";
		static String getAlphaNumericString(int n)
	    {
	  
	        // chose a Character random from this String
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz";
	  
	        // create StringBuffer size of AlphaNumericString
	        StringBuilder sb = new StringBuilder(n);
	  
	        for (int i = 0; i < n; i++) {
	  
	            // generate a random number between
	            // 0 to AlphaNumericString variable length
	            int index
	                = (int)(AlphaNumericString.length()
	                        * Math.random());
	  
	            // add Character one by one in end of sb
	            sb.append(AlphaNumericString
	                          .charAt(index));
	        }
	  
	        return sb.toString();
	    }
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
            //opening file for reading in Java

			String testing = null;
			List<TempDAO> tempdao ;
			while (testing == null)
			{
				if( (tempdao = tempRepository.getChainPW("")).get(0) != null)
				{
					testing = tempdao.get(0).geto_chainPW();
				}
				
			}

    	    StringBuffer inputBuffer = new StringBuffer();
    	    System.out.println("http://"+user+":"+testing+"@127.0.0.1:4766/");

            UserMultichainContent userMultichainContent = new UserMultichainContent();  
                	userMultichainContent.username=user;
                	userMultichainContent.connectionPort=4766;
                	userMultichainContent.nodeAddress="127.0.0.1";
                	multiChainService.createConnection(userMultichainContent,testing);
                	BlockChainUtil.chainPWMap=multiChainService.getChainPassword();
	}

}

import axios from 'axios';
import authHeader from './auth-header';
import {Search_API_URL} from '../router/index'
const API_URL = Search_API_URL;
//const qs = require('qs');

class SearchSkillSetService {
  getSkillSetFindResult(searchConditionDAO) {
 
///const form_data = new FormData();

//form_data.append('searchConditionDAO',JSON.stringify(dao))

  return axios.post( API_URL + 'findskillset',{searchConditionDAO},{headers: authHeader()});

//return axios.get( API_URL + 'findskillset', {data:{searchConditionDAO: dao}}, {headers: authHeader()});
    //return axios.get( API_URL + 'findskillset',{params:{searchConditionDAO: JSON.stringify(dao)}},  {headers: authHeader()});
  }
  
  getSkillSetPool() {
    return axios.get(API_URL + 'getskillsetpool', { headers: authHeader() });
  }

}

export default new SearchSkillSetService();
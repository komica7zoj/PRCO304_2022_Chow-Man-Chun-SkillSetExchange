import axios from 'axios';
import authHeader from './auth-header';
import {Search_API_URL} from '../router/index'
const API_URL = Search_API_URL;
//const qs = require('qs');

class SearchSkillSetService {
  getSkillSetFindResult(searchConditionDAO) {
  return axios.post( API_URL + 'findskillset',{searchConditionDAO},{headers: authHeader()});
  }
 
  getSkillSetInfoMatchResult(searchConditionDAO) {
  return axios.post( API_URL + 'getSkillSetList',{searchConditionDAO},{headers: authHeader()});
  } 
  
    viewSkillSet(id) {
  return axios.post( API_URL + 'viewskillset',{infoId:id},{headers: authHeader()});
  } 
  
  getSkillSetPool() {
    return axios.get(API_URL + 'getskillsetpool', { headers: authHeader() });
  }

}

export default new SearchSkillSetService();
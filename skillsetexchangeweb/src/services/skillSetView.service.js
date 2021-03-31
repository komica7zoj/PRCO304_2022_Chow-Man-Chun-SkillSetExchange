import axios from 'axios';
import authHeader from './auth-header';
import {Tackle_API_URL} from '../router/index'
const API_URL = Tackle_API_URL;
//const qs = require('qs');

class SkillSetViewService {
  issueTackle(dao) {
  return axios.post( API_URL + 'createTackle',{dao},{headers: authHeader()});
  }
  
  viewSkillSet(id, username) {
  return axios.post( API_URL + 'viewskillset',{infoId:id, username: username},{headers: authHeader()});
  } 
  

}

export default new SkillSetViewService();
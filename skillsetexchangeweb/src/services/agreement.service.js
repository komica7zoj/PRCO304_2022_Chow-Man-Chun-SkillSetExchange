import axios from 'axios';
import authHeader from './auth-header';
import {Tackle_API_URL} from '../router/index'
const API_URL = Tackle_API_URL;
//const qs = require('qs');

class AgreementService {
  Agree(tackleInfo) {
  return axios.post( API_URL + 'tackleAgree',tackleInfo,{headers: authHeader()});
  }
  
  Object(tackleInfo) {
  return axios.post( API_URL + 'tackleObject',tackleInfo,{headers: authHeader()});
  } 
  

}

export default new AgreementService();
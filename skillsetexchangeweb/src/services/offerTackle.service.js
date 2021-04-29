import axios from 'axios';
import authHeader from './auth-header';
import {Tackle_API_URL} from '../router/index'
const API_URL = Tackle_API_URL;
//const qs = require('qs');

class offerTackleService {
  acceptTackle(dao) {
  return axios.post( API_URL + 'acceptTackle',{dao},{headers: authHeader()});
  }
  

    viewTackle(username) {
  return axios.post( API_URL + 'viewTackle',{username: username},{headers: authHeader()});
  } 

}

export default new offerTackleService();
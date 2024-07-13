import setupAxios from './axios';
import {setTokens} from './token';

export default function setup() {
  setupAxios();
  setTokens();
}

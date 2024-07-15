import setupAxios from './axios';
import {checkTokens} from './token';

export default function setup() {
  checkTokens();
  setupAxios();
}

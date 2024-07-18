import {getTokens} from '../setup/token';
import {jwtDecode} from 'jwt-decode';

const getCurrentProfile = () => {
  var tokens = getTokens();

  if (tokens.accessToken === '') {
    return null;
  } else {
    const result = jwtDecode(tokens.accessToken);

    return {
      role: result.roles[0],
      user: result.sub
    };
  }
};

export default getCurrentProfile;

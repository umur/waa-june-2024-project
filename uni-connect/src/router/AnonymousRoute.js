import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Navigate } from "react-router";

const AnonymousRoute = ({ element }) => {
    const { isLoggedIn } = useContext(AuthContext);
    return !isLoggedIn ? element : <Navigate to="/home" />;
  };
  
  export default AnonymousRoute;
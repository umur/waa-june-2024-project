import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Navigate } from "react-router";

const ProtectedRoute = ({ element }) => {
    const { isLoggedIn } = useContext(AuthContext);
    return isLoggedIn ? element : <Navigate to="/login" />;
  };
  
  export default ProtectedRoute;
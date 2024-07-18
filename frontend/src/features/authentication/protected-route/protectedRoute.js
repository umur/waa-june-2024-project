import { Navigate } from "react-router-dom";
import getCurrentProfile from "../../../core/utils/current-profile";

export const ProtectedRoute = ({ children }) => {
    const user = getCurrentProfile();
  if (!user) {
    // user is not authenticated
    return <Navigate to="/login" />;
  }
  return children;
};
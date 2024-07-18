import {
    createBrowserRouter,
    Navigate,
    RouterProvider,

} from "react-router-dom";
import SignUp from "../page/auth/Signup";
import Login from "../page/auth/Login";
import Home from "../page/home";
import Profile from "../page/profile/Profile";
import ProtectedRoute from "./ProtectedRoute";
import AnonymousRoute from "./AnonymousRoute";
import Resource from "../page/resources/Resource";
import Event from "../page/event/Event";
import EventDetail from "../page/event/EventDetail";


const Router = () => {
  
    const routes = createBrowserRouter([
      {
        path: "/sign-up",
        element: <AnonymousRoute element={<SignUp />} />,
      },
      {
        path: "/login",
        element: <AnonymousRoute element={<Login />} />,
      },

      {
        path: "/home",
        element: <ProtectedRoute element={<Home />} />,
      },
      {
        path: "/users/:id/profile",
        element: <ProtectedRoute element={<Profile />} />,
      },
      {
        path: "/resources",
        element: <ProtectedRoute element={<Resource />} />,
      },
      {
        path:"/events",
        element: <ProtectedRoute element={<Event />} />,
      },
      {
        path:"/events/:id/details",
        element: <ProtectedRoute element={<EventDetail />} />,
      },
      {
        path: "*",
        element: <Navigate to="/home" replace />,
      },
    ]);
  
    return <RouterProvider router={routes} />;
  };
  
  export default Router;
import React, { createContext, useEffect, useState } from 'react';
import { decodeToken } from '../util/JwtDecodeUtil';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [username, setUsername] = useState("");
    const [userId, setUserId] = useState(0);

    function checkLoggedIn(){
        const user = sessionStorage.getItem('user');
        console.log(user)
        if (user) {
            setUser(JSON.parse(user));
        }
    }

    function setUser(user){
        setUsername(user.username);
        setUserId((decodeToken(user.accessToken)).userId);
        setIsLoggedIn(true);
    }

    useEffect(() => {
        checkLoggedIn();
    }, []);

    const logIn = (user) => {
        setUser(user);
    };

    const logOut = () => {
        setIsLoggedIn(false);
        setUserId(0);
        setUsername("");
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn,username,userId, logIn, logOut }}>
            {children}
        </AuthContext.Provider>
    );
};

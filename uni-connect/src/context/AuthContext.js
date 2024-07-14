import React, { createContext, useEffect, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [username, setUsername] = useState("");

    function checkLoggedIn(){
        const user = sessionStorage.getItem('user');
        if (user) {
            setUser(user);
        }
    }

    function setUser(user){
        setUsername(user.username);
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
        setUsername("");
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn,username, logIn, logOut }}>
            {children}
        </AuthContext.Provider>
    );
};

import { useState, useEffect, createContext, useContext } from 'react';
import initFirebase from 'initFirebase.js';


const AuthContext = createContext();

export function AuthProvider({ children }) {
  const auth = useAuthProvider();
  return <AuthContext.Provider value={auth}>{children}</AuthContext.Provider>;
}

export const useAuth = () => {
  return useContext(AuthContext);
};

function useAuthProvider() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const unsubscribe = firebase.auth().onAuthStateChanged(user => {
      if (user) {
        setUser(user);
      } else {
        setUser(false);
      }
    });

    return () => unsubscribe();
  }, []);

  const signin = (email, password) => {
    return initFirebase.auth().signInWithEmailAndPassword(email, password);
  };

  const signout = () => {
    return initFirebase.auth().signOut();
  };

  return {
    user,
    signin,
    signout
  };
}

import React from "react";
import styles from "../styles/register.module.css";
import { GoogleAuthProvider, getAuth, signInWithPopup, createUserWithEmailAndPassword } from 'firebase/auth';
import { getDatabase, ref, set } from "firebase/database";
import initFirebase from '../../initFirebase';
import { useRouter } from 'next/router';
import axios from 'axios';

const Register = () => {

  const router = useRouter(); // Initialize useRouter
  const app = initFirebase();
  const provider = new GoogleAuthProvider();
  const auth = getAuth();
  const db = getDatabase();

  const createUserInBackend = async (uid, username, email,url) => {
    const userData = {
      firebaseUID: uid,
      email: email,
      name: username,
      dpUrl: url, 
    };
    try {
    const response = await axios.post('http://localhost:8081/api/user/create-user', userData);
      console.log('User Created:', response.data);
    } catch (error) {
      console.log('Error creating user:', error);
    }

  };

    
  const signUpWithGoogle = async () => {
    try {
      const result = await signInWithPopup(auth, provider);
      const user = result.user;
      const dpUrl = user.photoURL;  // Fetching the profile picture URL
  
      await set(ref(db, 'users/' + user.uid), {
        username: user.displayName,
        email: user.email,
        photoURL: dpUrl  // Storing the profile picture URL in Firebase Realtime Database
      });
  
      await createUserInBackend(user.uid, user.displayName, user.email, dpUrl);  // Passing dpUrl to your backend function
      router.push('/profile'); // Redirect to profile page
    } catch (error) {
      console.error('Error signing up with Google:', error);
    }
  };
  

  const signUpWithEmail = async (e) => {
    e.preventDefault();
    const email = e.target.email.value;
    const password = e.target.password.value;
    const username = e.target.username.value;

    try {
      const userCredential = await createUserWithEmailAndPassword(auth, email, password);
      const user = userCredential.user;
      await set(ref(db, 'users/' + user.uid), {
        username: username,
        email: email
      });
      console.log(user.uid);
      await createUserInBackend(user.uid, username, email,"bsfajbsf");
      router.push('/profile'); // Redirect to profile page
    } catch (error) {
      console.error('Error signing up with email and password:', error);
    }
  };

  return (
    <div className={styles.registerContainer}>
      <div className={styles.card}>
        <div className={styles.right}>
          <h1 className={styles.title}>Create a new account</h1>
          <form className={styles.form} onSubmit={signUpWithEmail}> 
              <label htmlFor="username" className={styles.label}>
              </label>
              <input
                type="text"
                id="username"
                name="username"
                placeholder="Username"
                className={styles.textInput}
                required
              />
              <label htmlFor="email" className={styles.label}>
              </label>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="Email"
                className={styles.emailInput}
                required
              />
              <label htmlFor="password" className={styles.label}>
              </label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Password"
                className={styles.passwordInput}
                required
              />
            <div className={styles.buttons}>
              <button type="submit" className={styles.registerButton}>
                Sign up
              </button>
              <button type="button" className={styles.google} onClick={signUpWithGoogle}>Register with Google</button>  
            </div>
          </form>
        </div>
        <div className={styles.left}>
          <h1>Find the soul</h1>
          <span> sdbhj
            ksbgf
            hasvdf
            jsvfjh
            bsvh
            vbj
          </span>
          <div className={styles.login}>
            Already have an account?
          </div>
          <button className={styles.loginButton} onClick={() => { window.location.href = "/login";}}>
            Login
          </button>
        </div>
        
      </div>
    </div>
  );
};

export default Register;

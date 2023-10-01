import React from 'react';
import styles from '../styles/login.module.css';
import { useRouter } from 'next/router';
import { signInWithPopup, GoogleAuthProvider,signInWithEmailAndPassword } from 'firebase/auth';
import initFirebase from '../../initFirebase';
import { getAuth, signInWithRedirect } from 'firebase/auth';



const Login = () => {

  const router = useRouter();
  const app = initFirebase();
  const provider = new GoogleAuthProvider();
  const auth = getAuth();    


  const signInWithGoogle = async () => {
    try {
      const result = await signInWithPopup(auth, provider);
      console.log(result.user);
      router.push('/feed'); // Redirect to feed.js page
    } catch (error) {
      console.error('Error signing in with Google:', error);
    }
  };

  const signInWithEmail = async (e) => {
    e.preventDefault();
    const email = e.target.email.value;
    const password = e.target.password.value;

    try {
      await signInWithEmailAndPassword(auth, email, password);
      router.push('/feed'); // Redirect to feed.js page
    } catch (error) {
      console.error('Error signing in with email and password:', error);
    }
  };
  return (
    <div className={styles.loginContainer}>
      <div className={styles.card}>
        <div className={styles.left}>
          <h1 className={styles.head}>Find the soul</h1>
          <p>dasfghb
            sdkfan JMlr
             otU1AdVqdx5HTPG3l1
          </p>
          <span>Don't have an account? Sign Up</span>
          <button className={styles.registerButton} onClick={() => { window.location.href = '/register'; }}> Register</button>
        </div>
        <div className={styles.right}>
            <h1 className={styles.heading} >Login</h1>
            {/* <form className={styles.form}>
            <input type='email' id='email' name='email' placeholder='Email' className={styles.emailInput} />
            <input type='password' id='password' name='password' placeholder='Password' className={styles.passwordInput} />
            <div className={styles.remember}>
              <input type='checkbox' id='remember-me' name='remember-me' className={styles.checkbox} />
              <label htmlFor='remember-me' className={styles.label}>Remember Me</label>
            </div>
            <button type='submit' className={styles.loginButton}>Login</button>
            </form> */}
            <form className={styles.form} onSubmit={signInWithEmail}>
              <input type='email' id='email' name='email' placeholder='Email' className={styles.emailInput} />
              <input type='password' id='password' name='password' placeholder='Password' className={styles.passwordInput} />
              <div className={styles.remember}>
                <input type='checkbox' id='remember-me' name='remember-me' className={styles.checkbox} />
                <label htmlFor='remember-me' className={styles.label}>Remember Me</label>
              </div>
              <div className={styles.buttons}>
                <button type='submit' className={styles.loginButton} >Login</button>
                <button type='button' className={styles.loginButton} onClick={signInWithGoogle}>Sign In with Google</button>
              </div>
            </form>
          <a href='#' className={styles.forgotPassword}>Forgot Password?  </a>
          
        </div>
      </div>
    </div>
  );
};

export default Login;
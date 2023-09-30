import React from 'react';
import styles from '../styles/login.module.css';

const Login = () => {
  return (
    <div className={styles.loginContainer}>
      <div className={styles.card}>
        <div className={styles.left}>
          <h1 className={styles.head}>Find the soul</h1>
          <p>dasfghb
            sdkfan Jbsdfjkb
            jkbsdfjk
             tsBKuJY4kAkdiVsjeUV
             F5TyFgKMC57NwO
             9oaKjJJHfVWStJYW
            jsbfvl
             Kc3mDRlRXpdFhbk b
             jb gUQKUT0j6I71n5Mlr
             otU1AdVqdx5HTPG3l1
          </p>
          <span>Don't have an account? Sign Up</span>
          <button className={styles.registerButton}> Register</button>
        </div>
        <div className={styles.right}>
            <h1 className={styles.heading} >Login</h1>
            <form className={styles.form}>
            <input type='email' id='email' name='email' placeholder='Email' className={styles.emailInput} />
            <input type='password' id='password' name='password' placeholder='Password' className={styles.passwordInput} />
            <div className={styles.remember}>
              <input type='checkbox' id='remember-me' name='remember-me' className={styles.checkbox} />
              <label htmlFor='remember-me' className={styles.label}>Remember Me</label>
            </div>
            <button type='submit' className={styles.loginButton}>Login</button>
            </form>
          <a href='#' className={styles.forgotPassword}>Forgot Password?  </a>
          
        </div>
      </div>
    </div>
  );
};

export default Login;
import React from 'react';
import styles from '../styles/login.module.css';

const Login = () => {
  return (
    <div className={styles.loginContainer}>
      <div className={styles.card}>
        <div className={styles.logo}>
          {/* Our Logo */}
        </div>
        <h1 className={styles.tuttu}>Login</h1>
        <form>
          <div className={styles.inputGroup}>
            <input type='email' id='email' name='email' placeholder='Email' className={styles.emailInput} />
          </div>
          <div className={styles.inputGroup}>
            <input type='password' id='password' name='password' placeholder='Password' className={styles.passwordInput} />
          </div>
          <div className={styles.inputGroup}>
            <input type='checkbox' id='remember-me' name='remember-me' className={styles.checkbox} />
            <label htmlFor='remember-me' className={styles.label}>Remember Me</label>
          </div>
          <button type='submit' className={styles.loginButton}>Login</button>
        </form>
        <a href='#' className={styles.forgotPassword}>Forgot Password?  </a>
        <a href='#' className={styles.signUp}>Don't have an account? Sign Up</a>
      </div>
    </div>
  );
};

export default Login;
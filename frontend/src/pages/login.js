import React from 'react';
import styles from '../styles/login.module.css';

const Login = () => {
  return (
    <div className={styles.loginContainer}>
      <div className={styles.logo}>
        {/* Your Logo */}
      </div>
      <h1 className={styles.title}>Login</h1>
      <form>
        <div className={styles.inputGroup}>
          <label htmlFor='email' className={styles.label}>Email</label>
          <input type='email' id='email' name='email' required  className={styles.emailInput} />
        </div>
        <div className={styles.inputGroup}>
          <label htmlFor='password' className={styles.label}>Password</label>
          <input type='password' id='password' name='password' required className={styles.passwordInput}  />
        </div>
        <div className={styles.inputGroup}>
          <input type='checkbox' id='remember-me' name='remember-me' />
          <label htmlFor='remember-me' className={styles.label}>Remember Me</label>
        </div>
        <button type='submit' className={styles.loginButton}>Login</button>
      </form>
      <a href='#' className={styles.forgotPassword}>Forgot Password?</a>
      <a href='#' className={styles.signUp}>Sign Up</a>
    </div>
  );
};

export default Login;
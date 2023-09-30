import React from "react";
import styles from "../styles/register.module.css";
import { useAuth } from '../../authHook';
import Header from "../components/Header";
const Register = () => {
  return (
    <div className={styles.registerContainer}>
      <div className={styles.card}>
        <div className={styles.left}>

          <div className={styles.login}>
            Already have an account?
          </div>
          <button className={styles.loginButton}>
            Login
          </button>
        </div>
        <div className={styles.right}>
          <h1 className={styles.title}>Create a new account</h1>
          <form className={styles.form}> 
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
            <button type="submit" className={styles.registerButton}>
              Sign up
            </button>
          </form>
          <button className={styles.google}>Register with Google</button>
        </div>
      </div>
    </div>
  );
};

export default Register;

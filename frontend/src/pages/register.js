import React from "react";
import styles from "../styles/register.module.css";

const Register = () => {
  return (
    <div className={styles.registerContainer}>
      <div className={styles.card}>
        <div className={styles.logo}>{/* Our Logo */}</div>
        <h1 className={styles.title}>Register</h1>
        <form>
          <div className={styles.inputGroup}>
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
          </div>
          <div className={styles.inputGroup}>
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
          </div>
          <div className={styles.inputGroup}>
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
          </div>
          <button type="submit" className={styles.registerButton}>
            Register
          </button>
        </form>
        <a href="#" className={styles.login}>
          Already have an account? Login
        </a>
      </div>
    </div>
  );
};

export default Register;

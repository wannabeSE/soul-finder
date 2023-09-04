import React from 'react';
import Link from 'next/link';
import styles from '../styles/Header.module.css';

const Header = () => {
  return (
    <header className={styles.headerContainer}>
      <div className={styles.logoContainer}>
        <Link href='/'>
          <a className={styles.logo}>MyApp</a>
        </Link>
      </div>
      <nav className={styles.navContainer}>
        <Link href='/'>
          <a className={styles.navLink}>Home</a>
        </Link>
        <Link href='/about'>
          <a className={styles.navLink}>About</a>
        </Link>
        <Link href='/contact'>
          <a className={styles.navLink}>Contact</a>
        </Link>
      </nav>
      <div className={styles.authContainer}>
        <Link href='/login'>
          <a className={styles.authLink}>Login</a>
        </Link>
        <Link href='/register'>
          <a className={styles.authLink}>Register</a>
        </Link>
      </div>
    </header>
  );
};

export default Header;
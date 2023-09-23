import React, { useState } from 'react';
import styles from '../styles/Header.module.css';  // Make sure the path is correct
import Proimage from '../Assets/profile-pic.png'

const Header = () => {
  const [dropdownVisible, setDropdownVisible] = useState(false);

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  return (
    <nav className={styles.navbar}>
      <div className={styles.logo } onClick={() => window.location.href = '/'}>Logo</div>
      <div className={styles.menu}>
        <div className={styles.menuItem} onClick={() => window.location.href = '/feed'}>Feed</div>
        <div className={styles.menuItem} onClick={() => window.location.href = '/match'}>
          Match
          <span className={styles.notificationBell}>🔔</span>
        </div>
        <div className={styles.menuItem} onClick={() => window.location.href = '/contribute'}>Contribute</div>
        <div className={styles.profile}>
          <img src= {Proimage}   alt='Pro' className={styles.profileImage} onClick={toggleDropdown} />
          {dropdownVisible && (
            <div className={styles.dropdown}>
              <a href='/profile'>Profile</a>
              <a href='/settings'>Settings</a>
              <a href='/help'>Help</a>
              <a href='/logout'>Logout</a>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Header;

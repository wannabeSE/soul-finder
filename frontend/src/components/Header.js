import React, { useState ,useEffect } from 'react';
import styles from '../styles/Header.module.css';  // Make sure the path is correct
import { useRouter } from 'next/router';

const Header = () => {
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const router = useRouter();
  const [activePage, setActivePage] = useState('');  // Track the active page

  useEffect(() => {
    // You can add more paths as needed
    if (router.pathname === '/feed') {
      setActivePage('feed');
    } else if (router.pathname === '/match') {
      setActivePage('match');
    } else if (router.pathname === '/contribute') {
      setActivePage('contribute');
    } 
    else if (router.pathname === '/chat'){
      setActivePage('chat');
    }
    else {
      setActivePage('');
    }
  }, [router.pathname]);

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  return (
    <nav className={styles.navbar}>
      <div className={styles.logo} onClick={() => { window.location.href = '/'; setActivePage('home'); }}>
        <img className={styles.logo} src='https://png.pngtree.com/png-clipart/20190611/original/pngtree-wolf-logo-png-image_2306634.jpg' alt="logo"/>
      </div>
      <div className={styles.menu}>
        <div className={activePage === 'feed' ? styles.activeMenuItem : styles.menuItem} onClick={() => { window.location.href = '/feed'; setActivePage('feed'); }}>Feed</div>
        <div className={activePage === 'match' ? styles.activeMenuItem : styles.menuItem} onClick={() => { window.location.href = '/match'; setActivePage('match'); }}>
          Match
          <span className={styles.notificationBell}>🔔</span>
        </div>
        <div className={activePage === 'contribute' ? styles.activeMenuItem : styles.menuItem} onClick={() => { window.location.href = '/contribute'; setActivePage('contribute'); }}>Contribute</div>
        <div className={activePage === 'chat' ? styles.activeMenuItem : styles.menuItem} onClick={() => { window.location.href = '/chat'; setActivePage('chat'); }}>
          <img className={styles.chat} src='https://png.pngtree.com/png-clipart/20190611/original/pngtree-wolf-logo-png-image_2306634.jpg'/>
        </div>
        <div className={styles.profile}>
          <img src='https://png.pngtree.com/png-clipart/20190611/original/pngtree-wolf-logo-png-image_2306634.jpg' alt='Pro' className={styles.profileImage} onClick={toggleDropdown} />
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

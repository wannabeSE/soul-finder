import { useRouter } from 'next/router';
import React, { useEffect } from 'react';
import styles from '../styles/index.module.css';

const LandingPage = () => {

  const router = useRouter();
  const isLoggedIn = false; // Replace with your actual logic

  useEffect(() => {
    if (isLoggedIn) {
      router.push('/homepage');
    }
  }, [isLoggedIn]);

  const handleSignIn = () => {
    router.push('/login');
  };

  const handleSignUp = () => {
    router.push('/register');
  };

  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <div className={styles.logo}>Your Logo</div>
        <nav className={styles.nav}>
          <a href='#'>Home</a>
          <a href='#'>About Us</a>
          <a href='#'>How It Works</a>
          <a href='#'>Contact Us</a>
        </nav> 
        
        <div className={styles.authButtons}>
          <button onClick={handleSignIn}>Sign In</button>
          <button onClick={handleSignUp}>Sign Up</button>
        </div>
      </header>
      <section className={styles.hero}>
        <img src='your-banner-image.jpg' alt='Banner' />
        <h1>Your Catchy Headline</h1>
        <p>Your Subheadline</p>
        <button>Get Started</button>
      </section>
      <section className={styles.about}>
        <h2>About Us</h2>
        <p>Your Brief Description</p>
        <p>Your Mission Statement</p>
      </section>
      <section className={styles.features}>
        <div className={styles.feature}>
          <img src='ai-icon.png' alt='AI Matching' />
          <h3>AI Matching</h3>
          <p>Explain the AI-based image matching feature.</p>
        </div>
        <div className={styles.feature}>
          <img src='community-icon.png' alt='Community Validation' />
          <h3>Community Validation</h3>
          <p>Describe community validation.</p>
        </div>
        <div className={styles.feature}>
          <img src='safety-icon.png' alt='User Safety' />
          <h3>User Safety</h3>
          <p>Talk about secure communication and data privacy.</p>
        </div>
      </section>
      <footer className={styles.footer}>
        <div className={styles.footerLinks}>
          <a href='#'>Privacy Policy</a>
          <a href='#'>Terms of Service</a>
        </div>
        <div className={styles.socialMedia}>
          <a href='#'>Facebook</a>
          <a href='#'>Twitter</a>
          <a href='#'>Instagram</a>
        </div>
        <div className={styles.contactInfo}>
          <p>Contact Us: info@example.com</p>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
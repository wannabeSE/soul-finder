import React from 'react';
import styles from '../styles/contributeCard.module.css';
import Image from 'next/image';
import postImg from "./pic.jpg";

const ContributeCard = () => {
  return (
    <div className={styles.container}>
        <div className={styles.postContainer}>
            <div className={styles.post}>
                <div className={styles.title}> Your post </div>
                <div className={styles.imageContainer}>
                    <img className={styles.imageContainer} src ='https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80' alt = "lost post" />
                </div>
            </div>
            <div className={styles.post}>
                <div className={styles.title}> Matched post</div>
                <div className={styles.imageContainer}>
                    <img className={styles.imageContainer} src ='https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80' alt = "lost post" />

                </div>
                
            </div>
        </div>
        <div className={styles.buttons}>
            <button className={styles.acceptButton}> Accept</button>
            <button className={styles.declineButton}> Decline</button>

        </div>
    </div>
    
  );
};

export default ContributeCard;

import React from 'react';
import styles from '../styles/postCard.module.css';
import Image from 'next/image';
import postImg from "./pic.jpg";

const Card = ({ post }) => {
  return (
    <div className={styles.container}>

      <div className={styles.author}>
        <div className={styles.authorImage}>
        <img className={styles.authorImage} src="https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80" alt='Kire' />
        </div>
        <div className={styles.authorName}>Sifat Hossain</div>
      </div>

      <div className={styles.date}>11.9.2001</div>

      <div className={styles.postImage}>
        <img className = {styles.postImage} src = 'https://jeffcosheriffal.com/wp-content/uploads/2021/09/Missing-Deshun-Johnson-Jr.jpg' />
      </div>
      <button className={styles.foundButton}>Direct Message</button>
    </div>
  );
};

export default Card;

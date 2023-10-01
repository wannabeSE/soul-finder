import React from 'react';
import styles from '../styles/postCard.module.css';

const Card = ({ post }) => {
  return (
    <div className={styles.container}>
      <div className={styles.author}>
        <div className={styles.authorImage}>
          <img className={styles.authorImage} src={post.authorImage} alt={post.authorName} />
        </div>
        <div className={styles.authorName}>{post.authorName}</div>
      </div>

      <div className={styles.date}>{post.date}</div>

      <div className={styles.postImage}>
        <img className={styles.postImage} src={post.postImage} alt="Post" />
      </div>
      <button className={styles.foundButton}>Direct Message</button>
    </div>
  );
};

export default Card;

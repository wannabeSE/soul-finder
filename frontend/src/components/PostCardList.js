import React from 'react';
import Card from './PostCard';
import styles from '../styles/postCardList.module.css';

const CardList = ({ posts }) => {
  return (
    <div className={styles.container}>
      <div className={styles.posts}>
        {posts.map((post, index) => (
          <Card key={index} post={post} />
        ))}
      </div>
    </div>
  );
};

export default CardList;

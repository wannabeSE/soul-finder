import React from 'react';
import Card from './PostCard';
import styles from '../styles/postCardList.module.css';

const CardList = () => {
  return (
    <div className={styles.container}>
      {/* {posts.map((post, index) => (
        <Card />
      ))} */}
        <div className={styles.posts}>
            <Card/>
            <Card/>
            <Card/>
            <Card/>
        </div>
    </div>
  );
};

export default CardList;

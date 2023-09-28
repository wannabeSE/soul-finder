import React from 'react';
import MatchCard from './MatchCard';
import styles from '../styles/matchCardList.module.css';

const MatchCardList = () => {
  return (
    <div className={styles.container}>
        <div className={styles.posts}>
            <MatchCard/>
            <MatchCard/>
            <MatchCard/>
            <MatchCard/>
            <MatchCard/>

        </div>
    </div>
  );
};

export default MatchCardList;

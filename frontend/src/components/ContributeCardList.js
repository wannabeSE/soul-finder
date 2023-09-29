import React from 'react';
import ContributeCard from './ContributeCard';
import styles from '../styles/contributeCardList.module.css';

const ContributeCardList = () => {
  return (
    <div className={styles.container}>
        <div className={styles.posts}>
            <ContributeCard/>
            <ContributeCard/>
            <ContributeCard/>
            <ContributeCard/>
            <ContributeCard/>

        </div>
    </div>
  );
};

export default ContributeCardList;

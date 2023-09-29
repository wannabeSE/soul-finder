import React from "react";
import MatchCardList from "../components/MatchCardList";
import styles from "../styles/match.module.css"


const Match = () =>{
    return(
        <div className={styles.container}>
            <MatchCardList/>
        </div>
    )
};

export default Match;
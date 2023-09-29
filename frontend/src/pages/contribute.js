import React from "react";
import ContributeCardList from "../components/ContributeCardList";
import styles from "../styles/contribute.module.css"


const Contribute = () =>{
    return(
        <div className={styles.container}>
            <ContributeCardList/>
        </div>
    )
};

export default Contribute
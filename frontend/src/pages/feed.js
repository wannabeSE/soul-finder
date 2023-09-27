import React, { useState } from 'react';
import Header from '../components/Header.js';
import styles from '../styles/feed.module.css';

const Feed = () => {
    const [showLost, setShowLost] = useState(true);
    const [showDialog, setShowDialog] = useState(false);
    const [image, setImage] = useState(null);
  
    const handleImageChange = (e) => {
      const file = e.target.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
        setImage(reader.result);
      };
    };
  
    return (
      <div className={styles.container}>
        <Header />
        <div className={styles.insideNav}>
            <div className={styles.actionBar}>
            <button className={showLost ? styles.activeButton : styles.lostButton} onClick={() => setShowLost(true)}>Lost</button>
            <button className={!showLost ? styles.activeButton : styles.foundButton} onClick={() => setShowLost(false)}>Found</button>
            </div>
        </div>
        <div className = {styles.createButtonDiv}>
            <button className={styles.createPostButton} onClick={() => setShowDialog(true)}>Create Post</button>
        </div >
        <div className={styles.posts}>
          {showLost ? (
            <div className={styles.lostPosts}>
              {/* Render Lost Posts Here */}
            </div>
          ) : (
            <div className={styles.foundPosts}>
              {/* Render Found Posts Here */}
            </div>
          )}
        </div>
        {showDialog && (
          <div className={styles.dialog}>
            <div className={styles.dialogitems}>
                <h2 className={styles.createPost} >Create Post</h2>
                <div className={styles.imageUpload}>
                <label htmlFor='fileInput' className={styles.imageUpload}>
                    {image ? <img src={image} alt='Uploaded preview' />  : <span >📷</span> }
                </label>
                <input  id='fileInput' type='file' accept='image/*' onChange={handleImageChange} style={{ display: 'none' }} />
                </div>
                <div className={styles.buttons}>
                    <button className = {styles.postButton} onClick={() => { /* Handle Post Action Here */ }}>Post</button>
                    <button className = {styles.closeButton} onClick={() => setShowDialog(false)}>Close</button>
                </div>
            </div>
          </div>
        )}
      </div>
    );
  };
  
  export default Feed;
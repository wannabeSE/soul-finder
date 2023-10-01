import React, { useState } from 'react';
import styles from '../styles/feed.module.css';
import CardList from '../components/PostCardList';
import ReactCrop from 'react-image-crop';
import 'react-image-crop/dist/ReactCrop.css';
import { getAuth } from 'firebase/auth';
import axios from 'axios';
import { useRouter } from 'next/router';



const Feed = () => {
    const [showLost, setShowLost] = useState(true);
    const [showDialog, setShowDialog] = useState(false);
    const [images, setImages] = useState([]);
    const [currentImageIndex, setCurrentImageIndex] = useState(0);
    const [state,setState] = useState(null);

    const auth = getAuth();
    const user = auth.currentUser;


    // if(!user)
    // {
    //   router.push('/login');
    // }


    const handleImageChange = (e) => {
      let file = e.target.files[0];

      let files = Array.from(e.target.files);
      console.log(files);

      setState(files);

      const fileURLs = files.map((file) => URL.createObjectURL(file));
      setImages(fileURLs);
    };


    // Function to handle the "Next" button click
    const handleNextClick = () => {
      if (currentImageIndex < images.length - 1) {
        setCurrentImageIndex(currentImageIndex + 1);
      }
    };

    // Function to handle the "Previous" button click
    const handlePrevClick = () => {
      if (currentImageIndex > 0) {
        setCurrentImageIndex(currentImageIndex - 1);
      }
    };

    //posot to backend
    const postToBackend = async () => {
      const body = document.getElementById('body').value;
    
      if (!body || images.length === 0) {
        alert('Both fields are required!');
        return;
      }
    
      let formData = new FormData();

      for (let i = 0;i<state.length;i++)
      {
        formData.append('image',state[i]);
      }

      // Add post data to formData
      const postData = {
        body: body,
        userId: user.uid, // Assuming you have user's uid
      };

      formData.append('body',body);
      formData.append('userId',user.uid)


      // formData.append('postObject',postData);
      console.log(formData);
      try {
        const response = await axios.post('http://localhost:8081/api/post/save-post', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        },
        {
          withCredentials: true
        });
        console.log('Post saved:', response.data);
      } catch (error) {
        console.log('Error saving post:', error);
      }
    };
    

    

    return (
      <div className={styles.container}>
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
              <CardList />
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
              <h2 className={styles.createPost}>Create Post</h2>
              <div className={styles.bodydiv}>
                <input id='body' type='text' className={styles.body} placeholder="Write something">
                </input>
              </div>
              <div>
                <label htmlFor='fileInput' className={styles.imageUpload}>
                  {images.length > 0 ? (
                    <img src={images[currentImageIndex]} alt={`Uploaded preview ${currentImageIndex}`} />
                  ) : (
                    <img src='/image-solid.svg' alt='Default' />
                  )}
                </label>
                <div className={styles.scrollButtons}>
                  <button className={styles.prevButton} onClick={handlePrevClick}>
                    <img src='/chevron-left-solid.svg' alt='Previous' />
                  </button>
                  <button className={styles.nextButton} onClick={handleNextClick}>
                    <img src='/chevron-right-solid.svg' alt='Next' />
                  </button>
                </div>
                <input
                  id='fileInput'
                  type='file'
                  accept='image/*'
                  multiple
                  onChange={handleImageChange}
                  style={{ display: 'none' }}
                />
              </div>
              <div className={styles.buttons}>
                <button className={styles.postButton} onClick={postToBackend}>Post</button>
                <button className={styles.closeButton} onClick={() => setShowDialog(false)}>Close</button>
              </div>
            </div>
          </div>
        )}
      </div>
    );
  };
  
  export default Feed;
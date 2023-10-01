import React, { useState, useEffect } from "react";
import styles from "../styles/profile.module.css";
import { getAuth, updatePassword, reauthenticateWithCredential, EmailAuthProvider } from 'firebase/auth';
import { getDatabase, ref, set, get } from "firebase/database";
import { getStorage, ref as storageRef, uploadBytes } from "firebase/storage";
import { getDownloadURL } from 'firebase/storage';

const Profile = () => {
  const [contact, setContact] = useState("");
  const [username, setUsername] = useState("");
  const [showDialog, setShowDialog] = useState(false);
  const [newPassword, setNewPassword] = useState("");
  const [currentPassword, setCurrentPassword] = useState("");
  const [profilePic, setProfilePic] = useState(null);
  const [previewPic, setPreviewPic] = useState(null);


  const auth = getAuth();
  const user = auth.currentUser;
  const db = getDatabase();
  const storage = getStorage();

  // console.log(user.uid);


  useEffect(() => {
    // Fetch username and contact from Realtime Database
    const fetchData = async () => {
      const usernameSnap = await get(ref(db, `users/${user.uid}/username`));
      const contactSnap = await get(ref(db, `users/${user.uid}/contact`));
      setUsername(usernameSnap.val());
      setContact(contactSnap.val());
    };
    fetchData();
  }, []);

  useEffect(() => {
    const storage = getStorage();
    const profilePicRef = ref(storage, `profilePics/${user.uid}`);
    
    getDownloadURL(profilePicRef)
      .then((url) => {
        setProfilePic(url);
      })
      .catch((error) => {
        setProfilePic("https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg");
      });
  }, []);

  const handleContactUpdate = async () => {
    await set(ref(db, `users/${user.uid}/contact`), contact);
  };

  const handlePasswordChange = async () => {
    const credential = EmailAuthProvider.credential(user.email, currentPassword);
    await reauthenticateWithCredential(user, credential);
    await updatePassword(user, newPassword);
  };

  const handleProfilePicUpload = () => {
    if (!previewPic) {
      alert("Please select an image first.");
      return;
    }
  
    const storage = getStorage();
    const storageRef = ref(storage, `profilePics/${user.uid}`);
  
    const uploadTask = uploadBytesResumable(storageRef, previewPic);
  
    // Listen for state changes, errors, and completion of the upload.
    uploadTask.on('state_changed',
      (snapshot) => {
        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
        const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        console.log('Upload is ' + progress + '% done');
      },
      (error) => {
        // Handle unsuccessful uploads
        console.error("Error uploading image: ", error);
      },
      () => {
        // Handle successful uploads on complete
        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
          console.log('File available at', downloadURL);
          setProfilePic(downloadURL); // Update the profile picture URL in the state
        });
      }
    );
  };
  
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setProfilePic(URL.createObjectURL(file)); // For preview
    setPreviewPic(file); // To be uploaded
  };

  let DP = storage.profilePic;
  
  return (
    <div className={styles.container}>
      <div className={styles.profile}>
      <img src={profilePic || "https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg"} alt="User Photo" className={styles.profilePic} />
        <input type="file" onChange={handleFileChange} />
        <button onClick={handleProfilePicUpload} className={styles.updateDp}>Update Profile Pic</button>
        <h1>Name : {username}</h1>
        <div className={styles.contactDiv}>
          <input className={styles.typeContact} type="text" placeholder="Add contact number" value={contact} onChange={(e) => setContact(e.target.value)} />
          <button onClick={handleContactUpdate}>Update</button>
        </div>
        <button onClick={() => setShowDialog(true)} >Change Password</button>
        
        { showDialog && <div>
          <input type="password" placeholder="Current Password" onChange={(e) => setCurrentPassword(e.target.value)} />
          <input type="password" placeholder="New Password" onChange={(e) => setNewPassword(e.target.value)} />
          <button onClick={handlePasswordChange}>Change Password</button>
        </div>
        }
      </div>

    </div>
  );
};

export default Profile;

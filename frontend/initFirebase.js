import firebase from 'firebase/app';
import { initializeApp } from 'firebase/app'; 


const firebaseConfig = {
    apiKey: "AIzaSyB9sYQsL388ArJGksx2qMu8_4iTpwqDxts",
    authDomain: "find-the-soul.firebaseapp.com",
    projectId: "find-the-soul",
    storageBucket: "find-the-soul.appspot.com",
    messagingSenderId: "38522341380",
    appId: "1:38522341380:web:e66aaa632d85583c098d51"
};

const app = initializeApp(firebaseConfig);
const initFirebase = () => {

    return app;
}
export default initFirebase;
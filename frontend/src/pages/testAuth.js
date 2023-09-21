import { signInWithPopup, GoogleAuthProvider } from 'firebase/auth';
import initFirebase from '../../initFirebase';
import { getAuth, signInWithRedirect } from 'firebase/auth';


const Test = () => {

  const app = initFirebase();

  const provider  = new GoogleAuthProvider();
  const auth  = getAuth();
  console.log("getauth : ",auth);

  const signIn = async () => {
    const result = await signInWithPopup(auth,provider);
    console.log(result.user);
  }

  return (
    <div>
      Please sign in.
      <button onClick={signIn}>Sign in</button>
    </div>
  );

}
export default Test;

import Styles from 'util';'../styles/globals.css';
import Header from '../components/Header'; 
import { AuthProvider } from '../../authHook';
import initFirebase from '../../initFirebase';

function MyApp({ Component, pageProps }) {

  const app = initFirebase();

  console.log(app);

  return (
    <>
      
      <Component {...pageProps} />
      
    </>
  );
}

export default MyApp;
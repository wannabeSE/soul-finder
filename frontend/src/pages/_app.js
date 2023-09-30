import '../styles/global.css';
import Header from '../components/Header'; 
import { AuthProvider } from '../../authHook';
import initFirebase from '../../initFirebase';

function MyApp({ Component, pageProps }) {

  const app = initFirebase();

  console.log(app);

  return (
    <div>
    <Header/>
      <Component {...pageProps} />
    </div>
  );
}

export default MyApp;
import '../styles/globals.css';
import Header from '../components/Header';  // Import the Header component

function MyApp({ Component, pageProps }) {
  return (
    <>
      <Header />  {/* Include the Header component */}
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
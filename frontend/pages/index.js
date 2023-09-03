import React from 'react';

// Dummy data for posts
const posts = [
  { id: 1, content: 'This is the first post.' },
  { id: 2, content: 'This is the second post.' },
  { id: 3, content: 'This is the third post.' }
];

const HomePage = () => {
  return (
    <div>
      {/* Navigation Bar */}
      <div style={{ display: 'flex', justifyContent: 'space-between', padding: '1rem', backgroundColor: '#f2f2f2' }}>
        <div>
          <img src='/logo.png' alt='Logo' style={{ width: '50px' }} />
        </div>
        <div>
          <h1>Find the Soul</h1>
        </div>
        <div style={{ display: 'flex', gap: '1rem' }}>
          <div>Feed Icon</div>
          <div>Community Icon</div>
          <div>Profile Icon</div>
        </div>
      </div>

      {/* News Feed */}
      <div style={{ padding: '1rem' }}>
        {/* Post Box */}
        <div style={{ marginBottom: '1rem' }}>
          <textarea placeholder='Whats on your mind' rows='4' cols='50'></textarea>
          <button>Post</button>
        </div>

        {/* Scrollable Posts */}
        <div style={{ maxHeight: '400px', overflowY: 'scroll' }}>
          {posts.map(post => (
            <div key={post.id} style={{ marginBottom: '1rem', padding: '1rem', border: '1px solid #ccc' }}>
              {post.content}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
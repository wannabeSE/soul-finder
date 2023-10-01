import React, { useState } from 'react';
import ReactCrop from 'react-image-crop';
import 'react-image-crop/dist/ReactCrop.css';

const TestCrop = () => {
  const [crop, setCrop] = useState({ aspect: 1 });
  const [image, setImage] = useState(null);

  const onCropChange = (newCrop) => {
    console.log("Crop change:", newCrop);
    setCrop(newCrop);
  };

  const onSelectFile = (e) => {
    if (e.target.files && e.target.files.length > 0) {
      const reader = new FileReader();
      reader.addEventListener('load', () => setImage(reader.result));
      reader.readAsDataURL(e.target.files[0]);
    }
  };

  return (
    <div>
      {image && (
        <ReactCrop
          src={image}
          crop={crop}
          onChange={onCropChange}
        />
      )}
      <input type="file" accept="image/*" onChange={onSelectFile} />
    </div>
  );
};

export default TestCrop;

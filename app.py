#!/usr/bin/env python
# coding: utf-8

# In[1]:


from flask import Flask,request
app = Flask(__name__)

from flask_cors import CORS
CORS(app)
import tensorflow as tf
import keras
from keras.models import load_model
import re
import pickle
import numpy as np
from keras.preprocessing import image
from keras.applications.inception_resnet_v2 import preprocess_input, decode_predictions
from tensorflow.keras import Model
from tensorflow.keras.layers import Input, Dense
from tensorflow.keras.applications.inception_resnet_v2 import InceptionResNetV2


# In[2]:


with open('encoder.pkl','rb') as f:
    one_hot = pickle.load(f)
    f.close()


# In[3]:


def create_model():
    global model
    backbone = InceptionResNetV2(weights='imagenet', include_top=False, pooling='avg', input_shape=(299,299,3))
    outputs = Dense(120, activation='softmax')(backbone.output)
    model = Model(inputs=backbone.input, outputs=outputs)
    model.load_weights('model-v1.h5')
    model.compile(optimizer='adam', loss='categorical_crossentropy', metrics = ["accuracy"])
    model._make_predict_function()


# In[4]:


def preprocess_image(img):
    Image = image.load_img("train/" + img + ".jpg", target_size=(299, 299))
    data = image.img_to_array(Image)
    data = preprocess_input(data)
    return data


# In[5]:


def un_onehot(data):
    breed = one_hot.inverse_transform(data)
    return breed


# In[6]:


one_hot


# In[7]:


@app.route('/upload',methods=['GET'])
def upload_file():
    file = request.files['image']
    x = preprocess_image(file)
    breed = model.predict(x)
    label = un_onehot(breed)
    return json.dumps(dict("breed:", label))


# In[ ]:





# In[8]:


@app.route('/')
def render_static():
    upload_file()
    return render_template('%s.html' %index)

if __name__ == '__main__':
    create_model()
    app.run(host='0.0.0.0',port=8000, debug = True)


# In[ ]:




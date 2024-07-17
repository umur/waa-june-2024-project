import React, {useCallback, useEffect, useState} from 'react';
import {initialFeedbackCategoryForm as initialForm} from '../../types/types';
import {getAllApi, saveApi, updateApi} from '../../service/apiFeedbackCategory';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import FeedbaclCategoryList from './FeedbackCategoryList';
import FeedbackCategoryModal from './FeedbackCategoryModal';
import {State} from '../../core/constants';
import axios from 'axios';

const FeedbackCategory = () => {
  const [feedbackCategoryForm, setFeedbackCategoryForm] = useState(initialForm);
  const [feedbackCategorysList, setFeedbackCategorysList] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const [feedbackCategoryState, setFeedbackCategoryState] = useState({
    status: State.IDLE,
    error: null,
    errors: {}
  });

  useEffect(() => {
    fetchFeedbackCategorys();
  }, [refresh]);

  const fetchFeedbackCategorys = useCallback(async () => {
    try {
      const data = await getAllApi();
      setFeedbackCategorysList(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
  }, []);

  const resetForm = () => {
    setFeedbackCategoryForm(initialForm);
    setFeedbackCategoryState({errors: {}, error: null, status: State.IDLE});
  };

  const [show, setShow] = useState(false);

  const handleClose = () => {
    resetForm();
    setShow(false);
  };
  const handleShow = () => setShow(true);

  const handleSave = async () => {
    try {
      setFeedbackCategoryState({errors: {}, error: null, status: State.IDLE});

      const res = await axios.post('/admins/feedbackcategories', feedbackCategoryForm);

      // Handle the response
      if (res.status === 201) {
        setFeedbackCategoryState({...feedbackCategoryState, status: State.SUCCEEDED});
        setRefresh(!refresh);
        resetForm();
        setShow(false);
      } else if (res.status === 400) {
        setFeedbackCategoryState({
          status: State.FAILED,
          error: res.data.message || 'Something went wrong. Please try again later.'
        });
      }
    } catch (err) {
      // Handle errors
      if (err.response && err.response.status === 400) {
        setFeedbackCategoryState({
          status: State.FAILED,
          error: null,
          errors: err.response.data
        });
      } else {
        setFeedbackCategoryState({
          status: State.FAILED,
          errors: {},
          error: 'Something went wrong. Please try again later.'
        });
      }
    }
  };

  const handleChange = event => {
    const {name, value} = event.target;

    setFeedbackCategoryForm(prevFeedbackCategoryForm => ({
      ...prevFeedbackCategoryForm,
      [name]: value
    }));
  };

  return (
    <div className="App">
      <button className="btn btn-primary" onClick={handleShow}>
        Add Feedback Category
      </button>

      <FeedbackCategoryModal
        show={show}
        handleClose={handleClose}
        handleSave={handleSave}
        title="Feedback Category"
        feedbackCategoryForm={feedbackCategoryForm}
        feedbackCategoryState={feedbackCategoryState}
        handleChange={handleChange}
      />

      <FeedbaclCategoryList
        feedbackCategorysList={feedbackCategorysList}
        setFeedbackCategoryForm={setFeedbackCategoryForm}
        setFeedbackCategorysList={setFeedbackCategorysList}
        setShow={setShow}
      />
    </div>
  );
};

export default FeedbackCategory;

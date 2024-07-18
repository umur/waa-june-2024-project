import React, {useCallback, useEffect, useState} from 'react';
import {initialFeedbackForm as initialForm} from '../../types/types';
import {getAllApi, getAllCategoriesApi, saveApi, updateApi} from '../../service/apiFeedbackService';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import FeedbackModal from './FeedbackModal';
import FeedbackList from './FeedbackList';
import {State} from '../../core/constants';

const Feedback = () => {
  const [feedbackForm, setFeedbackForm] = useState(initialForm);
  const [feedbacksList, setFeedbacksList] = useState([]);
  const [feedbackCategory, setFeedbackCategory] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const [feedbackState, setFeedbackState] = useState({
    status: State.IDLE,
    error: null,
    errors: {}
  });

  useEffect(() => {
    fetchFeedbacks();
    fetchAllCategories();
  }, [refresh]);

  const fetchFeedbacks = useCallback(async () => {
    try {
      const data = await getAllApi();
      console.log(data);
      setFeedbacksList(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
      console.error('Error fetching feedback:', error);
    }
  }, []);

  const fetchAllCategories = useCallback(async () => {
    try {
      const data = await getAllCategoriesApi();
      setFeedbackCategory(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
      console.error('Error fetching feedback:', error);
    }
  }, []);

  const resetForm = () => {
    setFeedbackForm(initialForm);
    setShow(false);
  };

  const [show, setShow] = useState(false);

  const handleClose = () => {
    resetForm();
    setShow(false);
  };

  const handleShow = () => setShow(true);

  const handleSave = async () => {
    try {
      setFeedbackState({errors: {}, error: null, status: State.IDLE});

      const res = feedbackForm.id > 0 ? await updateApi(feedbackForm) : await saveApi(feedbackForm);
      setRefresh(!refresh);
      resetForm();
      // Handle the response
      if (res.status === 201) {
        setFeedbackState({...feedbackState, status: State.SUCCEEDED});
        setShow(false);
      } else if (res.status === 400) {
        setFeedbackState({
          status: State.FAILED,
          error: res.data.message || 'Something went wrong. Please try again later.'
        });
      }
    } catch (err) {
      // Handle errors
      if (err.response && err.response.status === 400) {
        setFeedbackState({
          status: State.FAILED,
          error: null,
          errors: err.response.data
        });
      } else {
        setFeedbackState({
          status: State.FAILED,
          errors: {},
          error: 'Something went wrong. Please try again later.'
        });
      }
    }
  };
  const handleChange = event => {
    const {name, value} = event.target;

    setFeedbackForm(prevFeedbackCategoryForm => ({
      ...prevFeedbackCategoryForm,
      [name]: value
    }));
  };

  return (
    <div className="App">
      <button className="btn btn-primary" onClick={handleShow}>
        Add Feedback
      </button>

      <FeedbackModal
        show={show}
        handleClose={handleClose}
        handleSave={handleSave}
        title="Feedback"
        feedbackForm={feedbackForm}
        allCategories={feedbackCategory}
        handleChange={handleChange}
        feedbackState={feedbackState}
      />

      <FeedbackList
        setFeedbackForm={setFeedbackForm}
        setFeedbacksList={setFeedbacksList}
        feedbacksList={feedbacksList}
        setShow={setShow}
      />
    </div>
  );
};

export default Feedback;

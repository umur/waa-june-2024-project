import React, {useCallback, useEffect, useState} from 'react';
import {initialFeedbackForm as initialForm} from '../../types/types';
import {getAllApi, getAllCategoriesApi, saveApi, updateApi} from '../../service/apiFeedbackService';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import FeedbackModal from './FeedbackModal';
import FeedbackList from './FeedbackList';

const Feedback = () => {
  const [feedbackForm, setFeedbackForm] = useState(initialForm);
  const [feedbacksList, setFeedbacksList] = useState([]);
  const [feedbackCategory, setFeedbackCategory] = useState([]);
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    fetchFeedbacks();
    fetchAllCategories();
  }, [refresh]);

  const fetchFeedbacks = useCallback(async () => {
    try {
      const data = await getAllApi();
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
  };

  const [show, setShow] = useState(false);

  const handleClose = () => {
    resetForm();
    setShow(false);
  };

  const handleShow = () => setShow(true);

  const handleSave = async event => {
    event.preventDefault();
    try {
      console.log(feedbackForm.id);
      feedbackForm.id > 0 ? await updateApi(feedbackForm) : await saveApi(feedbackForm);
      setRefresh(!refresh);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
    resetForm();
    setShow(false);
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

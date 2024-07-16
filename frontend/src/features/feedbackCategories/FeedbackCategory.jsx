import React, {useCallback, useEffect, useState} from 'react';
import {initialFeedbackCategoryForm as initialForm} from '../../types/types';
import {getAllApi, saveApi, updateApi} from '../../service/apiFeedbackCategory';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import FeedbaclCategoryList from './FeedbackCategoryList';
import FeedbackCategoryModal from './FeedbackCategoryModal';

const FeedbackCategory = () => {
  const [feedbackCategoryForm, setFeedbackCategoryForm] = useState(initialForm);
  const [feedbackCategorysList, setFeedbackCategorysList] = useState([]);

  const [refresh, setRefresh] = useState(false);

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
  };

  const [show, setShow] = useState(false);

  const handleClose = () => {
    resetForm();
    setShow(false);
  };
  const handleShow = () => setShow(true);

  const handleSave = async event => {
    console.log(feedbackCategoryForm);
    event.preventDefault();
    try {
      feedbackCategoryForm.id > 0 ? await updateApi(feedbackCategoryForm) : await saveApi(feedbackCategoryForm);
      setRefresh(!refresh);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
    resetForm();
    setShow(false);
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

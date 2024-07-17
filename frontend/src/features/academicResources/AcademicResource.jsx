import React, {useCallback, useEffect, useState} from 'react';
import {initialAcademicForm as initialForm} from '../../types/types';
import {getAllApi, getAllCategoriesApi, saveApi, updateApi} from '../../service/apiAcademicResource';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import AcademicResourceModal from './AcademicResourceModal';
import AcademicResourceList from './AcademicResourceList';
import {State} from '../../core/constants';

const AcademicResource = () => {
  const [academicResForm, setAcademicResForm] = useState(initialForm);
  const [academicResourceList, setAcademicResourceList] = useState([]);
  const [resCategory, setResCategory] = useState([]);
  const [refresh, setRefresh] = useState(false);

  const [academicResState, setAcademicResState] = useState({
    status: State.IDLE,
    error: null,
    errors: {}
  });

  useEffect(() => {
    fetchAcademicResource();
    fetchAllCategories();
  }, [refresh]);

  const fetchAcademicResource = useCallback(async () => {
    try {
      const data = await getAllApi();
      setAcademicResourceList(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
  }, []);

  const fetchAllCategories = useCallback(async () => {
    try {
      const data = await getAllCategoriesApi();

      setResCategory(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
  }, []);

  const resetForm = () => {
    setAcademicResForm(initialForm);
  };

  const [show, setShow] = useState(false);

  const handleClose = () => {
    resetForm();
    setShow(false);
  };

  const handleShow = () => setShow(true);

  const handleSave = async () => {
    console.log(academicResForm);
    try {
      setAcademicResState({errors: {}, error: null, status: State.IDLE});

      const res = academicResForm.id > 0 ? await updateApi(academicResForm) : await saveApi(academicResForm);
      setRefresh(!refresh);
      resetForm();
      setShow(false);
      // Handle the response
      if (res.status === 201) {
        setAcademicResState({...academicResState, status: State.SUCCEEDED});
      } else if (res.status === 400) {
        setAcademicResState({
          status: State.FAILED,
          error: res.data.message || 'Something went wrong. Please try again later.'
        });
      }
    } catch (err) {
      // Handle errors
      if (err.response && err.response.status === 400) {
        setAcademicResState({
          status: State.FAILED,
          error: null,
          errors: err.response.data
        });
      } else {
        setAcademicResState({
          status: State.FAILED,
          errors: {},
          error: 'Something went wrong. Please try again later.'
        });
      }
    }
  };

  const handleChange = event => {
    const {name, value, files} = event.target;

    if (name === 'file') {
      setAcademicResForm(prevFormData => ({
        ...prevFormData,
        file: files[0]
      }));
    } else {
      setAcademicResForm(prevFormData => ({
        ...prevFormData,
        [name]: value
      }));
    }
  };

  return (
    <div className="App">
      <button className="btn btn-primary" onClick={handleShow}>
        Add Academic Resource
      </button>

      <AcademicResourceModal
        show={show}
        handleClose={handleClose}
        handleSave={handleSave}
        title="Academic Resource"
        academicResForm={academicResForm}
        allCategories={resCategory}
        handleChange={handleChange}
        academicResState={academicResState}
      />

      <AcademicResourceList
        setAcademicResForm={setAcademicResForm}
        setAcademicResourceList={setAcademicResourceList}
        academicResourceList={academicResourceList}
        setShow={setShow}
      />
    </div>
  );
};

export default AcademicResource;

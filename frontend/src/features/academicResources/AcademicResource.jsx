import React, {useCallback, useEffect, useState} from 'react';
import {initialAcademicForm as initialForm} from '../../types/types';
import {getAllApi, getAllCategoriesApi, saveApi, updateApi} from '../../service/apiAcademicResource';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import AcademicResourceModal from './AcademicResourceModal';
import AcademicResourceList from './AcademicResourceList';

const AcademicResource = () => {
  const [academicResForm, setAcademicResForm] = useState(initialForm);
  const [academicResourceList, setAcademicResourceList] = useState([]);
  const [resCategory, setResCategory] = useState([]);
  const [refresh, setRefresh] = useState(false);

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

  const handleSave = async event => {
    event.preventDefault();
    try {
      academicResForm.id > 0 ? await updateApi(academicResForm) : await saveApi(academicResForm);
      setRefresh(!refresh);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
    resetForm();
    setShow(false);
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

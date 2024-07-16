import React, {useCallback, useEffect, useState} from 'react';
import {initialAcademicResourceTypeForm as initialForm} from '../../types/types';
import {getAllApi, saveApi, updateApi} from '../../service/apiAcademicResourceTypes';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import AcademicResourceTypeList from './AcademicResourceTypeList';
import AcademicResourceTypeModal from './AcademicResourceTypeModal';

const AcademicResourceType = () => {
  const [resourceTypeForm, setResourceTypeForm] = useState(initialForm);
  const [resourceTypesList, setResourceTypesList] = useState([]);
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    fetchAcademicResourceTypes();
  }, [refresh]);

  const fetchAcademicResourceTypes = useCallback(async () => {
    try {
      const data = await getAllApi();
      setResourceTypesList(data);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
  }, []);

  const resetForm = () => {
    setResourceTypeForm(initialForm);
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
      resourceTypeForm.id > 0 ? await updateApi(resourceTypeForm) : await saveApi(resourceTypeForm);
      setRefresh(!refresh);
    } catch (error) {
      <ErrorDialog show={'failed'} errorMessage={'Error'} handleClose={() => {}} />;
    }
    resetForm();
    setShow(false);
  };

  const handleChange = event => {
    const {name, value} = event.target;

    setResourceTypeForm(prevFeedbackCategoryForm => ({
      ...prevFeedbackCategoryForm,
      [name]: value
    }));
  };

  return (
    <div className="App">
      <button className="btn btn-primary" onClick={handleShow}>
        Add Academic Resource Type
      </button>

      <AcademicResourceTypeModal
        show={show}
        handleClose={handleClose}
        handleSave={handleSave}
        title="Academic Resource Category"
        resourceTypeForm={resourceTypeForm}
        handleChange={handleChange}
      />

      <AcademicResourceTypeList
        resourceTypesList={resourceTypesList}
        setResourceTypeForm={setResourceTypeForm}
        setResourceTypesList={setResourceTypesList}
        setShow={setShow}
      />
    </div>
  );
};

export default AcademicResourceType;

import React, {useCallback, useEffect, useState} from 'react';
import {initialAcademicResourceTypeForm as initialForm} from '../../types/types';
import {getAllApi, saveApi, updateApi} from '../../service/apiAcademicResourceTypes';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import AcademicResourceTypeList from './AcademicResourceTypeList';
import AcademicResourceTypeModal from './AcademicResourceTypeModal';
import {Roles, State} from '../../core/constants';
import {useNavigate} from 'react-router';
import getCurrentProfile from '../../core/utils/current-profile';
import NavBar from '../../core/component/NavBar';

const AcademicResourceType = () => {
  const [resourceTypeForm, setResourceTypeForm] = useState(initialForm);
  const [resourceTypesList, setResourceTypesList] = useState([]);
  const [refresh, setRefresh] = useState(false);

  const [resourceTypeState, setResourceTypeState] = useState({
    status: State.IDLE,
    error: null,
    errors: {}
  });

  const profile = getCurrentProfile();
  const navigate = useNavigate();

  useEffect(() => {
    if (profile === null || profile === undefined) {
      navigate('/login');
    }
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

  const handleSave = async () => {
    try {
      setResourceTypeState({errors: {}, error: null, status: State.IDLE});

      const res = resourceTypeForm.id > 0 ? await updateApi(resourceTypeForm) : await saveApi(resourceTypeForm);
      setRefresh(!refresh);
      resetForm();
      setShow(false);
      // Handle the response
      if (res.status === 201) {
        setResourceTypeState({...resourceTypeState, status: State.SUCCEEDED});
      } else if (res.status === 400) {
        setResourceTypeState({
          status: State.FAILED,
          error: res.data.message || 'Something went wrong. Please try again later.'
        });
      }
    } catch (err) {
      // Handle errors
      if (err.response && err.response.status === 400) {
        setResourceTypeState({
          status: State.FAILED,
          error: null,
          errors: err.response.data
        });
      } else {
        setResourceTypeState({
          status: State.FAILED,
          errors: {},
          error: 'Something went wrong. Please try again later.'
        });
      }
    }
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
      <NavBar />
      {profile && profile.role === Roles.ADMIN && (
        <button className="btn btn-primary mt-3 mx-5" onClick={handleShow}>
          Add Academic Resource Type
        </button>
      )}

      <AcademicResourceTypeModal
        show={show}
        handleClose={handleClose}
        handleSave={handleSave}
        title="Academic Resource Category"
        resourceTypeForm={resourceTypeForm}
        handleChange={handleChange}
        resourceTypeState={resourceTypeState}
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

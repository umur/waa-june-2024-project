import React from 'react';
import {deleteApi} from '../../service/apiAcademicResourceTypes';

const AcademicResourceTypeList = ({resourceTypesList, setResourceTypeForm, setResourceTypesList, setShow}) => {
  const editHandler = id => {
    if (id) {
      setShow(true);
      const result = resourceTypesList.find(cat => cat.id === id);
      if (result) {
        setResourceTypeForm({
          id: result.id,
          name: result.name
        });
      }
    }
  };

  const deleteHandler = async id => {
    try {
      const data = await deleteApi(id);
      setResourceTypesList(data);
    } catch (error) {
      console.error('Error deleting feedbackcategory:', error);
    }
  };

  return (
    <div className="container">
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {resourceTypesList.map((data, index) => (
            <tr key={data.id}>
              <td>{index + 1}</td>
              <td>{data.name}</td>
              <td className="btn btn-sm btn-success m-1" onClick={() => editHandler(data.id)}>
                Edit
              </td>
              <td className="btn btn-sm btn-danger m-1" onClick={() => deleteHandler(data.id)}>
                Delete
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AcademicResourceTypeList;

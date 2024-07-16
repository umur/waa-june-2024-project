import React from 'react';
import {deleteApi} from '../../service/apiFeedbackCategory';

const FeedbaclCategoryList = ({feedbackCategorysList, setFeedbackCategoryForm, setFeedbackCategorysList, setShow}) => {
  const editHandler = id => {
    if (id) {
      setShow(true);
      const result = feedbackCategorysList.find(cat => cat.id === id);
      if (result) {
        setFeedbackCategoryForm({
          id: result.id,
          name: result.name,
          description: result.description
        });
      }
    }
  };

  const deleteHandler = async id => {
    try {
      const data = await deleteApi(id);
      setFeedbackCategorysList(data);
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
          {feedbackCategorysList.map((data, index) => (
            <tr key={data.id}>
              <td>{index + 1}</td>
              <td>{data.name}</td>
              <td>{data.description}</td>
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

export default FeedbaclCategoryList;

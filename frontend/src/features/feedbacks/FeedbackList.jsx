import React, {useState} from 'react';
import {deleteApi} from '../../service/apiFeedbackService';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';

const FeedbackList = ({feedbacksList, setFeedbacksList, setFeedbackForm, setShow}) => {
  const [showDialog, setShowDialog] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);

  const editHandler = id => {
    if (id) {
      setShow(true);
      const result = feedbacksList.find(cat => cat.id === id);
      if (result) {
        setFeedbackForm({
          id: result.id,
          title: result.title,
          body: result.body,
          feedbackCategory: result.category.id
        });
      }
    }
  };

  const handleConfirmDelete = async () => {
    if (itemToDelete) {
      try {
        const data = await deleteApi(itemToDelete);
        setFeedbacksList(data);
        setItemToDelete(null);
      } catch (error) {
        console.error('Error deleting feedbackcategory:', error);
      }
      setShowDialog(false);
    }
  };

  const handleCloseDialog = () => {
    setShowDialog(false);
    setItemToDelete(null);
  };

  const deleteHandler = id => {
    setItemToDelete(id); // Set the item to delete
    setShowDialog(true); // Show the confirmation dialog
  };

  return (
    <div className="container">
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Category</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {feedbacksList.map((data, index) => (
            <tr key={data.id}>
              <td>{index + 1}</td>
              <td>{data.title}</td>
              <td>{data.body}</td>
              <td>{data.category.name}</td>
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

      <ConfirmationDialog
        show={showDialog}
        handleClose={handleCloseDialog}
        handleConfirm={handleConfirmDelete}
        title="Confirm Delete"
        body="Are you sure you want to delete this item?"
      />
    </div>
  );
};

export default FeedbackList;

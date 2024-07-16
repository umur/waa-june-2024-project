import React, {useState} from 'react';
import {deleteApi} from '../../service/apiFeedbackCategory';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';

const FeedbaclCategoryList = ({feedbackCategorysList, setFeedbackCategoryForm, setFeedbackCategorysList, setShow}) => {
  const [showDialog, setShowDialog] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);

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

  const handleConfirmDelete = async () => {
    if (itemToDelete) {
      try {
        const data = await deleteApi(itemToDelete);
        setFeedbackCategorysList(data);
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

export default FeedbaclCategoryList;

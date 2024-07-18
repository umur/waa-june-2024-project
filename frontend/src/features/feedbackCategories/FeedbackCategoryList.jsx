import React, {useState} from 'react';
import {deleteApi} from '../../service/apiFeedbackCategory';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';
import TableComponent from '../../core/component/dialogs/table/TableComponent';

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

  const headers = ['SlNo', 'Name', 'Description'];

  const formattedData = feedbackCategorysList.map((item, index) => ({
    SlNo: index + 1,
    Name: item.name,
    Description: item.description,
    id: item.id
  }));

  return (
    <div className="container">
      <div>
        <TableComponent
          headers={headers}
          data={formattedData}
          editHandler={editHandler}
          deleteHandler={deleteHandler}
        />

        <ConfirmationDialog
          show={showDialog}
          handleClose={handleCloseDialog}
          handleConfirm={handleConfirmDelete}
          title="Confirm Delete"
          body="Are you sure you want to delete this item?"
        />
      </div>
    </div>
  );
};

export default FeedbaclCategoryList;

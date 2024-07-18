import React, {useState} from 'react';
import {deleteApi} from '../../service/apiFeedbackService';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';
import TableComponent from '../../core/component/dialogs/table/TableComponent';

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

  const headers = ['SlNo', 'Title', 'Description', 'Category', 'Owner', 'Date'];

  const formatDate = date => {
    const options = {day: '2-digit', weekday: 'short', month: 'short', year: 'numeric'};
    return new Date(date).toLocaleDateString('en-US', options).replace(/,/g, '');
  };

  const formattedData = feedbacksList.map((item, index) => ({
    SlNo: index + 1,
    Title: item.title,
    Description: item.body,
    Category: item.category.name,
    id: item.id,
    Owner: item.student?.id ? item.student.username : 'Anoynomous',
    Date: item.postedDate ? formatDate(item.postedDate) : 'N/A'
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

export default FeedbackList;

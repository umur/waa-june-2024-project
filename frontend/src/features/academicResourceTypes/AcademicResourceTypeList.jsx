import React, {useState} from 'react';
import {deleteApi} from '../../service/apiAcademicResourceTypes';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';
import TableComponent from '../../core/component/dialogs/table/TableComponent';

const AcademicResourceTypeList = ({resourceTypesList, setResourceTypeForm, setResourceTypesList, setShow}) => {
  const [showDialog, setShowDialog] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);

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
  const handleConfirmDelete = async () => {
    if (itemToDelete) {
      try {
        const data = await deleteApi(itemToDelete);
        setResourceTypesList(data);
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

  const headers = ['SlNo', 'Name'];

  const formattedData = resourceTypesList.map((item, index) => ({
    SlNo: index + 1,
    Name: item.name,
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

export default AcademicResourceTypeList;

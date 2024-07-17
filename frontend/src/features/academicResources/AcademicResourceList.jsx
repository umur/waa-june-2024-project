import React, {useState} from 'react';
import {deleteApi} from '../../service/apiAcademicResource';
import ConfirmationDialog from '../../core/component/dialogs/ConfirmationDialog';
import TableComponent from '../../core/component/dialogs/table/TableComponent';

const AcademicResourceList = ({academicResourceList, setAcademicResourceList, setAcademicResForm, setShow}) => {
  const [showDialog, setShowDialog] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);

  const editHandler = id => {
    if (id) {
      setShow(true);
      const result = academicResourceList.find(cat => cat.id === id);
      console.log(result);
      if (result) {
        setAcademicResForm({
          id: result.id,
          name: result.name,
          body: result.body,
          resourceCategory: result.resourceType.id,
          file: null
        });
      }
    }
  };

  const handleConfirmDelete = async () => {
    if (itemToDelete) {
      try {
        const data = await deleteApi(itemToDelete);
        setAcademicResourceList(data);
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

  const headers = ['SlNo', 'Name', 'Description', 'Category'];

  const formattedData = academicResourceList.map((item, index) => ({
    SlNo: index + 1,
    Name: item.name,
    Description: item.body,
    Category: item.resourceType.name,
    id: item.id
  }));

  return (
    <div>
      <div className="container">
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

export default AcademicResourceList;

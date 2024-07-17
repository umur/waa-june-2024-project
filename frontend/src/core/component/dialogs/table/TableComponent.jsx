import React from 'react';

const TableComponent = ({headers, data, editHandler, deleteHandler}) => {
  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            {headers.map((header, index) => (
              <th key={index} scope="col">
                {header}
              </th>
            ))}
            {(editHandler || deleteHandler) && <th scope="col" colSpan={2}></th>}
          </tr>
        </thead>
        <tbody>
          {data.map((item, rowIndex) => (
            <tr key={item.id}>
              {headers.map((header, colIndex) => (
                <td key={colIndex}>{item[header]}</td>
              ))}
              {editHandler && (
                <td className="btn btn-sm btn-success m-1" onClick={() => editHandler(item.id)}>
                  Edit
                </td>
              )}
              {deleteHandler && (
                <td className="btn btn-sm btn-danger m-1" onClick={() => deleteHandler(item.id)}>
                  Delete
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableComponent;

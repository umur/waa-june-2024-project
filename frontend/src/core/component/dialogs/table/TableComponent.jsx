import React from 'react';
import getCurrentProfile from '../../../utils/current-profile';
import {Roles} from '../../../constants';

const TableComponent = ({headers, data, editHandler, deleteHandler}) => {
  const profile = getCurrentProfile();

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
            {profile && profile.role === Roles.ADMIN && (
              <>{(editHandler || deleteHandler) && <th scope="col" colSpan={2}></th>}</>
            )}
          </tr>
        </thead>
        <tbody>
          {data.map((item, rowIndex) => (
            <tr key={item.id}>
              {headers.map((header, colIndex) => (
                <td key={colIndex}>{item[header]}</td>
              ))}

              {(profile && profile.user === item.Owner) || (profile && profile.role === Roles.ADMIN) ? (
                <>
                  <td className="btn btn-sm btn-success m-1" onClick={() => editHandler(item.id)}>
                    Edit
                  </td>
                  <td className="btn btn-sm btn-danger m-1" onClick={() => deleteHandler(item.id)}>
                    Delete
                  </td>
                </>
              ) : null}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableComponent;

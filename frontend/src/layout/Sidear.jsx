import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import './Sidebar.css';
import {Roles} from '../core/constants';

const Sidebar = ({profile}) => {
  const [open, setOpen] = useState(false);

  const toggleDropdown = () => {
    setOpen(!open);
  };

  return (
    <div className="sidebar bg-dark text-white">
      <div className="p-3">
        <h5 className="mb-3">Student Connect</h5>
        <ul className="nav flex-column">
          <li className="nav-item">
            <Link className="nav-link text-white" to="/dashboard">
              Dashboard
            </Link>
          </li>

          <li className="nav-item">
            <div className={`dropdown ${open ? 'show' : ''}`}>
              <button
                className="btn bg-dark dropdown-toggle text-white w-100 text-start"
                type="button"
                onClick={toggleDropdown}
                style={{border: 'none'}} // Remove default border
              >
                Master Data <i className={`fas fa-chevron-${open ? 'up' : 'down'}`}></i>
              </button>
              <ul className={`dropdown-menu w-100 ${open ? 'show' : ''}`} style={{backgroundColor: '#343a40'}}>
                <li>
                  <Link className="dropdown-item text-white" to="/charts/chart1">
                    Chart 1
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item text-white" to="/charts/chart2">
                    Chart 2
                  </Link>
                </li>
              </ul>
            </div>
          </li>

          {profile && profile.role === Roles.ADMIN && (
            <>
              {' '}
              <li className="nav-item">
                <Link className="nav-link text-white" to="/feedback-categories">
                  Feedback Category
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/academic-resouce-type">
                  Academic Resouce Type
                </Link>
              </li>
            </>
          )}

          <li className="nav-item">
            <Link className="nav-link text-white" to="/feedbacks">
              Feedbacks
            </Link>
          </li>

          <li className="nav-item">
            <Link className="nav-link text-white" to="/academic-resouces">
              Academic Resouce
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;

import NavBar from '../../core/component/NavBar';
import {Container, Row} from 'react-bootstrap';
import DashboardCard from '../dashboard-card/dashboard-card';
import {faCalendarAlt, faUserGraduate} from '@fortawesome/free-solid-svg-icons';

export default function AdminDashboard() {
  const options = [
    {icon: faUserGraduate, title: 'Students', link: '/students'},
    {icon: faCalendarAlt, title: 'Events', link: '/events'}
  ];

  return (
    <>
      <NavBar />
      <Container className="mt-5">
        <Row>
          {options.map(opt => (
            <DashboardCard icon={opt.icon} link={opt.link} title={opt.title} />
          ))}
        </Row>
      </Container>
    </>
  );
}

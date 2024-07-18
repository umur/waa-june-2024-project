import NavBar from '../../core/component/NavBar';
import {fa8, faCalendarAlt, faComment, faUserGraduate} from '@fortawesome/free-solid-svg-icons';
import {Container, Row} from 'react-bootstrap';
import DashboardCard from '../dashboard-card/dashboard-card';

export default function StudentDashboard() {
  const options = [
    {icon: faUserGraduate, title: 'Students', link: '/students'},
    {icon: faCalendarAlt, title: 'Events', link: '/events'},
    {icon: faComment, title: 'Discussion', link: '/discussions'}
    {icon: faCalendarAlt, title: 'Events', link: '/events'},
<<<<<<< HEAD
    {icon: faCalendarAlt, title: 'Feedback', link: '/feedbacks'},
    {icon: faUserGraduate, title: 'Academic Resouces', link: '/academic-resouces'}
=======
    {icon: faComment, title: 'Discussion', link: '/discussions'}
>>>>>>> develop
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
